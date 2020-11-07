package top.laonaailifa.jdk.concurrent.example.sync_container.demo1;

import java.util.concurrent.TimeUnit;

public class Demo1 {
    volatile static String name = "A";

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            name = "B";
        }).start();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name);
        }).start();
    }
}
