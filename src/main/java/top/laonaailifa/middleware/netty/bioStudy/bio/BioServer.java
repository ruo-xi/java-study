package top.laonaailifa.middleware.netty.bioStudy.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器端
 */
public class BioServer {

    ServerSocket serverSocket;

    public BioServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("ip: "+socket.getRemoteSocketAddress());
            new Thread(new BioServerHandler(socket)).start();
        }
    }


    public static void main(String[] args) {
        BioServer server = new BioServer(9090);
        server.start();
    }
}
