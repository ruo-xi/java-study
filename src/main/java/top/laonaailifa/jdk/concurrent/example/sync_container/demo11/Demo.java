package top.laonaailifa.jdk.concurrent.example.sync_container.demo11;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

/**
 * synchronizedQueue 同步队列
 * 同步队列是容量为0，也就是来的东西必须给消费掉.
 * 首先启动一个消费者，调用add方法，他报错了
 * 只能调用put，意思就是阻塞等待消费者消费。put里面其实用的是transfer，任何东西必须消费，不能往容器里面扔。
 */
public class Demo {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> strings = new SynchronousQueue<>();

        new Thread(() -> {
            try {
                while (true) {
                    System.out.println(strings.take());
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        strings.put("aaa");
//        TimeUnit.SECONDS.sleep(1);
        /**
         * put 会阻塞
         */
        strings.put("aaa");
        strings.put("aaa");
        strings.put("aaa");
        strings.put("aaa");
        strings.put("aaa");
        System.out.println(strings.size());

    }
}
