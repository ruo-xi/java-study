package top.laonaailifa.jdk.classload.example;

/**
 * result:
 * A Static Block
 * B Static Block
 * B str
 *
 * 因为Test_A.str所以Test_A需要加载
 * 并且父类先初始化
 */
public class Test_2 {
    public static void main(String[] args) {
        System.out.println(Test_2_B.str);
    }
}

class Test_2_A {

    static {
        System.out.println("A Static Block");
    }
}

class Test_2_B extends Test_2_A {
    public static String str = "B str";

    static {
        System.out.println("B Static Block");
    }
}


