package top.laonaailifa.jdk.concurrent.example.sync_container.demo5;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Demo {
    public static void main(String[] args) {
        Queue<String> strings = new ConcurrentLinkedDeque<>();

        for (int i = 0; i < 10; i++) {
            /**
             * Inserts the specified element at the tail of this queue.
             */
            strings.offer("a" + i);//add to tail
        }
        System.out.println(strings);

        System.out.println(strings.size());

        /**
         * 检索
         * Retrieves and removes the head of this queue, or returns null if this queue is empty.
         */
        System.out.println(strings.poll());
        /**
         * Returns the number of elements in this queue.
         */
        System.out.println(strings.size());

        /**
         * Retrieves, but does not remove, the head of this queue, or returns null if this queue is empty.
         */
        System.out.println(strings.peek());
        System.out.println(strings.size());
    }
}
