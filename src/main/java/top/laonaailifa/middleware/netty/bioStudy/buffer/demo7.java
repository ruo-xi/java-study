package top.laonaailifa.middleware.netty.bioStudy.buffer;

import java.nio.ByteBuffer;

public class demo7 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();
        System.out.println(buffer.getClass());
        System.out.println(readOnlyBuffer.getClass());
        readOnlyBuffer.flip();
        System.out.println(buffer.position());
        System.out.println(readOnlyBuffer.position());
        for (int i = 0; i < readOnlyBuffer.capacity(); i++) {
            System.out.println(readOnlyBuffer.get());
        }

    }
}
