package top.laonaailifa.jdk.concurrent.example.sync.demo2;

import java.util.concurrent.TimeUnit;

/**
 * 字符串常量 如果value相等 则相当于同一个对象储存在常量池中.
 */
public class Demo2 {
    int count = 0;

    public synchronized void test1() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void test2() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this) {
            count++;
        }

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
