package io.lance.gradle.common.file.service.sftp.impl;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import io.lance.gradle.common.file.service.FileService;
import io.lance.gradle.common.file.service.sftp.vo.SFtpConfig;
import io.lance.gradle.common.file.vo.EbsFile;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Properties;
import java.util.UUID;

/**
 * @author Lance.
 * @time: 2018-01-23 14:08
 * @desc: sftp文件处理
 */
@Service
public class SFtpFileServiceImpl implements FileService {
    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger();


    private static final int TIME_OUT = 6000;

    private Channel channel = null;

    private Session session = null;

    private ChannelSftp chSftp = null;

    @PostConstruct
    public void init() {
        logger.info("初始时执行....");

        try {
            this.getChannel();
        } catch (JSchException e) {
            e.printStackTrace();
        } catch (SftpException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取配置信息
     *
     * @author lance
     * @date 2018-01-23 14:21:39
     */
    public SFtpConfig getConfig() {
        logger.info("获取配置信息");
        SFtpConfig config = new SFtpConfig();
        config.setHost("");
        config.setPort(22);
        config.setUserName("");
        config.setPassword("");

        return config;
    }

    public ChannelSftp getChannel() throws JSchException, SftpException {
        SFtpConfig config = this.getConfig();

        String ftpHost = config.getHost();
        int ftpPort = config.getPort();
        String ftpUserName = config.getUserName();
        String ftpPassword = config.getPassword();
        // 创建JSch对象
        JSch jsch = new JSch();
        // 根据用户名，主机ip，端口获取一个Session对象
        session = jsch.getSession(ftpUserName, ftpHost, ftpPort);
        if (ftpPassword != null) {
            // 设置密码
            session.setPassword(ftpPassword);
        }
        Properties configTemp = new Properties();
        configTemp.put("StrictHostKeyChecking", "no");
        // 为Session对象设置properties
        session.setConfig(configTemp);
        // 设置timeout时间
        session.setTimeout(TIME_OUT);
        session.connect();
        // 通过Session建立链接
        // 打开SFTP通道
        channel = session.openChannel("sftp");
        // 建立SFTP通道的连接
        channel.connect();
        logger.info("Connected successfully to ftpHost = " + ftpHost + ",as ftpUserName = " + ftpUserName + ", returning: " + channel);
        chSftp = (ChannelSftp) channel;

        chSftp.mkdir("/files");

        return chSftp;
    }

    /**
     * close
     *
     * @author lance
     * @date 2018-01-23 15:11:49
     */
    public void closeChannel() throws Exception {
        if (channel != null) {
            channel.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
        logger.info("disconnected SFTP successfully!");
    }


    @Override
    public EbsFile put(String src) {
        String fileId = StringUtils.removeAll(UUID.randomUUID().toString(), "\\-");
        String fileName = FilenameUtils.getExtension(src);


        EbsFile ebsFile = new EbsFile();
        ebsFile.setFileId(fileId);

        try {

            String dst = null;

            //chSftp.put(src, dst, ChannelSftp.OVERWRITE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public EbsFile put(File file) {
        return null;
    }

    public void get(String fileId) {
        try {
            System.out.println(chSftp.getHome());


            // chSftp.get();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
