package top.laonaailifa.jdk.concurrent.example.sync_container.demo3;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Demo {
    public static void main(String[] args) {
//        List<String> list = new Vector<>();
        List<String> list = new CopyOnWriteArrayList<>();
        Random random = new Random();
        Thread[] threads = new Thread[100];

        for (int i = 0; i < threads.length; i++) {
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        list.add("A" + random.nextInt(10000));
                    }
                }
            };
            threads[i] = new Thread(task);
        }
        run(threads);
        System.out.println(list.size());
    }

    private static void run(Thread[] threads) {
        long start = System.currentTimeMillis();

        Arrays.asList(threads).forEach(thread -> thread.start());
        Arrays.asList(threads).forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
