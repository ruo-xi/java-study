package top.laonaailifa.jdk.optimization;

import java.util.ArrayList;

// -Xms5M -Xmx5M -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemory -XX:HeapDumpPath=~/dump.file
public class Oom {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        int i = 0;
        for (; ; ) {
            users.add(new User(String.valueOf(i), i++));
        }
    }
}


class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
