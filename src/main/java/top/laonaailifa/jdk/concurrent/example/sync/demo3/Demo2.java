package top.laonaailifa.jdk.concurrent.example.sync.demo3;

public class Demo2 implements Runnable {

    private int count = 10;

    @Override
    public /*synchronized*/ void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + "cunt =" + count);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Demo2 demo = new Demo2();
            new Thread(demo, "THREAD" + i).start();
        }
    }
}
