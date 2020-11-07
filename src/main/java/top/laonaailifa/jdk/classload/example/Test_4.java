package top.laonaailifa.jdk.classload.example;


/**
 * 什么都不会输入
 * 因为Object数组底层是 ObjArrayKlass存储 与是什么对象无关所以不需要加载对象
 * 基本类型数组是 TypeArrayKlass
 */
public class Test_4 {
    public static void main(String[] args) {
        Test_4 arrs[] = new Test_4[1];
    }
}

class Test_4_A{
    static {
        System.out.println("Test_4_A static block");
    }
}
