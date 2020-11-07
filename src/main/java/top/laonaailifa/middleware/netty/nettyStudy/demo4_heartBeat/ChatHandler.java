package top.laonaailifa.middleware.netty.nettyStudy.demo4_heartBeat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    public static ChannelGroup users = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        String text = msg.text();
        System.out.println("接收到的数据: " + text);
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        users.add(ctx.channel());
        String s = ctx.channel().id().asShortText();
        System.out.println("客户端添加, id = " + s);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        String s = ctx.channel().id().asShortText();
        System.out.println("客户端移除, id = " + s);
        users.remove(ctx.channel());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress().toString() + "online");
        System.out.println(users.size());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress().toString() + "ouline");
        System.out.println(users.size());
    }
}
