package top.laonaailifa.jdk.concurrent.example.sync.demo10;

import java.util.ArrayList;
import java.util.List;

/**
 * volatile 不能保证原子性
 */
public class Demo {
    volatile int count = 0;

    public void test() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        List<Thread> threads = new ArrayList();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(demo::test, "thread-" + i));
        }

        threads.forEach((o) -> o.start());

        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(demo.count);
    }
}
