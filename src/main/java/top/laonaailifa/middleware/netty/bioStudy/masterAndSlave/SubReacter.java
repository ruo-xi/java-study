package top.laonaailifa.middleware.netty.bioStudy.masterAndSlave;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SubReacter implements Runnable {
    private final Selector selector;
    private final ServerSocketChannel ssc;
    private boolean restart = false;
    int num;

    public SubReacter(Selector selector, ServerSocketChannel ssc, int idx) {
        this.selector = selector;
        this.ssc = ssc;
        this.num = idx;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            System.out.println("waiting for restart");
            while (!Thread.interrupted() && !restart) {
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
                    dispatch((iterator.next()));
                    iterator.remove();
                }
            }
        }
    }

    private void dispatch(SelectionKey next) {
        Runnable r = (Runnable) next.attachment();
        if (r != null) {
            r.run();
        }
    }

    public void setRestart(boolean b) {
        this.restart = b;
    }
}
