package top.laonaailifa.jdk.concurrent.example.sync.demo9;

public class Demo {
    boolean  running = true;

    public void test() {
        System.out.println("test start...");
        while (running) {
//            String str = "zzz";
//            System.out.println(str.toString());
        }
        System.out.println("test end ...");
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        new Thread(demo::test, "t1").start();

        try {
            Thread.sleep(1000); //1 Second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        demo.running = false;
    }
}
