package top.laonaailifa.jdk.jol;

import org.openjdk.jol.info.ClassLayout;

import java.util.ArrayList;
import java.util.List;

public class JolExample7 {
    public static void main(String[] args) throws InterruptedException {
        List<A> list = new ArrayList<>();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 21; i++) {
                A a = new A();
                synchronized (a) {
                    list.add(a);
//                    System.out.println(ClassLayout.parseInstance(list.get(i)).toPrintable());
                    if (i == 18 || i == 19) {
                        System.out.println("------------------------------------------------------------- " + i + " ----------------------------------------------------------------------------------");
                        System.out.println(ClassLayout.parseInstance(list.get(i)).toPrintable());
                        System.out.println(ClassLayout.parseInstance(A.class).toPrintable());
                    }
                }
            }
        });
        t1.start();
        t1.join();

        new Thread(() -> {
            for (;;){

            }
        }).start();

        Thread.sleep(1000);
        System.out.println("before next thread start");
        new Thread(() -> {
            int k = 0;
            for (A a : list) {
                synchronized (a) {
//                    System.out.println(ClassLayout.parseInstance(list.get(k)).toPrintable());
                    if (k == 18 || k == 19) {
                        System.out.println("------------------------------------------------------------- " + k + " ----------------------------------------------------------------------------------");
                        System.out.println(ClassLayout.parseInstance(list.get(k)).toPrintable());
                        System.out.println(ClassLayout.parseInstance(A.class).toPrintable());
                    }
                }
                k++;
            }
        }).start();
    }
}
