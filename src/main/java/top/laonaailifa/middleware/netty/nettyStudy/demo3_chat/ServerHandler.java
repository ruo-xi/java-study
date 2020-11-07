package top.laonaailifa.middleware.netty.nettyStudy.demo3_chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ServerHandler extends SimpleChannelInboundHandler<String> {

    static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();
        group.forEach(ch -> {
            System.out.println(channel.remoteAddress() + ":" + msg + "\r\n");
            if (ch != channel) {
                ch.writeAndFlush(channel.remoteAddress() + ":" + msg + "\r\n");
            }
        });
        ctx.fireChannelRead(msg);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        group.writeAndFlush(channel.remoteAddress().toString() + "connected");
        group.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        group.writeAndFlush(channel.remoteAddress().toString() + "leave");
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress().toString() + "online");
        System.out.println(group.size());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress().toString() + "ouline");
        System.out.println(group.size());
    }
}
