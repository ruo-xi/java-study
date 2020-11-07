package top.laonaailifa.jdk.concurrent.example.sync.demo15;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class RLDemo3 {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Thread t1 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("t1 start");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("t1 end");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        });

        t1.start();
        Thread t2 = new Thread(() -> {
            boolean locked = false;
            try {
//                lock.lock();
                lock.lockInterruptibly();
                System.out.println("t2 start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t2 end");
                locked = false;
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            } finally {
                if (locked) {
                    lock.unlock();
                }
            }
        });
        t2.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.interrupt();


    }
}
