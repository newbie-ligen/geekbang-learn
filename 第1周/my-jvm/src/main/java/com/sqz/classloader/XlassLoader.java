package com.sqz.classloader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *继承classLoader重写findClass方法就可以实现自定义ClassLoader
 */
public class XlassLoader extends ClassLoader {

    public String path;

    public XlassLoader(String path) {
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        InputStream inputStream = this.getClass().getResourceAsStream(path+name + ".xlass");
        try {
            int length = inputStream.available();
            byte[] byteArray = new byte[length];
            inputStream.read(byteArray);
            byte[] classBytes = decode(byteArray);
            return defineClass(name, classBytes, 0, classBytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException(name, e);
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    inputStream = null;
                }
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        XlassLoader xlassLoader = new XlassLoader("/");
        Class<?> clazz = xlassLoader.loadClass("/Hello");
        Object instance = clazz.getDeclaredConstructor().newInstance();
        Method method = clazz.getMethod("hello");
        method.setAccessible(true);
        method.invoke(instance);
    }

    private static byte[] decode(byte[] byteArrays) {
        byte[] targetArray = new byte[byteArrays.length];
        for(int i=0;i<byteArrays.length;i++) {
            targetArray[i] = (byte) (255-byteArrays[i]);
        }
        return targetArray;
    }

}
