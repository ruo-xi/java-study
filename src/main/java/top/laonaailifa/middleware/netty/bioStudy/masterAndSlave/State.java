package top.laonaailifa.middleware.netty.bioStudy.masterAndSlave;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ThreadPoolExecutor;

public interface State {
    void changeState(Handler h);

    void handle(Handler h, SelectionKey sk, SocketChannel sc,
                ThreadPoolExecutor pool) throws IOException;
}
