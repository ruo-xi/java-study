package top.laonaailifa.jdk.concurrent.example.sync.demo1;

public class Demo4 {
    private static int count = 10;

    /**
     * 静态方法中 sync 锁定的是类的class对象
     */
    public synchronized static void test() {
        count--;
        System.out.println(Thread.currentThread().getName() + "count = " + count);
    }

    public static void test2() {
        synchronized (Demo4.class) {
            count++;
        }
    }
}
