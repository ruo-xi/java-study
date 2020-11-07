package top.laonaailifa.middleware.netty.bioStudy.masterAndSlave;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Handler implements Runnable {

    private SelectionKey sk;
    private SocketChannel sc;
    private static final int THREAD_COUNTING = 10;
    private static ThreadPoolExecutor pool = new ThreadPoolExecutor(
            THREAD_COUNTING, THREAD_COUNTING, 10,
            TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
    State state;

    public Handler(SelectionKey selectionKey, SocketChannel socketChannel) {
        this.sk = selectionKey;
        this.sc = socketChannel;
    }


    @Override
    public void run() {
        try {
            state.handle(this, sk, sc, pool);
        } catch (IOException e) {
            try {
                System.out.println(sc.getRemoteAddress().toString()+"has closed");
                closeChannel();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private void send() throws IOException {

    }



    public void setState(State state) {
        this.state = state;
    }

    public void closeChannel() {
        try {
            sk.cancel();
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void process(String str) {
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
}
