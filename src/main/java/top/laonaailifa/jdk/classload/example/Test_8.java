package top.laonaailifa.jdk.classload.example;

/**
 *调用子类 初始化父类
 */
public class Test_8 {
    static {
        System.out.println("Test_8 Static Block");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> aClass = Class.forName("top.laonaailifa.jdk.classload.example.Test_1_B");

    }
}
