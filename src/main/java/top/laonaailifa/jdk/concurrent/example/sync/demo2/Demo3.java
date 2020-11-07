package top.laonaailifa.jdk.concurrent.example.sync.demo2;

import java.util.concurrent.TimeUnit;

/**
 * 再不影响结果的情况下,sync锁定的区域越小越好.
 */
public class Demo3 {
    String s1 = "hello";
    String s2 = "hello";

    public void test1(){
        synchronized (s1){
            System.out.println("t1 start");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1 end");
        }
    }

    public void test2(){
        synchronized (s2){
            System.out.println("t1 start");
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1 end");
        }
    }

    public static void main(String[] args) {
        Demo3 demo = new Demo3();
        new Thread(demo::test1).start();
        new Thread(demo::test2).start();
    }
}
