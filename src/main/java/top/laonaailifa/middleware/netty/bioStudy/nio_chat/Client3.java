package top.laonaailifa.middleware.netty.bioStudy.nio_chat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class Client3 {

    private SocketChannel socketChannel;

    private String usrName;

    public Client3() throws IOException {
        socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 9999);
        if (!socketChannel.connect(socketAddress)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("nio非阻塞");
            }
        }
        usrName = socketAddress.getHostString();
    }


    public static void main(String[] args) throws IOException {
        Client3 client = new Client3();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        client.readData();
                    } catch (IOException e) {
                        System.out.println("连接断开");
                        break;
                    }
                }
            }
        }.start();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            if (client.writeData(s) == -1) {
                break;
            }
        }
    }

    private int writeData(String s) throws IOException {
        if (s.equals("quit")) {
            socketChannel.close();
            return -1;
        }
        ByteBuffer wrap = ByteBuffer.wrap(s.getBytes());
        socketChannel.write(wrap);
        return 0;
    }

    private void readData() throws IOException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int read = socketChannel.read(buffer);
        if (read > 0) {
            System.out.println(new String(buffer.array(), "utf-8"));
        }
    }
}