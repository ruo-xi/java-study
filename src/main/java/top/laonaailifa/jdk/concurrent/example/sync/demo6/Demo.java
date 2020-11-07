package top.laonaailifa.jdk.concurrent.example.sync.demo6;
import java.util.concurrent.TimeUnit;

/**
 * 同步方法可以重入
 */
public class Demo {
    synchronized void test1(){
        System.out.println("test1 start");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tset2();
        System.out.println("test1 end");
    }

    synchronized void tset2() {
        System.out.println("test2 start");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("test2 end");
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.test1();
    }
}
