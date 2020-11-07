package top.laonaailifa.jdk.concurrent.example.sync_container.demo2;

import java.util.Vector;

public class SaleOfTickets3 {
    private static Vector<Integer> tickets = new Vector<>();

    static {
        for (int i = 0; i < 10000; i++) {
            tickets.add(i);
        }
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                while (true) {
                    synchronized (tickets) {
                        if (tickets.size() <= 0){
                            break;
                        }
                        System.out.println(Thread.currentThread().getName() + " sell ticket " + tickets.remove(0));
                    }
                }
            }).start();
        }
    }
}
