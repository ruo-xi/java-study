package top.laonaailifa.jdk.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * 线程死亡后,重新创建的第一个线程地址相同,
 * 所以两个线程拿同一把锁,并且第一个线程死亡,两个线程之间没有新的线程创建,会依旧使用偏向锁.
 */
public class JolExample6 {
    public static void main(String[] args) throws InterruptedException {
        A a = new A();
//        a.hashCode();
        Thread t1 = new Thread(() -> {
            synchronized (a) {
                System.out.println(ClassLayout.parseInstance(a).toPrintable());
            }
//            while (true){
//
//            }
        });
        t1.start();
        t1.join();

        new Thread(() -> {
            while (true){

            }
        }).start();

        System.out.println(ClassLayout.parseInstance(a).toPrintable());
        Thread t2 = new Thread(() -> {
            synchronized (a) {
                System.out.println(ClassLayout.parseInstance(a).toPrintable());
//                try {
//                    a.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println(ClassLayout.parseInstance(a).toPrintable());
            }
        });
        t2.start();
    }
}
