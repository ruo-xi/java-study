package top.laonaailifa.middleware.netty.bioStudy.buffer;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class demo2 {
    public static void main(String[] args) throws IOException {

        FileInputStream fileInputStream = new FileInputStream("build.gradle");
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        channel.read(byteBuffer);
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()){
            System.out.print((char) byteBuffer.get());
        }
        fileInputStream.close();
    }

}
