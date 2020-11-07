package top.laonaailifa.jdk.jol;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.CountDownLatch;

/**
 * 偏向锁 14
 * 轻量级锁 145ms
 * 重量级锁 845
 */
public class JolExample4 {

    static CountDownLatch countDownLatch = new CountDownLatch(10000000);

    public static void main(String[] args) throws InterruptedException {
        A a = new A();
        /**
         * 执行该方法为轻量级锁
         * 不执行则是偏向锁
         */
//        a.hashCode();
        long start = System.currentTimeMillis();


//        for (int i = 0; i < 10000000L; i++) {
//            a.parse();
//        }

        /**
         * 重量级锁方法
         */
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (countDownLatch.getCount() > 0){
                    a.parse();
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();

        long end = System.currentTimeMillis();
        System.out.println(String.format("%sms", end - start));
    }
}
