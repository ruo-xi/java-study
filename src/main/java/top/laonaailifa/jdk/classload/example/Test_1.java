package top.laonaailifa.jdk.classload.example;


/**
 * result:
 *  A static Block
 *  A str
 *
 * 因为str是静态对象并且赋予的值是常量
 * 所以不需要加载Test_1_A
 */
public class Test_1 {
    public static void main(String[] args) {
        System.out.println(Test_1_B.str);
        while (true);
    }
}

class Test_1_A {
    public static String str = "A str";

    static {
        System.out.println("A static Block");
    }
}

class Test_1_B extends Test_1_A {
    static {
        System.out.println("B static Block");
    }
}