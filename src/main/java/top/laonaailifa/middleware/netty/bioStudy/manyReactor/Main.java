package top.laonaailifa.middleware.netty.bioStudy.manyReactor;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            new Reactor(9999).run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
