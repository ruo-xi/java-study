package top.laonaailifa.jdk.concurrent.example.sync_container.demo2;

import java.util.Vector;
import java.util.concurrent.TimeUnit;

public class SaleOfTickets2 {
    private static Vector<Integer> tickets = new Vector<>();
    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add(i);
        }
    }

    /**
     * 超卖
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0){
                    try {
                        TimeUnit.MILLISECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+" sell ticket " + tickets.remove(0));
                    System.out.println(tickets.size());
                }
            }).start();
        }
    }
}
