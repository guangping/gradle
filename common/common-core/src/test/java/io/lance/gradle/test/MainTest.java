package io.lance.gradle.test;

import org.testng.annotations.Test;
import org.testng.collections.Lists;

import java.util.List;

/**
 * @author Lance.
 * @time: 2017-11-14 14:54
 * @desc:
 */
public class MainTest {


    @Test
    public void run() {
        List<String> items = Lists.newArrayList();
        items.add("1");
        items.add("2");

        System.out.println(items.contains("1"));
    }
}
