package top.laonaailifa.middleware.netty.nettyStudy.demo6_mashibing_rpc.tool;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SerDerUtil {

    static ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

    public synchronized static byte[] ser(Object o){
        byteArrayOutputStream.reset();
        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return byteArrayOutputStream.toByteArray();
    }
}
