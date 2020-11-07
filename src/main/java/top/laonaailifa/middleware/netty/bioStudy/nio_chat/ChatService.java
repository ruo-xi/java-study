package top.laonaailifa.middleware.netty.bioStudy.nio_chat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class ChatService {
    private Selector selector;

    private ServerSocketChannel serverSocketChannel;
    private long timeout = 1000;

    public ChatService(int port) throws IOException {
        serverSocketChannel = ServerSocketChannel.open();
        selector = Selector.open();

        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务端准备就绪");
        start();
    }

    public void start() {
        try {
            int count = 0;
            while (true) {
                long start = System.nanoTime();
                selector.select(timeout);
                long end = System.nanoTime();
                if (end - start >= TimeUnit.MILLISECONDS.toNanos(timeout)) {
                    count = 1;
                } else {
                    count++;
                }
                if (count >= 10) {
                    System.out.println("有可能发生空轮寻" + count + "次");
                    rebuildSelector();
                    count = 1;
                    selector.selectNow();
                    continue;
                }
                Set<SelectionKey> selectionKeySet = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeySet.iterator();
                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();
                    if (selectionKey.isAcceptable()) {
                        SocketChannel accept = serverSocketChannel.accept();
                        accept.configureBlocking(false);
                        accept.register(selector, SelectionKey.OP_READ);
                        System.out.println(accept.getRemoteAddress().toString() + "连接");
                    }
                    if (selectionKey.isReadable()) {
                        readClientData(selectionKey);
                    }
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void rebuildSelector() throws IOException {
        Selector newSelector = Selector.open();
        Selector oldSelector = selector;
        for (SelectionKey key : oldSelector.keys()) {
            int i = key.interestOps();
            key.cancel();
            key.channel().register(newSelector, i);
        }
        selector = newSelector;
        oldSelector.close();
    }

    private void readClientData(SelectionKey key) {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        SocketChannel channel = ((SocketChannel) key.channel());
        String ip = null;
        try {
            ip = channel.getRemoteAddress().toString();
            int read = channel.read(buffer);
            buffer.flip();
            if (read > 0) {
                byte[] bytes = new byte[read];
                buffer.get(bytes, 0, read);
                String s = new String(bytes, "utf-8");
                System.out.println(ip + " sned:" + s);
                writeClientData(channel, s);
            } else if (read == -1) {
                System.out.println(ip + "离开");
                channel.close();
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println(ip + "离开");
        }


    }

    private void writeClientData(SocketChannel channel, String s) throws IOException {
        Set<SelectionKey> keys = selector.keys();
        for (SelectionKey key : keys) {
            if (key.isValid()) {
                SelectableChannel channel1 = key.channel();
                if (channel1 instanceof SocketChannel) {
                    SocketChannel socketChannel = (SocketChannel) channel1;
                    if (channel1 != channel) {
                        ByteBuffer wrap = ByteBuffer.wrap(s.getBytes());
                        socketChannel.write(wrap);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            ChatService service = new ChatService(9999);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
