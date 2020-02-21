package burukeyou.im.server.netty;

import burukeyou.im.server.netty.handler.CustomMessageHandler;
import burukeyou.im.server.netty.handler.HeartBeatHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;


/**
 *  每次有新连接到来的时候，都会调用 ChannelInitializer 的 initChannel() 方法，
 *  然后这里 所有 handler 都会被 new 一次。
 *  优化： 对无状态的handler（就是内部无成员变量）使用单例模式,
 *        这样的话，每来一次新的连接，添加 该handler 的时候就不需要每次都 new 了
 *
 *
 */
public class IMServerInitialzer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //
        ChannelPipeline pipeline = socketChannel.pipeline();

        //
        //添加ChannelHandler，用于对hhttp协议支持
        pipeline.addLast(new HttpServerCodec());   //编解码器
        pipeline.addLast(new ChunkedWriteHandler());   //对写大数据流的支持
        pipeline.addLast(new HttpObjectAggregator(1024));  //http聚合器，集成request，response


        //添加WebSocket协议支持的组件

        //hand1e会帮你处理一些繁重的复杂的事
        //会帮你处理握手动作: handshaking(c1ose,ping,pong)  ping(请求)+pong(响应)=心跳
        //对于 websocket来讲,都是以 frames进行传输的,不同的数据类型对应的 frames也不同
        pipeline.addLast(new WebSocketServerProtocolHandler("/connect"));//用于指定给客户端连接访问的路由:/connect

        //1-添加自定义处理器
        pipeline.addLast(new CustomMessageHandler());

        //2-
        pipeline.addLast(new IdleStateHandler(2,4,14));
       // pipeline.addLast(new HeartBeatHandler());

    }
}
