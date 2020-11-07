package top.laonaailifa.middleware.netty.bioStudy.buffer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class demo4 {
    public static void main(String[] args) throws IOException {
        FileOutputStream outputStream = new FileOutputStream("test/demo4write");
        FileInputStream inputStream = new FileInputStream("test/demo4read");

        FileChannel channelWrite = outputStream.getChannel();
        FileChannel channelRead = inputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(100);
        while (true) {
            byteBuffer.clear();
//            System.out.println(byteBuffer.position());
            int number = channelRead.read(byteBuffer);
            /**
             * 返回0的原因以下
             * 1.buffer 读满
             * 2.channel无数据时,并且没有删除SleectionKey
             * 3.资源被占用
             */
            System.out.println(number);
            if (-1 == number){
                break;
            }
            byteBuffer.flip();
            channelWrite.write(byteBuffer);
//            System.out.println(byteBuffer.position());
        }
    }
}
