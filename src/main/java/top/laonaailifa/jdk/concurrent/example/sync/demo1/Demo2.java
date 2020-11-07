package top.laonaailifa.jdk.concurrent.example.sync.demo1;

public class Demo2 {
    private int count = 10;

    public void test() {
        /**
         * sync(this)相当于锁定当前实例
         */
        synchronized (this) {
            count++;
            System.out.println(Thread.currentThread().getName() + "count = " + count);
        }
    }
}
