package top.laonaailifa.jdk.concurrent.example.sync_container.demo2;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class SaleOfTickets4 {
    private static Queue<Integer> tickets = new ConcurrentLinkedDeque<>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add(i);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    Integer poll = tickets.poll();
                    if (poll == null) {
                        break;
                    }
                    System.out.println("sell ticket : " + poll);
                }
            }).start();
        }
    }
}
