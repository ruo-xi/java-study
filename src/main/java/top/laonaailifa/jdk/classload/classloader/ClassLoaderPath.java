package top.laonaailifa.jdk.classload.classloader;

public class ClassLoaderPath {
    public static void main(String[] args) {
        System.out.println("BootClassLoader:");
        System.out.println("---------------------------------------------");
        System.out.println("PlatformClassLoader:");
        ClassLoader classLoader2 = ClassLoader.getSystemClassLoader().getParent();
        System.out.println(classLoader2);
//        classLoader2.resources()
        System.out.println("---------------------------------------------");
        System.out.println("AppClassLoader:");
        ClassLoader classLoader3 = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader3);
//        classLoader3.resources()
        System.out.println("---------------------------------------------");

        System.out.println(System.getProperty("jdk.module.main"));
        System.out.println(Thread.currentThread().getContextClassLoader());

//        new String()
    }
}
