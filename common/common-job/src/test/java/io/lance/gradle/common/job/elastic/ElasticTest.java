package io.lance.gradle.common.job.elastic;

import io.lance.gradle.common.job.JobMain;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Scanner;

/**
 * @author lance.
 * @time: 2018-05-09 15:35
 * @desc:
 */
@SpringBootTest(classes = JobMain.class)
public class ElasticTest extends AbstractTestNGSpringContextTests {

    private static final String OPERATION_EXIT = "EXIT";

    @Test
    public void run() {
        System.out.println("请开始您的输入，EXIT/E 退出");
        //怎么让程序一直运行
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            String in = scan.next().toString();
            if (OPERATION_EXIT.equals(in.toUpperCase())
                    || OPERATION_EXIT.substring(0, 1).equals(in.toUpperCase())) {
                System.out.println("您成功已退出！");
                break;
            }
            System.out.println("您输入的值：" + in);
        }
    }
}
