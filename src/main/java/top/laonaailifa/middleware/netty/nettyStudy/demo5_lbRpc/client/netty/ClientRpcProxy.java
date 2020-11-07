package top.laonaailifa.middleware.netty.nettyStudy.demo5_lbRpc.client.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import top.laonaailifa.middleware.netty.nettyStudy.demo5_lbRpc.entity.ClassInfo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ClientRpcProxy {
    public static Object create(Class clazz) {
        return Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) {
                ClassInfo classInfo = new ClassInfo();
                classInfo.setClassName(clazz.getName());
                classInfo.setMethodName(method.getName());
                classInfo.setArgs(args);
                classInfo.setClazzTyoe(method.getParameterTypes());

                NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
                Bootstrap bootstrap = new Bootstrap();
                ClientSocketNettyHandler clientSocketNettyHandler = new ClientSocketNettyHandler();

                bootstrap.group(eventExecutors)
                        .channel(NioSocketChannel.class)
                        .handler(new ChannelInitializer<SocketChannel>() {
                            @Override
                            protected void initChannel(SocketChannel ch) throws Exception {
                                ch.pipeline().addLast(new ObjectEncoder())
                                        .addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)))
                                        .addLast(clientSocketNettyHandler);
                            }
                        });
                System.out.println("..............client init.............");
                try {
                    ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9999).sync();
                    channelFuture.channel().writeAndFlush(classInfo).sync();
                    channelFuture.channel().closeFuture().sync();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    return clientSocketNettyHandler.getResponse();
                }
            }
        });
    }
}
