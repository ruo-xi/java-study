package top.laonaailifa.jdk.concurrent.example.sync.demo5;

import java.util.concurrent.TimeUnit;

/**
 * 结果不一定 可能是0 也可能是100
 */
public class Demo {
    String name;
    double balance;

    public synchronized void set(String name, double balance) {
        this.name = name;
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    public static void main(String[] args) {
        Demo demo = new Demo();

        new Thread(() -> demo.set("小明", 100.0)).start();
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(demo.getBalance());
    }
}
