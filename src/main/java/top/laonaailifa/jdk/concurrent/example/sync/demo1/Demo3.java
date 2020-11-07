package top.laonaailifa.jdk.concurrent.example.sync.demo1;

public class Demo3 {
    private int count = 10;

    /**
     *     相当于synchronized(this)
     */
    public synchronized void test() {
        count++;
        System.out.println(Thread.currentThread().getName() + "count = " + count);
    }
}
