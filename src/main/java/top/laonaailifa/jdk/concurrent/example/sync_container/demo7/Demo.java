package top.laonaailifa.jdk.concurrent.example.sync_container.demo7;

import top.laonaailifa.middleware.netty.bioStudy.oneReactor.TcpHandler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * LinkedBlockingQueue
 */
public class Demo {
    private static BlockingQueue<String> queue = new LinkedBlockingDeque<>(2);

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                try {
                    queue.put("商品" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                for (; ; ) {
                    try {
                        System.out.println(Thread.currentThread().getName() + "take " + queue.take());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "consumer" + i).start();
        }
    }

}
