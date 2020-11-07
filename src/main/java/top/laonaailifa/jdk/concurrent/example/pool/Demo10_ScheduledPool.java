package top.laonaailifa.jdk.concurrent.example.pool;

import java.sql.Time;
import java.time.Clock;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Demo10_ScheduledPool {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(4);

        service.scheduleAtFixedRate(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " time : " + ZonedDateTime.now());
        }, 0, 500, TimeUnit.MILLISECONDS);
    }
}
