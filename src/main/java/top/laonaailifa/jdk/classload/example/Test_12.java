package top.laonaailifa.jdk.classload.example;

/**
 * static 属性顺序赋值
 */
public class Test_12 {

    public static void main(String[] args) {
        Test_12_A obj = Test_12_A.getInstance();

        System.out.println(Test_12_A.val1);
        System.out.println(Test_12_A.val2);

    }
}

class Test_12_A {

    public static int val1; //0,0

    public static Test_12_A instance = new Test_12_A();//1,1

    Test_12_A() {
        val1++;
        val2++;
    }

    public static int val2 = 1;//1,1

    public static Test_12_A getInstance() {
        return instance;
    }
}