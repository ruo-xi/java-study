package top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc.*;
import top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc.handler.DecodeHandler;
import top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc.handler.ServerRequestHandler;

import java.net.InetSocketAddress;

public class RPCServer {
    public static void startServer() {

        MyCar myCar = new MyCar();
        MyCat myCat = new MyCat();
        Car.class.getSimpleName();
        Dispatcher.register(Car.class.getName(), myCar);
        Dispatcher.register(Cat.class.getName(), myCat);
        new Thread();


        NioEventLoopGroup boss = new NioEventLoopGroup(1);
//        NioEventLoopGroup worker = new NioEventLoopGroup();

        NioEventLoopGroup worker = boss;

        ServerBootstrap sbs = new ServerBootstrap();
        ChannelFuture bind = sbs.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) {
                        // ch.pipeline().addLast(new ServerRequestHandler());
                        System.out.println("client port : " + ch.remoteAddress().getPort());
//                        System.out.println("client port : " + ch.remoteAddress().getAddress());
                        ch.pipeline().addLast(new DecodeHandler())
                                .addLast(new ServerRequestHandler());
                    }
                })
                .bind(new InetSocketAddress("127.0.0.1", 9090));
//                .bind(new InetSocketAddress("hostname", 9090));

        try {
            bind.sync().channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
