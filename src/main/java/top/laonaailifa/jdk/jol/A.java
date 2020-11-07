package top.laonaailifa.jdk.jol;

public class A {
    int i = 0;

    public synchronized void parse() {
        i++;
    }

    public static void main(String[] args) {
        A a = new A();
        synchronized (a){

        }
    }
}
