package top.laonaailifa.middleware.netty.bioStudy.masterAndSlave;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Reactor implements Runnable {

    ServerSocketChannel serverSocketChannel;

    Selector selector;

    public Reactor(int port) {
        try {
            serverSocketChannel = ServerSocketChannel.open();
            selector = Selector.open();

            InetSocketAddress inetSocketAddress = new InetSocketAddress(port);
            serverSocketChannel.socket().bind(inetSocketAddress);
            serverSocketChannel.configureBlocking(false);
            SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            selectionKey.attach(new Acceptor(serverSocketChannel));

            System.out.println("start listen at port :" + inetSocketAddress.getPort());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                if (selector.select() == 0) {
                    continue;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                dispatch(iterator.next());
                iterator.remove();
            }
        }
    }

    private void dispatch(SelectionKey selectionKey) {
        Runnable r = (Runnable) selectionKey.attachment();
        if (r != null) {
            r.run();
        }
    }
}
