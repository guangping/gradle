package io.lance.gradle.common.shiro.test;

import io.lance.gradle.common.dao.AppMain;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;


/**
 * @author lance.
 * @time: 2018-04-27 15:35
 * @desc:
 */
@SpringBootTest(classes = AppMain.class)
public class ShiroTest extends AbstractTestNGSpringContextTests {


    @Test
    public void run() throws InterruptedException {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("lance", "123");
        subject.login(token);

        Thread.sleep(1000000);
    }
}
