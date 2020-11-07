package top.laonaailifa.jdk.jol;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class JolExample3 {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(ClassLayout.parseInstance(A.class).toPrintable());
        A a = new A();
//        Thread.sleep(10000);
//        a.hashCode();
        System.out.println(ClassLayout.parseInstance(A.class).toPrintable());

        System.out.println("before lock");
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
        synchronized (a){
            System.out.println("locking");
            System.out.println(ClassLayout.parseInstance(a).toPrintable());
        }
        System.out.println("after lcok");
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
    }
}
