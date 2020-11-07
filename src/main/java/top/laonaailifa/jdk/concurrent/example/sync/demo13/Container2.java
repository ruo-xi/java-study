package top.laonaailifa.jdk.concurrent.example.sync.demo13;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * notify 不会释放锁
 */
public class Container2 {
    volatile List lists = new ArrayList();

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {
        Container2 c = new Container2();
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
