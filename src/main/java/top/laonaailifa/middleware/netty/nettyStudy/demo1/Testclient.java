package top.laonaailifa.middleware.netty.nettyStudy.demo1;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.nio.charset.StandardCharsets;

public class Testclient {
    public static void main(String[] args) throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup(1);
        Bootstrap bootstrap = new Bootstrap();
        ChannelFuture connect = bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new StringDecoder())
                                .addLast(new ChannelInboundHandlerAdapter(){
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                        System.out.println(msg);
                                    }
                                });

                    }
                }).connect(new InetSocketAddress("127.0.0.1", 9090));

        Channel client = connect.sync().channel();

        ByteBuf buf = Unpooled.copiedBuffer("hello server\n".getBytes(StandardCharsets.UTF_8));
        ChannelFuture send = client.writeAndFlush(buf);
//        ChannelFuture send = client.writeAndFlush("hello");
        send.sync();

        client.closeFuture().sync();
    }
}
