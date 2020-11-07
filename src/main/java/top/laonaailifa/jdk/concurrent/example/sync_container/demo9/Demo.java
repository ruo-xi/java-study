package top.laonaailifa.jdk.concurrent.example.sync_container.demo9;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueue 延迟队列
 */
public class Demo {
    private static BlockingQueue<MyTask> tasks = new DelayQueue();

    private static class MyTask implements Delayed {
        long runningTime;

        public MyTask(long runningTime) {
            this.runningTime = runningTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(runningTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            if (this.getDelay(TimeUnit.MILLISECONDS) < o.getDelay(TimeUnit.MILLISECONDS)) {
                return -1;
            } else if (this.getDelay(TimeUnit.MILLISECONDS) > o.getDelay(TimeUnit.MILLISECONDS)) {
                return 1;
            } else {
                return 0;
            }
        }

        @Override
        public String toString() {
            return "" + runningTime;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        long now = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            tasks.put(new MyTask(now + i * 1000));
        }
        System.out.println(tasks);

        for (int i = 0; i < 5; i++) {
            System.out.println(tasks.take());
        }
    }
}
