package top.laonaailifa.jdk.concurrent.example.pool;

import java.util.concurrent.*;

public class Demo6_Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<Integer> task = new FutureTask<>(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return 1000;
        });

        new Thread(task).start();

        System.out.println(task.get());

        ExecutorService s = Executors.newFixedThreadPool(5);
        Future<Integer> future = s.submit(() -> {
            TimeUnit.MILLISECONDS.sleep(500);
            return 1;
        });
        System.out.println(future.get());
        System.out.println(future.isDone());
        s.shutdownNow();
    }
}
