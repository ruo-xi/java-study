package top.laonaailifa.jdk.jol;

import org.openjdk.jol.info.ClassLayout;

public class JolExample2 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        A a = new A();
        System.out.println(ClassLayout.parseInstance(A.class).toPrintable());

        System.out.println("before hash");
        System.out.println(ClassLayout.parseInstance(a).toPrintable());

        System.out.println("jvm--------0x"+Integer.toHexString(a.hashCode()));
//        HashUtil.getHash(a);
//        System.out.println();
        System.out.println("after hash");
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
    }
}
