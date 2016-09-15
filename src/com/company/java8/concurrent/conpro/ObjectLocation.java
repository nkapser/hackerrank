package com.company.java8.concurrent.conpro;

/**
 * Created by naresh.kapse on 26/08/16.
 */
import sun.misc.Unsafe;
import java.lang.reflect.Field;

public class ObjectLocation {

    private static int apple = 10;
    private int orange = 10;

    public static void main(String[] args) throws Exception {
        Unsafe unsafe = sun.misc.Unsafe.getUnsafe();

        Field appleField = ObjectLocation.class.getDeclaredField("apple");
        System.out.println("Location of Apple: "
                + unsafe.staticFieldOffset(appleField));

        Field orangeField = ObjectLocation.class.getDeclaredField("orange");
        System.out.println("Location of Orange: "
                + unsafe.objectFieldOffset(orangeField));
    }

    private static Unsafe getUnsafeInstance() throws SecurityException,
            NoSuchFieldException, IllegalArgumentException,
            IllegalAccessException {
        Field theUnsafeInstance = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafeInstance.setAccessible(true);
        return (Unsafe) theUnsafeInstance.get(Unsafe.class);
    }
}
