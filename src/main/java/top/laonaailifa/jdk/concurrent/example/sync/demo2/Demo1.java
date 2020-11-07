package top.laonaailifa.jdk.concurrent.example.sync.demo2;

import java.util.concurrent.TimeUnit;

/**
 * sync 遵循代码执行顺序.
 * 改变o指向的对象后,新的Obj对象未被锁定
 */
public class Demo1 {
    Object o = new Object();

    public void test() {
        synchronized (o) {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            }
        }
    }

    public static void main(String[] args) {
        Demo1 demo1 = new Demo1();
        new Thread(demo1::test, "t1").start();
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread t2 = new Thread(demo1::test, "t2");

        demo1.o = new Object();
        t2.start();
    }
}
