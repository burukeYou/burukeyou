package burukeyou.im.server;

import burukeyou.auth.authClient.util.AuthUtils;
import burukeyou.im.server.entity.bo.ChatDataContent;
import burukeyou.im.server.entity.bo.ChatMessage;
import burukeyou.im.server.entity.bo.UserChanelMap;
import burukeyou.im.server.netty.IMServerInitialzer;
import burukeyou.im.server.netty.handler.CustomMessageHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class IMConnectServer {

    private static IMConnectServer server;

    @Value("${custom.im.port}")
    private int port;

    private EventLoopGroup masterGroup;  //负责创建新连接
    private EventLoopGroup slaveGroup;   // 负责读取数据的线程,主要用于读取数据以及业务逻辑处理
    private ServerBootstrap serverBootstrap;  //服务端引导类, 这个类将引导我们进行服务端的启动工作
    private ChannelFuture channelFuture; //类似future代表异步计算结果

    //初始化
    public IMConnectServer() {
        masterGroup = new NioEventLoopGroup();
        slaveGroup = new NioEventLoopGroup();
        serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(masterGroup,slaveGroup)  //给引导类配置两大线程组，这个引导类的线程模型就定型了。
                .channel(NioServerSocketChannel.class)  //指定服务端的 IO 模型为NIO
                .childHandler(new IMServerInitialzer()) //配置所有通道处理器的初始化器,指定处理新连接数据的读写处理逻辑
                //.handler(new IMServerInitialzer()) //指定在服务端启动过程中的一些逻辑
                .childOption(ChannelOption.SO_KEEPALIVE,true)  //客户端-(给每条连接设置一些TCP底层相关的属性) 这里是表示是否开启TCP底层心跳机制，true为开启
                .childOption(ChannelOption.TCP_NODELAY, false) //表示是否开启Nagle算法，true表示关闭，，如果要求高实时性，有数据发送时就马上发送，就关闭，如果需要减少发送次数减少网络交互，就开启。
                .option(ChannelOption.SO_BACKLOG, 1024);//给服务端channel设置一些属性:表示系统用于临时存放已完成三次握手的请求的队列的最大长度，如果连接建立频繁，服务器处理创建新连接较慢，可以适当调大这个参数
    }


    public void startServer(){
        startServer(this.port);
    }

    public void startServer(final Integer port){
        this.channelFuture = serverBootstrap.bind(this.port); //一个异步的方法，调用之后是立即返回的

        // 给chanle添加监听器s
        channelFuture.addListener(new GenericFutureListener<Future<? super Void>>() {
            @Override
            public void operationComplete(Future<? super Void> future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("端口"+port+"绑定成功!");
                } else {
                    System.err.println("端口"+port+"绑定失败!");
                    startServer(port + 1);  //自动绑定递增端口
                }
            }
        });

        //
        System.err.println("聊天服务器启动成功");
    }



    private ObjectMapper objectMapper = new ObjectMapper();

    // 下推消息
    public void sendMsg(String receivedId,String message) throws JsonProcessingException {
        Channel receiverChannel = UserChanelMap.getChanleById(receivedId);  //获得消息接受者的chanel
        if (receiverChannel == null){
            //  channel为空代表用户离线,推送消息(push,个推,小米推送)
            // todo 离线消息推送到mq中
        }else {
            ChannelGroup clientGroup = CustomMessageHandler.clientGroup;
            Channel channel =  clientGroup.find(receiverChannel.id());
            if (channel != null){ //表示用户在线
                ChatMessage chatMessage = new ChatMessage();
                chatMessage.setMsg(message);
                chatMessage.setAcceptId(receivedId);
                chatMessage.setSendId(AuthUtils.ID());
                chatMessage.setSendAvatar(AuthUtils.AVATAR());
                chatMessage.setSendNickname(AuthUtils.NICKNAME());
                chatMessage.setType("text");
                receiverChannel.writeAndFlush(new TextWebSocketFrame(objectMapper.writeValueAsString(
                        ChatDataContent.builder().chatMessage(chatMessage).build())));
            }else {
                //  用户离线,推送消息
            }
        }
    }
}
