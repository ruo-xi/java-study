package top.laonaailifa.middleware.netty.bioStudy.manyReactor;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Acceptor implements Runnable {

    private final ServerSocketChannel serverSocketChannel;
    private final Selector selector;

    public Acceptor(Selector selector, ServerSocketChannel serverSocketChannel) {
        this.serverSocketChannel = serverSocketChannel;
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            SocketChannel socketChannel = serverSocketChannel.accept();
            System.out.println(socketChannel.socket().getRemoteSocketAddress().toString() + "is connected");
            if (socketChannel != null) {
                socketChannel.configureBlocking(false);
                SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_READ);
                selector.wakeup();
                selectionKey.attach(new Handler(selectionKey, socketChannel));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
