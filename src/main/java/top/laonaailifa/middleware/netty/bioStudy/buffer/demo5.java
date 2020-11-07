package top.laonaailifa.middleware.netty.bioStudy.buffer;

import java.nio.ByteBuffer;

public class demo5 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(100);
        buffer.putChar('a');
        buffer.putInt(2);
        buffer.putLong(5000L);
        buffer.putShort((short)2);
        buffer.putDouble(12.4);
        buffer.flip();

        System.out.println(buffer.getChar());
        System.out.println(buffer.getInt());
        System.out.println(buffer.getLong());
        System.out.println(buffer.getShort());
        System.out.println(buffer.getDouble());
    }

}
