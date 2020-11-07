package top.laonaailifa.middleware.netty.nettyStudy.demo4_heartBeat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.logging.LogLevel;

import java.nio.channels.SocketChannel;

public class LoggingHandler extends SimpleChannelInboundHandler<SocketChannel> {

    public LoggingHandler(LogLevel info) {
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, SocketChannel msg) throws Exception {

    }
}
