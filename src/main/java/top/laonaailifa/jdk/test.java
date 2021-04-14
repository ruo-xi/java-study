package top.laonaailifa.jdk;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class test {
    public static void main(String[] args) {
//        int c = 1000;
//        int d = 1000;
//
//        Integer a = Integer.valueOf(1000);
//        Integer b = Integer.valueOf(1000);
//
//        Integer e = 1000;
//        Integer f = 1000;
//        System.out.println(c == d);
//        System.out.println(a == b);
//        System.out.println(e == f);

        String b = new String("abc");
        String a = "abc";
        String c = "abc" + "def";
        String d = new String(a + b + c);
        String e = new String("gg" + "gg");

        System.out.println(a == b.intern());
        System.out.println(a == b);
        System.out.println(c == c.intern());
        System.out.println(d == d.intern());
        System.out.println(e == e.intern());

//        ReferenceQueue<String> q = new ReferenceQueue<>();
//        PhantomReference<String> p = new PhantomReference<>(a, q);
//        a = null;
//        System.gc();
//        while (q.poll() == null){
//

//        System.out.println(q.poll());
    }
}
