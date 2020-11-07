package top.laonaailifa.jdk.concurrent.example.sync_container.demo1;

import java.util.concurrent.TimeUnit;

public class Demo2 {
    private static ThreadLocal<User> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadLocal.set(new User());
            System.out.println(threadLocal.get());
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            threadLocal.set(new User());
//            threadLocal.remove();
            System.out.println(threadLocal.get());
        }).start();
    }
}
