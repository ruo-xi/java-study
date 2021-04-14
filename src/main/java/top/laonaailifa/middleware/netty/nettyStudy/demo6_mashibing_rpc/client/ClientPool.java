package top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc.client;

import io.netty.channel.socket.nio.NioSocketChannel;

public class ClientPool {
    NioSocketChannel[] clients;
    Object[] lock;

    public ClientPool(int size) {
        clients = new NioSocketChannel[size];
        lock = new Object[size];
        for (int i = 0; i < lock.length; i++) {
            lock[i] = new Object();
        }
    }

}
