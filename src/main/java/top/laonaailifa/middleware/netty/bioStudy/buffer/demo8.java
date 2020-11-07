package top.laonaailifa.middleware.netty.bioStudy.buffer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class demo8 {
    public static void main(String[] args) throws IOException {
        FileOutputStream outputStream = new FileOutputStream("/test/demo8write");
        FileInputStream inputStream = new FileInputStream("test/demo8read");

        FileChannel outputStreamChannel = outputStream.getChannel();
        FileChannel inputStreamChannel = inputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(100);
        while (true) {
            buffer.clear();
            int readNumber = inputStreamChannel.read(buffer);
            System.out.println(readNumber);
            if (readNumber == -1){
                break;
            }
            buffer.flip();
            outputStreamChannel.write(buffer);
        }
        outputStream.close();
        inputStream.close();
    }
}
