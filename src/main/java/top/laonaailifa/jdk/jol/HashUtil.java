package top.laonaailifa.jdk.jol;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class HashUtil {
    public static void getHash(Object object) throws NoSuchFieldException, IllegalAccessException {
        Field field = Unsafe.class.getDeclaredField("theUnsafe");
        field.setAccessible(true);

        Unsafe unsafe = (Unsafe) field.get(null);
        long hashCode = 0;
        for (int i = 4; i > 0; i--) {
            hashCode = hashCode << 8;
            hashCode |= unsafe.getByte(object, i) & 0xff;
            System.out.println(Long.toHexString(hashCode));
        }
        String code = Long.toHexString(hashCode);
        System.out.println("util--------0x" + code);
    }
}
