package top.laonaailifa.jdk.concurrent.example.pool;

import java.util.concurrent.Executors;

public class Demo4_Ececutors  {
    public static void main(String[] args) {
        Executors.defaultThreadFactory();
        Executors.newCachedThreadPool();
        Executors.newFixedThreadPool(1);
        Executors.newScheduledThreadPool(1);
        Executors.newSingleThreadExecutor();
        Executors.newSingleThreadScheduledExecutor();
        Executors.newWorkStealingPool();
    }
}
