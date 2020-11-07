package top.laonaailifa.jdk.concurrent.example.sync.demo4;

public class Demo {
    public synchronized void test1() {
        System.out.println(Thread.currentThread().getName() + "test1 start");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "test1 end");
    }

    public void test2() {
        System.out.println(Thread.currentThread().getName() + "test2 start");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "test2 end");
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        new Thread(demo::test1,"test1").start();
        new Thread(demo::test2,"test2").start();
    }
}
