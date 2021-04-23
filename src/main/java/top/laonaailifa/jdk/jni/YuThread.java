
package top.laonaailifa.jdk.jni;

/**
 * strat()-> start0()
 * <p>
 * private native void start0();
 */

/**
 * javac -h . YuThread.java
 *
 * gcc -fPIC -I /usr/lib/jvm/java-11-jdk/include/linux -I /usr/lib/jvm/java-11-jdk/include  -shared -o libYuThreadNative.so callback.c downloadfile.c
 *
 * export LD_LIBRARY_PATH=LD_LIBRARY_PATH:/home/yu/source/java-study/src/main/java/top/laonaailifa/jdk/java/threa
 */
public class YuThread {

    static {
        System.load("/home/yu/Github/java-study/src/main/java/top/laonaailifa/jdk/jni/libYuThreadNative.so");
    }

    public static void main(String[] args) {
        YuThread yuThread = new YuThread();
        yuThread.callback();
        System.out.println();
        System.out.println("------------------------------------");
        System.out.println();
        yuThread.start0();
    }

    public void run(){
        System.out.println("callback successfully");
    }

    private native void callback();

    private native void start0();
}


//    public static void main(String[] args) {
//        new Thread(() -> {
//            System.out.println("Hellow World");j
//        }).start();
//    }

