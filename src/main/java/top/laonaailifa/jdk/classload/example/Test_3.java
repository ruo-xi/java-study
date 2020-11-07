package top.laonaailifa.jdk.classload.example;

/**
 * result:
 * A Static Block
 * B Static Block
 * B str
 *
 */


public class Test_3 {
    public static void main(String[] args) {
        System.out.println(Test_3_B.str);
    }
}


class Test_3_A {
    public static String str = "A str";

    static {
        System.out.println("A Static Block");
    }
}

class Test_3_B extends Test_3_A {
    public static String str = "B str";

    static {
        System.out.println("B Static Block");
    }
}