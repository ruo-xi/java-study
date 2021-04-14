package top.laonaailifa;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.StringConcatFactory;

public class test {

    public int a = 0;

    public static void main(String[] args) {
        String s = "zz" + "zl";
        String a = "hh";
        String z = "aa";
        String b = s + a + z;
//        StringConcatFactory.makeConcatWithConstants(MethodHandles.lookup(),);

        test test = new test();
        test.sayHello();
        test.hashCode();
        Integer num = 11;
        System.out.println(String.class.getClassLoader());
        System.out.println(Math.class.getClassLoader());
//        System.out.println( Thread.currentThread().getContextClassLoader().getParent()));


        top.laonaailifa.test test1 = new test();
        A a1 = new A();
        System.out.println(test1.a);
        System.out.println(a1.a);
        System.out.println((((test) a1).a));

        Integer i1 = new Integer(1);
        Integer i2 = new Integer(2);

        i1 = i1 ^ i2;
        i2 = i1 ^ i2;
        i1 = i1 ^ i2;

        System.out.println(i1);
        System.out.println(i2);

    }

    public void sayHello() {

    }
}

class A extends test {
    public int a = 1;
}
