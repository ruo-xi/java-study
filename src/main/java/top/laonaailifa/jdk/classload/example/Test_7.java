package top.laonaailifa.jdk.classload.example;

import java.util.UUID;

/**
 * 因为uuid 后面不是常量 所以需要动态生成就需要加载类
 */
public class Test_7 {
    public static void main(String[] args) {
        System.out.println(Test_7_A.uuid);
    }
}

class Test_7_A {
    public static final String uuid = UUID.randomUUID().toString();

    static {
        System.out.println("Test_7_A Static Block");
    }
}
