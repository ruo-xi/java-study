package top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc.client;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.junit.jupiter.api.Test;
import top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc.Car;
import top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc.Content;
import top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc.Header;
import top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc.server.RPCServer;
import top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc.tool.SerDerUtil;

import java.io.IOException;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * demand
 * 1. Two-way communication
 * 2. Number of connections
 * 3. Unpack
 * 4. Dynamic proxy
 * 5. Serialization
 * 6. Protocol encapsulation
 * 7. Connection pool
 */
public class RPCTest {

    public static void main(String[] args) {
        new Thread(RPCServer::startServer).start();

        System.out.println("server starting ...");

        AtomicInteger atomicInteger = new AtomicInteger(0);
        Thread[] threads = new Thread[20];
        for (int i = 0; i < threads.length; i++) {
            new Thread(() -> {
                Car car = proxyGet(Car.class);
                String ooxx = car.ooxx("hello world" + atomicInteger.incrementAndGet());//dyamic proxy
                System.out.println("rse: " + ooxx);
            }).start();
        }

//        for (int i = 0; i < 20; i++) {
//            Car car = proxyGet(Car.class);
//            car.ooxx("hello world" + atomicInteger.incrementAndGet());//dyamic proxy
//        }

//        Cat cat = proxyGet(Cat.class);
//        cat.xxoo("hello world" + atomicInteger.incrementAndGet());//dyamic proxy


        try {
//            Thread.sleep(5000);
            System.in.read();
            System.out.println("end -------");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static <T> T proxyGet(Class<T> klass) {
        ClassLoader classLoader = klass.getClassLoader();

        return klass.cast(Proxy.newProxyInstance(classLoader, new Class[]{klass}, (proxy, method, args) -> {

            // 1. call service method args  -> message [content]
            String name = klass.getName();
            String methodName = method.getName();
            Class<?>[] parameterTypes = method.getParameterTypes();

            Content content = new Content();
            content.setName(name);
            content.setMethodName(methodName);
            content.setParameterTypes(parameterTypes);
            content.setArgs(args);


            // 2. requestID+message  Local cache
            byte[] msgBody = SerDerUtil.ser(content);
            Header header = createHeader(msgBody);
            byte[] msgHeader = SerDerUtil.ser(header);

            // 3. Connection pool  ::  to get tthe connection
            ClientFactory factory = ClientFactory.getFactory();
//            NioSocketChannel clientChannel = factory.getClient(new InetSocketAddress("localhost", 9090));
            NioSocketChannel clientChannel = factory.getClient(new InetSocketAddress("127.0.0.1", 9090));

            // 4. send  -> IO -> out
            ByteBuf byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(msgHeader.length + msgBody.length);

            long id = header.getRequestID();
//            CountDownLatch countDownLatch = new CountDownLatch(1);
            CompletableFuture<String> res = new CompletableFuture<>();
            ResponseMappingCallback.addCallBack(id, res);


            byteBuf.writeBytes(msgHeader);
            byteBuf.writeBytes(msgBody);

            ChannelFuture channelFuture = clientChannel.writeAndFlush(byteBuf);
            channelFuture.sync();

//            countDownLatch.await();

            // 5. if io return  how can the process execute the code below (sleep / callback)
            return res.get();
        }));
    }

    private static Header createHeader(byte[] msgBody) {
        Header header = new Header();
        header.setFlag(0x14141414);
        header.setRequestID(Math.abs(UUID.randomUUID().getLeastSignificantBits()));
        header.setDataLen(msgBody.length);
        return header;
    }


}
