package top.laonaailifa.middleware.netty.bioStudy.manyReactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ThreadPoolExecutor;

public class WriteState implements State {
    @Override
    public void changeState(Handler h) {
        h.setState(new ReadState());
    }

    @Override
    public void handle(Handler h, SelectionKey sk, SocketChannel sc, ThreadPoolExecutor pool) throws IOException {
        String str = "Your message has sent to" + sc.getRemoteAddress().toString() + "\r\n";
        ByteBuffer buf = ByteBuffer.wrap(str.getBytes());
        while (buf.hasRemaining()) {
            sc.write(buf);
        }
        h.setState(new ReadState());
        sk.interestOps(SelectionKey.OP_READ);
        sk.selector().wakeup();
    }
}
