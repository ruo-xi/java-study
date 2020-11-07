package top.laonaailifa.middleware.netty.nettyStudy;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.security.MessageDigest;
import java.util.Arrays;

public class TestByteBuf {
    public static void main(String[] args) {
        ByteBuf buf = Unpooled.buffer(10);
        byte[] bytes = {1, 2, 3, 4, 5};
        buf.writeBytes(bytes);
        printBuf(buf);

        buf.readByte();
        buf.readByte();
        printBuf(buf);

        buf.discardReadBytes();
        printBuf(buf);

        buf.clear();
        printBuf(buf);

        buf.setZero(0,buf.capacity());
        printBuf(buf);

        buf.writeBytes(bytes);
        buf.writeBytes(bytes);
        buf.writeByte(10);
        printBuf(buf);

    }

    public static void printBuf(ByteBuf buf) {
        System.out.println(buf.toString());
        System.out.println(Arrays.toString(buf.array()));
        System.out.println();
    }
}
