package top.laonaailifa.middleware.netty.bioStudy.masterAndSlave;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ThreadPoolExecutor;

public class ReadState implements State {

    SelectionKey sk;

    @Override
    public void changeState(Handler h) {
        h.setState(new WorkState());
    }

    @Override
    public void handle(Handler h, SelectionKey sk, SocketChannel sc, ThreadPoolExecutor pool) throws IOException {
        this.sk = sk;
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int num = sc.read(buffer);
        if (num == -1) {
            System.out.println(sc.getRemoteAddress().toString() + "connection closed");
            h.closeChannel();
            return;
        }
        String str = new String(buffer.array());
        if ((str != null) && !str.equals(" ")) {
            h.setState(new WorkState());
            pool.execute(new WorkerThread(h, str));
//            h.state.handle(h, sk, sc, pool);
            System.out.println(sc.socket().getRemoteSocketAddress().toString() + " > " + str);
        }
    }

    private void process(Handler h, String str) {
        h.setState(new WriteState());
        this.sk.interestOps(SelectionKey.OP_WRITE);
        this.sk.selector().wakeup();
    }

    class WorkerThread implements Runnable {
        Handler h;

        String str;

        public WorkerThread(Handler h, String str) {
            this.h = h;
            this.str = str;
        }

        @Override
        public void run() {
            process(h, str);
        }

    }

}
