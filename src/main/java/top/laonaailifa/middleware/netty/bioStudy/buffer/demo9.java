package top.laonaailifa.middleware.netty.bioStudy.buffer;

import java.nio.ByteBuffer;

public class demo9 {
    public static void main(String[] args) {
        byte[] bytes = new byte[]{'a', 'b', 'c'};
        ByteBuffer wrap = ByteBuffer.wrap(bytes);
        bytes[0] = 's';
        wrap.put(2, (byte) 's');
        for (int i = 0; i < wrap.capacity(); i++) {
            System.out.println((char) wrap.get());
        }
    }
}
