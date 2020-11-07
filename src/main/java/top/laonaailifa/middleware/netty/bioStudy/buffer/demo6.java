package top.laonaailifa.middleware.netty.bioStudy.buffer;

import java.nio.ByteBuffer;

public class demo6 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }
        buffer.position(2);
        buffer.limit(8);
        ByteBuffer slice = buffer.slice();
        for (int i = 0; i < slice.capacity(); i++) {
            byte anInt = buffer.get();
            slice.put((byte)(anInt * 2));
        }
    }
}
