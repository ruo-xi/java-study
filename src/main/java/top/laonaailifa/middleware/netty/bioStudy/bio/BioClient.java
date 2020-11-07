package top.laonaailifa.middleware.netty.bioStudy.bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class BioClient {
    public static void main(String[] args) {
        Socket socket = null;
        OutputStream outputStream = null;
        try {
            socket = new Socket("127.0.0.1", 9090);
            new Thread(new BioClientHandler(socket)).start();
            outputStream = socket.getOutputStream();
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入消息");
            while (true) {
                String s = scanner.nextLine();

                if (s.trim().equals("by")) {
                    break;
                }
                outputStream.write(s.getBytes());
                outputStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (outputStream != null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
