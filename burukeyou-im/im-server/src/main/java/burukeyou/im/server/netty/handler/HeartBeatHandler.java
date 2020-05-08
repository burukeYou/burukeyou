package burukeyou.im.server.netty.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 *      检测  channel 的心跳的处理器
 *          继承 Channe1 nboundHandlerAdapter,从而不需要实现 channe1Read0方法
 *
 *  非常重要的一点: 如果一个 handler 要被多个 channel 进行共享，必须要加上 @ChannelHandler.Sharable 显示地告诉 Netty，
 *                这个 handler 是支持多个 channel 共享的，否则会报错.
 *
 */

//@ChannelHandler.Sharable  注解标识: 这个handler 是可以被多个 channel 共享的
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        // 判断是否是空闲状态监测事件 （当channel空闲时(读空闲，写空闲。。)触发的用户事件）
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event =(IdleStateEvent)evt;

            if (event.state() == IdleState.READER_IDLE){
                System.out.println("read idle");
            }else if(event.state() == IdleState.WRITER_IDLE){
                System.out.println("write idle");
            }else if(event.state() == IdleState.ALL_IDLE){
                Channel currentChannel = ctx.channel();
               // currentChannel.close();  //关闭无用channel
                System.out.println("read write idle.....");

            }

        }


    }
}
