package top.laonaailifa.jdk.classload.example;

public class Test_11 {

    public static void main(String[] args) {
        Test_11_A obj = Test_11_A.getInstance();

        System.out.println(Test_11_A.val1);
        System.out.println(Test_11_A.val2);
    }
}

class Test_11_A {

    public static int val1;//0,0
    public static int val2 = 1;//0,1

    public static Test_11_A instance = new Test_11_A();//1,2

    Test_11_A() {
        val1++;
        val2++;
    }

    public static Test_11_A getInstance() {
        return instance;
    }
}