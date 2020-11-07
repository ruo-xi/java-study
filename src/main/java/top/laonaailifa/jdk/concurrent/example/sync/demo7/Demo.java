package top.laonaailifa.jdk.concurrent.example.sync.demo7;

import java.util.concurrent.TimeUnit;

/**
 * 重入的另一种情况
 */
public class Demo {
    synchronized void test() {
        System.out.println("strat....");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end....");
    }

    public static void main(String[] args) {
        new Demo2().test();
    }

}

class Demo2 extends Demo {
    @Override
    synchronized void test() {
        System.out.println("demo2 start....");
        super.test();
        System.out.println("demo2 end ....");
    }
}
