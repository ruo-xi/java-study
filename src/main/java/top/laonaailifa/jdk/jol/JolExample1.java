package top.laonaailifa.jdk.jol;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

public class JolExample1 {
//    int a;
    public static void main(String[] args) {
        System.out.println(VM.current().details());
        System.out.println("-----------------------------");
        System.out.println(ClassLayout.parseInstance(new JolExample1()).toPrintable());
    }
}
