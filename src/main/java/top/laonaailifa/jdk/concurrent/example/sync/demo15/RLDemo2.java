package top.laonaailifa.jdk.concurrent.example.sync.demo15;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RLDemo2 {
    Lock lock = new ReentrantLock();

    public void test1() {
        try {
            lock.lock();
            for (int i = 0; i < 3; i++) {
                System.out.println(i);
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void test2() {
        boolean locked = false;

        try {
            locked = lock.tryLock(3, TimeUnit.SECONDS);
            System.out.println("test2 ......" + locked);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (locked){
                System.out.println("test2 end");
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        RLDemo2 rlDemo2 = new RLDemo2();
        new Thread(rlDemo2::test1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(rlDemo2::test2).start();
    }
}
