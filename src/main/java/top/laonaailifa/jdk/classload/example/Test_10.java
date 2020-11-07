package top.laonaailifa.jdk.classload.example;

/**
 * 不需要加载Test_10_A 静态代码块自然也不会执行
 */
public class Test_10 {
    public static void main(String[] args) {
        System.out.printf(Test_10_B.str);
    }
}

class Test_10_A {
    public static String str = "A str";

    static {
        System.out.println("A Static Block");
    }
}

class Test_10_B extends Test_10_A {
    static {
        str += "#11";
        System.out.println("B Static Block");
    }
}