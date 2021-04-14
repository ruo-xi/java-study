package top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc;

import java.util.concurrent.ConcurrentHashMap;

public class Dispatcher {


    public static ConcurrentHashMap<String, Object> invokeMapping = new ConcurrentHashMap<>();

    public static void register(String k, Object o) {
        invokeMapping.put(k, o);
    }

    public static Object get(String key) {
        return invokeMapping.get(key);
    }

}
