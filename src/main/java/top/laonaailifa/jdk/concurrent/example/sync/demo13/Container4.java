package top.laonaailifa.jdk.concurrent.example.sync.demo13;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Container4 {
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void sleepOneSecond() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Container4 c = new Container4();
//        Object lock = new Object();
        CountDownLatch latch = new CountDownLatch(5);

        new Thread(() -> {
            System.out.println("t2 start....");
            if (c.size() != 5) {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("t2 end ...");

        }, "t2").start();


        new Thread(() -> {
            System.out.println("ti start .... ");
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add" + i);

                latch.countDown();

                sleepOneSecond();
            }
        }, "t1").start();
    }
}
