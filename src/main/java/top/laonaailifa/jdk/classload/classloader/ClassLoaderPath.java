package top.laonaailifa.jdk.classload.classloader;

import org.openjdk.jol.vm.VM;

import javax.annotation.processing.AbstractProcessor;

public class ClassLoaderPath {
    public static void main(String[] args) {
        System.out.println("BootClassLoader:");
        System.out.println(String.class.getClassLoader());
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());
        System.out.println("---------------------------------------------");

        System.out.println("PlatformClassLoader:");
        ClassLoader classLoader2 = ClassLoader.getSystemClassLoader().getParent();
        System.out.println(AbstractProcessor.class.getClassLoader());
        System.out.println(classLoader2);
//        classLoader2.resources();
        System.out.println("---------------------------------------------");

        System.out.println("AppClassLoader:");
        ClassLoader classLoader3 = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader3);
//        classLoader3.resources();
        System.out.println("---------------------------------------------");

//        System.out.println(System.getProperty("jdk.module.main"));
        System.out.println(Thread.currentThread().getContextClassLoader());

        try {
//            Thread.currentThread().getContextClassLoader().loadClass("javax.annotation.processing.AbstractProcessor");
            Thread.currentThread().getContextClassLoader().loadClass("java.time.chrono.AbstractChronology");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        Class.forName()




//        System.out.println(System.getProperty("jdk.boot.class.path.append"));
//        new String()

//        System.getProperties().forEach((o, o2) -> {
//            System.out.println(o + "  ====== " + o2);
//        });
    }
}
