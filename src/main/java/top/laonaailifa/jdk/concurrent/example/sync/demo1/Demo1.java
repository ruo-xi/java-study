package top.laonaailifa.jdk.concurrent.example.sync.demo1;

/**
 * sync关键字 锁定的是对象不是代码块.
 * 对象有两种:1.类的实例 2.类的class的实例
 */
public class Demo1 {
    private int count = 10;
    private Object obj = new Object();

    public void test() {
        synchronized (obj) {
            count++;
            System.out.println(Thread.currentThread().getName() + "count = " + count);
        }
    }
}
