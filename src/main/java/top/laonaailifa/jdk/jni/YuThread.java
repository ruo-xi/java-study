
package top.laonaailifa.jdk.jni;

/**
 * strat()-> start0()
 * <p>
 * private native void start0();
 */


public class YuThread {

    static {
        System.loadLibrary("YuThreadNative");
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

