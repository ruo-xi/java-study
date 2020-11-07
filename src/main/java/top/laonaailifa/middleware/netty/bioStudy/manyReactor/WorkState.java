package top.laonaailifa.middleware.netty.bioStudy.manyReactor;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ThreadPoolExecutor;

public class WorkState implements State, Runnable {
//    Handler h;
//    private String command;

//    public WorkState() {
//    }

//    public WorkState(String s, Handler h) {
//        command = s;
//    }
//
    @Override
    public void changeState(Handler h) {
        h.setState(new WriteState());
    }

    @Override
    public void handle(Handler h, SelectionKey sk, SocketChannel sc, ThreadPoolExecutor pool) throws IOException {
//        new Thread(this).start();
    }


    @Override
    public void run() {
//        process();
    }
//
//    private void process(Handler h, String str) {
//        h.setState(new WriteState());
//    }
}
