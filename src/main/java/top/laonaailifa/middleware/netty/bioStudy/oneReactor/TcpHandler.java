package top.laonaailifa.middleware.netty.bioStudy.oneReactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class TcpHandler implements Runnable {

    SelectionKey selectionKey;
    SocketChannel socketChannel;

    int state;

    public TcpHandler(SelectionKey selectionKey, SocketChannel socketChannel) {
        this.selectionKey = selectionKey;
        this.socketChannel = socketChannel;
    }


    @Override
    public void run() {
        try {
            if (state == 0) {
                read();
            } else {
                send();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void send() throws IOException {
        String str = "Your message has sent to" + socketChannel.getRemoteAddress().toString() + "\r\n";
        ByteBuffer buf = ByteBuffer.wrap(str.getBytes());
        while (buf.hasRemaining()) {
            socketChannel.write(buf);
        }
        state = 0;
        selectionKey.interestOps(SelectionKey.OP_READ);
        selectionKey.selector().wakeup();
    }

    private void read() throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        int num = socketChannel.read(byteBuffer);
        if (num == -1) {
            System.out.println(socketChannel.getRemoteAddress().toString() + "is closed");
            closeChannel();
            return;
        }
        String str = new String(byteBuffer.array(), "utf-8");
        if ((str != null) && !str.equals(" ")) {
            process(str);
            System.out.println(socketChannel.getRemoteAddress().toString() + " > " + str);
            state = 1;
            selectionKey.interestOps(SelectionKey.OP_WRITE);
            selectionKey.selector().wakeup();
        }
    }

    private void closeChannel() {
        try {
            selectionKey.cancel();
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void process(String str) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
