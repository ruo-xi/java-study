package top.laonaailifa.jdk.classload.example;


/**
 * 因为str赋予的是字符串常量,且被final修饰
 * 所以会将str保存到Test_6的常量池
 * Test_6_A
 */
public class Test_6 {
    public static void main(String[] args) {
        System.out.println(Test_6_A.str);
    }
}

class Test_6_A {
    public static final String str = "A str";
    static {
        System.out.println("Test_6_a Static Block");
    }
}
