package top.laonaailifa.jdk.optimization;

public class DeadLock {
    static Object Lock1 = new Object();
    static Object Lock2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (DeadLock.Lock1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (DeadLock.Lock2) {

                }
            }
        }).start();


        new Thread(() -> {
            synchronized (DeadLock.Lock2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (DeadLock.Lock1) {

                }
            }
        }).start();
    }
}
