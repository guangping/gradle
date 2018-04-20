package io.lance.gradle.common.email.test;

import io.lance.gradle.common.email.AppMain;
import io.lance.gradle.common.email.config.EmailConfig;
import io.lance.gradle.common.email.pojo.EmailReceive;
import io.lance.gradle.common.email.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

/**
 * @author Lance.
 * @time: 2017-11-29 16:53
 * @desc:
 */
@SpringBootTest(classes = AppMain.class)
public class EmailTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private EmailConfig emailConfig;

    @Autowired
    private EmailService emailService;

    @Test
    public void config() {
        System.out.println(this.emailConfig.toString());
    }

    @Test
    public void send() {
        String title = "test";
        String content = "<a href=\"http://www.baidu.com/\">百度</a>";
        EmailReceive receive = new EmailReceive();
        receive.setEmail("");
        receive.setUserName("");


    }


}
