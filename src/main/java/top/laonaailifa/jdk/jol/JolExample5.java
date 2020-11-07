package top.laonaailifa.jdk.jol;

import java.util.concurrent.CountDownLatch;

public class JolExample5 {
    static CountDownLatch countDownLatch = new CountDownLatch(1000000000);
    public static void main(String[] args) throws InterruptedException {
        A a = new A();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                a.parse();
                countDownLatch.countDown();
            }).start();
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println(start - end);
    }
}
