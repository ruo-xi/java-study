package top.laonaailifa.middleware.netty.bioStudy.nio_chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class Client1 implements Runnable {

    private SocketChannel socketChannel;
    private Selector selector;

    public Client1() throws IOException {
        socketChannel = SocketChannel.open();
        selector = Selector.open();
        socketChannel.configureBlocking(false);
    }


    public static void main(String[] args) throws IOException {
        new Thread(new Client1()).start();
    }

    @Override
    public void run() {
        try {
            doCon();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            while (true) {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isValid()) {
                        if (selectionKey.isConnectable()) {
                            SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                            if (socketChannel.finishConnect()) {
                                socketChannel.register(selector, SelectionKey.OP_READ);
                                writeData(socketChannel);
                            } else {
                                System.out.println("Exit");
                                System.exit(1);
                            }
                        }
                        if (selectionKey.isReadable()) {
                            readData();
                        }
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readData() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int read = socketChannel.read(buffer);
        if (read > 0) {
            byte[] array = buffer.array();
            System.out.println(new String(array,"utf-8"));
        }
    }

    private void doCon() throws IOException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(
                "127.0.0.1", 9999);
        if (socketChannel.connect(inetSocketAddress)) {
            socketChannel.register(selector, SelectionKey.OP_READ);
            writeData(socketChannel);
        } else {
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
        }

    }

    private void writeData(SocketChannel socketChannel) throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Scanner sc = new Scanner(System.in);
                    String s = sc.nextLine();
                    if (s.equals("quit")) {
                        try {
                            socketChannel.close();
                            return;
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return;
                    }
                    try {
                        ByteBuffer byteBuffer = ByteBuffer.wrap(
                                (s).getBytes());
                        socketChannel.write(byteBuffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
