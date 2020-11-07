package top.laonaailifa.jdk.concurrent.juc;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName RWLock_Example
 * @Description TODO
 * @Author yu
 * @Date 10/26/20 6:59 PM
 * @Version 1.0
 **/
public class RWLock_Example {
    ReadWriteLock lock = new ReentrantReadWriteLock();
    private Integer data = 0;

    public void get() {
        if (1 != 3){

        }
        lock.readLock().lock();
        System.out.println(Thread.currentThread().getName() + "ready to read");
        try {
            Thread.sleep(((long) (Math.random() * 1000)));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("read data -> " + data);
        lock.readLock().unlock();
    }

    public void put(Integer integer) {
        lock.writeLock().lock();
        System.out.println(Thread.currentThread().getName() + "ready to write");
        try {
            Thread.sleep(((long) (Math.random() * 1000)));
            data = integer;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("write data -> " + integer);
        lock.writeLock().unlock();
    }

    public static void main(String[] args) {
        RWLock_Example rwLock_example = new RWLock_Example();
        for (int i = 0; i < 10; i++) {
            new Thread(rwLock_example::get).start();
            new Thread(() -> rwLock_example.put(new Random().nextInt(100))).start();
        }
    }
}
