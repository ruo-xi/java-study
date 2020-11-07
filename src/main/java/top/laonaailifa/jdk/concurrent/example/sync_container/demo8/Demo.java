package top.laonaailifa.jdk.concurrent.example.sync_container.demo8;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ArrayBlockingQueue
 */
public class Demo {
    private static BlockingQueue<String> strings = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            strings.put("a" + i);
        }
        /**
         * Inserts the specified element into this queue if it is possible to do
         *      so immediately without violating capacity restrictions, returning
         *      {@code true} upon success and throwing an
         *      {@code IllegalStateException} if no space is currently available.
         *      When using a capacity-restricted queue, it is generally preferable to
         *      use {@link #offer(Object) offer}.
         */
        strings.add("aaaa");
        /**
         * Inserts the specified element into this queue, waiting if necessary
         * for space to become available.
         */
//		strings.put("aaaa");
//        System.out.println(strings.offer("aaaa"));
        /**
         * Inserts the specified element into this queue, waiting up to the
         * specified wait time if necessary for space to become available.
         */
//        strings.offer("aaaa",1, TimeUnit.SECONDS);
        System.out.println(strings);
    }
}
