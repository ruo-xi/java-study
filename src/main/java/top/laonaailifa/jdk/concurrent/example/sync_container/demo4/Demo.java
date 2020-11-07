package top.laonaailifa.jdk.concurrent.example.sync_container.demo4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Collections
 */
public class Demo {
    public static void main(String[] args) {
        ArrayList<Object> arrayList = new ArrayList<>();
        List<Object> synchronizedList = Collections.synchronizedList(arrayList);
    }
}
