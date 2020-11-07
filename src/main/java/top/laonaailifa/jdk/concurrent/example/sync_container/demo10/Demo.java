package top.laonaailifa.jdk.concurrent.example.sync_container.demo10;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

/**
 * TransferQueue
 * 和普通的queue的方法差不多，多了一个transfer方法。
 * 如果你用这种队列的话，往往是消费者先启动，生产者生产一个东西的时候，他先是去找消费者，
 * 如果有消费者就直接丢给消费者。
 */
public class Demo {
    public static void main(String[] args) throws InterruptedException {
        TransferQueue<String> strings = new LinkedTransferQueue<>();
        new Thread(() -> {
            try {
                System.out.println("t1" + strings.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println("t2"+strings.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        TimeUnit.SECONDS.sleep(2);

        strings.transfer("aaa");
        System.out.println(strings.size());
//        new Thread(() -> {
//            try {
//                System.out.println("t3"+strings.take());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
    }
}
