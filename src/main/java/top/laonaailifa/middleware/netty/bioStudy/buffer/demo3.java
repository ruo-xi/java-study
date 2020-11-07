package top.laonaailifa.middleware.netty.bioStudy.buffer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class demo3 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("test/demo3.txt");
        FileChannel channel = fileOutputStream.getChannel();
        byte[] bytes="yu".getBytes();
        ByteBuffer byteBuffer = ByteBuffer.allocate(6);
        byteBuffer.put(bytes);
        byteBuffer.flip();
        channel.write(byteBuffer);
        fileOutputStream.close();
    }
}
