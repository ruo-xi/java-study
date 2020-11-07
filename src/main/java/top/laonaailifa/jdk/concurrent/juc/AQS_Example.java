package top.laonaailifa.jdk.concurrent.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class AQS_Example {
    /**
     * 公平锁 非公平锁的区别
     * 非公平锁也是在没有以下发生的情况下按顺序唤醒线程
     * 锁的自由时间期间  公平锁会将期间加入的线程放入队列    非公平锁会直接执行
     * 如下对比
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        /**
         * 非公平锁按顺序唤醒例子
         */
//        for (int i = 0; i < 10; i++) {
//            int g = i;
//            new Thread(() -> {
//                lock.lock();
//                try {
//                    countDownLatch.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(g);
//                lock.unlock();
//            }).start();
//            Thread.sleep(100);
//            countDownLatch.countDown();
//        }

        for (int i = 0; i < 10; i++) {
            int g = i;
            new Thread(() -> {
                lock.lock();
                System.out.println("before" + g);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }).start();
//            System.out.println("after" + g);
//            Thread.sleep(100);
        }
    }
}
