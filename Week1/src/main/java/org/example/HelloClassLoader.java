package org.example;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;

public class HelloClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try (var file = new RandomAccessFile("/Users/jay/work/geektime/GeekTimeJava/Week1/build/classes/java/main/org/example/Main.class", "r")) {
            var bytes = new byte[Math.toIntExact(file.length())];
            file.readFully(bytes);
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            new HelloClassLoader().findClass("org.example.Main").getDeclaredConstructor().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
