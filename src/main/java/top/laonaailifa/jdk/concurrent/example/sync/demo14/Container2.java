package top.laonaailifa.jdk.concurrent.example.sync.demo14;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Container2<T> {
    private final LinkedList<T> list = new LinkedList<>();
    private final int MAX = 10;

    private Lock lock = new ReentrantLock();
    private Condition producer = lock.newCondition();
    private Condition consumer = lock.newCondition();


    public  void put(T t) {
        lock.lock();
        while (list.size() == MAX) {
            try {
                producer.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        list.add(t);
        consumer.signalAll();
        lock.unlock();
    }

    public  T get() {
        lock.lock();
        T t = null;
        while (list.size() == 0) {
            try {
                consumer.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = list.removeFirst();
        producer.signalAll();
        lock.unlock();
        return t;
    }

    public static void main(String[] args) {
        Container2<String> container = new Container2<>();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                for (int j = 0; j < 5; j++) {
                    System.out.println(container.get());
                }
            }).start();
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                for (int j = 0; j < 25; j++) {
                    container.put(Thread.currentThread().getName() + " " + j);
                }
            }, "p" + i).start();
        }
    }
}
