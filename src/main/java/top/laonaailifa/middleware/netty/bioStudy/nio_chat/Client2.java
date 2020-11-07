package top.laonaailifa.middleware.netty.bioStudy.nio_chat;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Client2 {


    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9999);
        new Thread() {
            @Override
            public void run() {
                try {
                    byte[] bytes = new byte[1024];
                    int read = 0;
                    InputStream inputStream = socket.getInputStream();
                    while (true) {
                        System.out.println(11111);
                        read = inputStream.read(bytes);
                        if (read > 0) {
                            System.out.println(new String(bytes, 0, read));
                        }else if (read == -1){
                            inputStream.close();
                        }
                    }
                } catch (IOException e) {
                    System.out.println("连接关闭");
                }
            }
        }.start();

        try {
            OutputStream outputStream = socket.getOutputStream();
            Scanner sc = new Scanner(System.in);
            while (sc.hasNextLine()) {
                String s = sc.nextLine();
                if (s.equals("quit")) {
                    outputStream.close();
                    return;
                } else {
                    outputStream.write(s.getBytes(Charset.forName("utf-8")));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
