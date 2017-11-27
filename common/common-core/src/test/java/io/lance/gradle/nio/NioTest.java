package io.lance.gradle.nio;


import io.lance.gradle.common.core.util.Constants;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/**
 * @author Lance.
 * @time: 2017-11-27 14:13
 * @desc:
 */
public class NioTest {

    @Test
    public void run() throws UnsupportedEncodingException {
        String idea = "abcdefgh";
        byte[] ideas = idea.getBytes(Constants.CHARSET);
        //System.out.println(ideas.length);

        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.put(ideas);

        System.err.println(buffer);

        buffer.flip();
        System.out.println((char) buffer.get(3));
        System.err.println(buffer);
        byte[] dist = new byte[4];
        buffer.get(dist);
        buffer.compact();
        System.out.println("\n" + "content：" + new String(dist));
        System.err.println(buffer);
        buffer.rewind();
        System.err.println(buffer);

        /*buffer.flip();
        buffer.put((byte)'s');
        System.err.println(buffer);
        byte[] dist = new byte[4];
        buffer.get(dist);
        System.out.println("\n"+"content：" + new String(dist));
        System.err.println(buffer);*/


       /* System.out.println(new String(buffer.array()));
        byte [] items=new byte[26];
        buffer.get(items,0,13);
        System.out.println("content:"+new String(items));
        System.out.println(buffer);
        buffer.position(20);
        System.out.println(buffer);*/



   /*     byte [] items=new byte[10];
        buffer.get(items,0,10);
        System.out.println(new String(items));
        System.out.println(buffer);*/
    }


    @Test
    public void runFree() {
        ByteBuffer buffer = ByteBuffer.allocate(102400);
        System.out.println("--------Test reset----------");
        buffer.clear();
        buffer.position(5);
        buffer.mark();
        buffer.position(10);
        System.out.println("before reset:" + buffer);
        buffer.reset();
        System.out.println("after reset:" + buffer);

        System.out.println("--------Test rewind--------");
        buffer.clear();
        buffer.position(10);
        buffer.limit(15);
        System.out.println("before rewind:" + buffer);
        buffer.rewind();
        System.out.println("before rewind:" + buffer);

        System.out.println("--------Test compact--------");
        buffer.clear();
        buffer.put("abcd".getBytes());
        System.out.println("before compact:" + buffer);
        System.out.println(new String(buffer.array()));
        buffer.flip();
        System.out.println("after flip:" + buffer);
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        System.out.println((char) buffer.get());
        System.out.println("after three gets:" + buffer);
        System.out.println("\t" + new String(buffer.array()));
        buffer.compact();
        System.out.println("after compact:" + buffer);
        System.out.println("\t" + new String(buffer.array()));

        System.out.println("------Test get-------------");
        buffer = ByteBuffer.allocate(32);
        buffer.put((byte) 'a').put((byte) 'b').put((byte) 'c').put((byte) 'd')
                .put((byte) 'e').put((byte) 'f');
        System.out.println("before flip()" + buffer);
        // 转换为读取模式
        buffer.flip();
        System.out.println("before get():" + buffer);
        System.out.println((char) buffer.get());
        System.out.println("after get():" + buffer);
        // get(index)不影响position的值
        System.out.println((char) buffer.get(2));
        System.out.println("after get(index):" + buffer);
        byte[] dst = new byte[10];
        buffer.get(dst, 0, 2);
        System.out.println("after get(dst, 0, 2):" + buffer);
        System.out.println("\t dst:" + new String(dst));
        System.out.println("buffer now is:" + buffer);
        System.out.println("\t" + new String(buffer.array()));

        System.out.println("--------Test put-------");
        ByteBuffer bb = ByteBuffer.allocate(32);
        System.out.println("before put(byte):" + bb);
        System.out.println("after put(byte):" + bb.put((byte) 'z'));
        System.out.println("\t" + bb.put(2, (byte) 'c'));
        // put(2,(byte) 'c')不改变position的位置
        System.out.println("after put(2,(byte) 'c'):" + bb);
        System.out.println("\t" + new String(bb.array()));
        // 这里的buffer是 abcdef[pos=3 lim=6 cap=32]
        bb.put(buffer);
        System.out.println("after put(buffer):" + bb);
        System.out.println("\t" + new String(bb.array()));
    }


    @Test
    public void runTime() {
        System.out.println("----------Test allocate--------");
        System.out.println("before alocate:"
                + Runtime.getRuntime().freeMemory());

        // 如果分配的内存过小，调用Runtime.getRuntime().freeMemory()大小不会变化？
        // 要超过多少内存大小JVM才能感觉到？
        ByteBuffer buffer = ByteBuffer.allocate(102400);
        System.out.println("buffer = " + buffer);

        System.out.println("after alocate:"
                + Runtime.getRuntime().freeMemory());

        // 这部分直接用的系统内存，所以对JVM的内存没有影响
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(102400);
        System.out.println("directBuffer = " + directBuffer);
        System.out.println("after direct alocate:"
                + Runtime.getRuntime().freeMemory());

        System.out.println("----------Test wrap--------");
        byte[] bytes = new byte[32];
        buffer = ByteBuffer.wrap(bytes);
        System.out.println(buffer);

        buffer = ByteBuffer.wrap(bytes, 10, 10);
        System.out.println(buffer);
    }

    @Test
    public void send() throws UnsupportedEncodingException {
        String idea = "abcdefgh";
        byte[] ideas = idea.getBytes(Constants.CHARSET);

        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put(ideas);

        System.out.println(buffer);
        buffer.rewind();
        System.out.println(buffer);
    }


}
