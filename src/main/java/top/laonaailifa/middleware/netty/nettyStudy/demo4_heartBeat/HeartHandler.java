package top.laonaailifa.middleware.netty.nettyStudy.demo4_heartBeat;

import io.netty.channel.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class HeartHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.READER_IDLE) {
                System.out.println("进入读空闲");
            } else if (event.state() == IdleState.WRITER_IDLE) {
                System.out.println("进入写空闲");
            } else if (event.state() == IdleState.ALL_IDLE) {
                System.out.println("channle 关闭前, users数量:" + ChatHandler.users.size());

                Channel channel = ctx.channel();
                channel.close();
                System.out.println("channle 关闭后, users数量:" + ChatHandler.users.size());

            }
        }
    }
}
