package top.laonaailifa.jdk.concurrent.example.sync.demo15;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 公平锁
 */
public class RLDemo4 {
    Lock lock = new ReentrantLock(true);

    public void run() {
        for (int i = 0; i < 100; i++) {
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "    get the lock");
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        RLDemo4 rlDemo4 = new RLDemo4();
        new Thread(rlDemo4::run).start();
        new Thread(rlDemo4::run).start();
    }
}
