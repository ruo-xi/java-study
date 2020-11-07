package top.laonaailifa.jdk.proxy.jdk;

import java.lang.annotation.Annotation;
import java.lang.reflect.Proxy;

/**
 *
 */
public class Test {
    public static void main(String[] args) {
        System.setProperty("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        Dao dao = (Dao) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Dao.class}, (proxy, method, args1) -> {
            Select annotation = method.getAnnotation(Select.class);
            String s = annotation.value();
            return s;
        });
        System.out.println(dao.query());
    }
}
