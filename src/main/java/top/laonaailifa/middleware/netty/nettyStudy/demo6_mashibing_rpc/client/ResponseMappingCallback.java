package top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc.client;

import top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc.Packmsg;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class ResponseMappingCallback {
    static ConcurrentHashMap<Long, CompletableFuture> mapping = new ConcurrentHashMap<>();

    public static void addCallBack(long requestID, CompletableFuture cb) {
        mapping.putIfAbsent(requestID, cb);
    }

    public static void runCallBack(Packmsg packmsg) {
        CompletableFuture cf = mapping.get(packmsg.getHeader().getRequestID());
        cf.complete(packmsg.getContent().getRes());
        removeCB(packmsg.getHeader().getRequestID());
    }
    public static void removeCB(long requsetID){
        mapping.remove(requsetID);
    }
}
