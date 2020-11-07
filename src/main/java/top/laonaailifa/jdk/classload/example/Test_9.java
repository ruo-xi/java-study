package top.laonaailifa.jdk.classload.example;

public class Test_9 {
    public static void main(String[] args) {
        System.out.printf(new Test_9_B().str);
    }
}

class Test_9_A {
    static {
        System.out.println("A Static Block");
    }
}

class Test_9_B extends Test_9_A {

    public String str = "A str";
    static {
        System.out.println("B Static Block");
    }
}
