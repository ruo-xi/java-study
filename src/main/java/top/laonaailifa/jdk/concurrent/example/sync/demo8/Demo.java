package top.laonaailifa.jdk.concurrent.example.sync.demo8;

import java.util.concurrent.TimeUnit;

/**
 * printStackTrace()会帮助释放锁
 */
public class Demo {
    int count = 0;

    synchronized void test() {
        System.out.println(Thread.currentThread().getName() + "start...");
        while (true) {
            count++;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
            try {
                TimeUnit.MILLISECONDS.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 5) {
//                try {
                    int i = 1 / 0;
//                } catch (Exception e) {
//                    System.out.println("error");
//                }
            }
        }
    }

    public static void main(String[] args) {
        Demo demo = new Demo();

        new Thread(demo::test, "t1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(demo::test, "t2").start();
    }
}
