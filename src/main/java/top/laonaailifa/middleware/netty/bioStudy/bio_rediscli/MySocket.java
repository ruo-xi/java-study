package top.laonaailifa.middleware.netty.bioStudy.bio_rediscli;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MySocket {

    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;

    public MySocket(String s, int i) {
        try {
            socket = new Socket(s, i);
            inputStream = socket.getInputStream();
            outputStream = socket.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(String str) {
        try {
            outputStream.write(str.getBytes());
//            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String read() {
        byte[] bytes = new byte[1024];
        int count = 0;
        try {
            count = inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(bytes, 0, count);
    }

}
