package top.laonaailifa.middleware.netty.nettyStudy.demo3_chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {
    public static void main(String[] args) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(bossGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ClintInitializer());
            ChannelFuture connect = bootstrap.connect("127.0.0.1", 9999).sync();
            ChannelFuture channelFuture = connect.channel().closeFuture();
            Channel channel = channelFuture.channel();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            for (; ; ) {
                channel.writeAndFlush(bufferedReader.readLine() + "\r\n");
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
        }

    }
}
