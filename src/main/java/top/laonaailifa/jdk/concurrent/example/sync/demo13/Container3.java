package top.laonaailifa.jdk.concurrent.example.sync.demo13;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Container3 {
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        Container3 c = new Container3();
        Object lock = new Object();

        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2 start....");
                if (c.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2 end ...");
                lock.notify();
            }
        }, "t2").start();
        new Thread(() -> {
            System.out.println("ti start .... ");
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add" + i);
                    if (c.size() == 5) {
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1").start();
    }
}
