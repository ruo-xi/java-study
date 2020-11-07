package top.laonaailifa.middleware.netty.bioStudy.masterAndSlave;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class Acceptor implements Runnable {

    ServerSocketChannel ssc;
    private final int cores = Runtime.getRuntime().availableProcessors();
    private final Selector[] selectors = new Selector[cores];
    private SubReacter[] s = new SubReacter[cores];
    private int selIdx = 0;
    private Thread[] t = new Thread[cores];

    public Acceptor(ServerSocketChannel serverSocketChannel) throws IOException {
        this.ssc = serverSocketChannel;
        for (int i = 0; i < cores; i++) {
            selectors[i] = Selector.open();
            s[i] = new SubReacter(selectors[i], ssc, i);
            t[i] = new Thread(s[i]);
            t[i].start();
        }
    }

    @Override
    public void run() {
        try {
            SocketChannel sc = ssc.accept();
            System.out.println(sc.getRemoteAddress().toString() + "is conntected");
            if (sc != null) {
                sc.configureBlocking(false);
                s[selIdx].setRestart(true);
                selectors[selIdx].wakeup();
                SelectionKey sk = sc.register(selectors[selIdx], SelectionKey.OP_READ);
                selectors[selIdx].wakeup();
                s[selIdx].setRestart(false);
                sk.attach(new Handler(sk, sc));
                if (++selIdx == selectors.length) {
                    selIdx = 0;
                }
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}
