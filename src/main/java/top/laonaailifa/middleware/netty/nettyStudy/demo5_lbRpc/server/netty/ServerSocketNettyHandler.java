package top.laonaailifa.middleware.netty.nettyStudy.demo5_lbRpc.server.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.reflections.Reflections;
import top.laonaailifa.middleware.netty.nettyStudy.demo5_lbRpc.entity.ClassInfo;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@ChannelHandler.Sharable
public class ServerSocketNettyHandler extends ChannelInboundHandlerAdapter {
    public static ServerSocketNettyHandler serverSocketNettyHandler
            = new ServerSocketNettyHandler();
    private static ExecutorService executorService
            = Executors.newFixedThreadPool(1000);

    public String getImplClassName(ClassInfo classInfo) throws ClassNotFoundException {
        String iName = "top.laonaailifa.nettyStudy.demo5_lbRpc.server.service";
        int i = classInfo.getClassName().lastIndexOf(".");
        String className = classInfo.getClassName().substring(i);
        Class aClass = Class.forName(iName + className);
        Reflections reflections = new Reflections(iName);
        Set<Class<?>> classes = reflections.getSubTypesOf(aClass);
        if (classes.size() == 0) {
            System.out.println("未找到实现类");
            return null;
        } else if (classes.size() > 1) {
            System.out.println("找到多个实现类,未明确使用那个实现类");
            return null;
        } else {
            Class[] classes1 = classes.toArray(new Class[0]);
            return classes1[0].getName();
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                ClassInfo classInfo = (ClassInfo) msg;
                try {
                    Object o = Class.forName(getImplClassName(classInfo)).newInstance();
                    Method method = o.getClass().getMethod(classInfo.getMethodName(), classInfo.getClazzTyoe());
                    Object invoke = method.invoke(o, classInfo.getArgs());
                    ctx.channel().writeAndFlush(invoke);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
