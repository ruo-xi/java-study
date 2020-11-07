package top.laonaailifa.jdk.concurrent.example.sync_container.demo2;

import java.util.ArrayList;
import java.util.List;

/**
 * 有10000张火车票,同时有10个窗口对外售票
 * 请写一个模拟程序
 */
public class SaleOfTickets1 {
    private static List<Integer> tickets = new ArrayList();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add(i);
        }
    }

    /**
     * 卖重
     * 超卖
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (tickets.size() > 0){
                    System.out.println(Thread.currentThread().getName()+" sell ticket " + tickets.remove(0));
                }
            }).start();
        }
    }
}
