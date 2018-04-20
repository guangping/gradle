package io.lance.gradle.common.email.service;

import io.lance.gradle.common.core.exception.EbsException;
import io.lance.gradle.common.core.util.Constants;
import io.lance.gradle.common.email.config.EmailConfig;
import io.lance.gradle.common.email.pojo.EmailReceive;
import io.lance.gradle.common.email.pojo.EmailSendSingle;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import java.util.List;

/**
 * @author Lance.
 * @time: 2017-11-29 16:06
 * @desc: 邮件发送
 */
@Service
public class EmailService {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private EmailConfig emailConfig;


    /**
     * 发送邮件
     *
     * @author lance
     * @date 2018-04-19 16:57:11
     */
    public void sendMail(EmailSendSingle emailSendSingle) throws EmailException {
        if (null == emailSendSingle) {
            throw new EbsException("未设置收件人信息");
        }
        EmailReceive receive = new EmailReceive();
        receive.setEmail(emailSendSingle.getReceiveEmail());
        receive.setUserName(emailSendSingle.getReceiveUserName());
        this.sendMail(emailSendSingle.getTitle(), emailSendSingle.getContent(), receive);
    }

    public void sendMail(String title, String content, List<EmailReceive> list) throws EmailException {
        if (StringUtils.isBlank(title) || StringUtils.isBlank(content)) {
            throw new EbsException("邮件标题或内容不能为空");
        }
        if (null == list || list.isEmpty()) {
            throw new EbsException("未设置收件人信息");
        }
        this.sendMail(title, content, list.toArray(new EmailReceive[]{}));
    }

    /**
     * @param title       标题
     * @param content     内容
     * @param receiveList 邮件接收者
     * @desc: 批量发送邮件
     * @author lance
     * @time: 2017-11-29 16:14:54
     */
    public void sendMail(String title, String content, EmailReceive... receiveList) throws EmailException {
        if (null == receiveList || receiveList.length == 0) {
            logger.info("未设置收件人");
            return;
        }
        String hostName = this.emailConfig.getHost();
        int port = this.emailConfig.getPort();
        String userName = this.emailConfig.getUserName();
        String password = this.emailConfig.getPassword();
        String from = this.emailConfig.getFrom();

        HtmlEmail email = new HtmlEmail();
        email.setCharset(Constants.CHARSET);
        email.setHostName(hostName);
        email.setSmtpPort(port);
        email.setAuthenticator(new DefaultAuthenticator(userName, password));
        email.setFrom(from, userName);
        //收件人
        for (EmailReceive receive : receiveList) {
            email.addTo(receive.getEmail(), receive.getUserName());
        }
        email.setSubject(title);
        email.setHtmlMsg(HtmlUtils.htmlUnescape(content));

        //附件
        /*     email.attach();*/

        //发送
        String result = email.send();

        logger.info("邮件发送结果:{}", result);
    }
}
