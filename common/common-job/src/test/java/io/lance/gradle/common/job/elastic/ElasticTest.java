package io.lance.gradle.common.job.elastic;

import io.lance.gradle.common.job.JobMain;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * @author lance.
 * @time: 2018-05-09 15:35
 * @desc:
 */
@SpringBootTest(classes = JobMain.class)
public class ElasticTest extends AbstractTestNGSpringContextTests {


    @Test
    public void run(){
        System.out.println("test job");
        try {
            Thread.sleep(1000*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
