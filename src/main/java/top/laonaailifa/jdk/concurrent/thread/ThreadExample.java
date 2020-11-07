package top.laonaailifa.jdk.concurrent.thread;

public class ThreadExample {
    int x = 20;
    boolean f = false;

    public void t1() {
        x = 40;
        f = true;
    }

    public void t2() {
        if (f) {
            System.out.println(x);
        }
    }

    public static void main(String[] args) {

        ThreadExample example = new ThreadExample();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(example::t1).start();
        new Thread(example::t2).start();
//    }
    }
}
