package top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc.handler.ClientResponseHandler;
import top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc.handler.DecodeHandler;

import java.net.InetSocketAddress;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class ClientFactory {
    int poolSize = 10;

    Random random = new Random();
    NioEventLoopGroup clientWorker;
    // one consumer can connect multiple provider. each provider has its own pool
    ConcurrentHashMap<InetSocketAddress, ClientPool> outBoxs = new ConcurrentHashMap<>();


    private volatile static ClientFactory factory;

//    static {
//        factory = new ClientFactory();
//
//    }

//    public static ClientFactory getFactory() {
//        return factory;
//    }

    public static ClientFactory getFactory() {
        if (factory == null) {
            synchronized (ClientFactory.class) {
                if (factory == null) {
                    factory = new ClientFactory();
                }
            }
        }
        return factory;
    }

    public synchronized NioSocketChannel getClient(InetSocketAddress address) {
        ClientPool clientPool = outBoxs.get(address);
        if (clientPool == null) {
            outBoxs.putIfAbsent(address, new ClientPool(poolSize));
            clientPool = outBoxs.get(address);
        }
        int i = random.nextInt(poolSize);

        if (clientPool.clients[i] != null && clientPool.clients[i].isActive()) {
            return clientPool.clients[i];
        }

        synchronized (clientPool.lock[i]) {
            return clientPool.clients[i] = create(address);
        }
    }

    private NioSocketChannel create(InetSocketAddress address) {
        clientWorker = new NioEventLoopGroup(1);
        Bootstrap bootstrap = new Bootstrap();
        ChannelFuture connect = bootstrap.group(clientWorker)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new DecodeHandler())
                                .addLast(new ClientResponseHandler());
                    }
                })
                .connect(address);
        try {
            NioSocketChannel client = (NioSocketChannel) connect.sync().channel();
            return client;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;


    }


}
