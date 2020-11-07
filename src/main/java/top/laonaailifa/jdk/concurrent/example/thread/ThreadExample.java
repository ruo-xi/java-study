package top.laonaailifa.jdk.concurrent.example.thread;

public class ThreadExample {
    static Thread thread = null;
    public static boolean runing = true;

    public static void main(String[] args) {
       while (true){
           new Thread(() -> {
               System.out.println("not main");
           });
           System.out.println("main");
           try {
               Thread.sleep(1000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }
}
