package top.laonaailifa.jdk.concurrent.example.sync.demo14;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

public class Container1<T> {
    private final LinkedList<T> list = new LinkedList<>();
    private final int MAX = 10;

    public synchronized void put(T t) {
        while (list.size() == MAX) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        list.add(t);
        this.notifyAll();
    }

    public synchronized T get() {
        T t = null;
        while (list.size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        t = list.removeFirst();
        this.notifyAll();
        return t;
    }

    public static void main(String[] args) {
        Container1<String> container = new Container1<>();
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
