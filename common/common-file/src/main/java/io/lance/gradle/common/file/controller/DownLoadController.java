package io.lance.gradle.common.file.controller;

import com.google.common.base.Charsets;
import io.lance.gradle.common.core.exception.EbsException;
import io.lance.gradle.common.web.util.WebUtil;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * @author Lance.
 * @time: 2017-12-25 15:52
 * @desc:
 */
@Controller
@RequestMapping("/download")
public class DownLoadController {

    private static final Logger logger = LogManager.getLogger();

    private static final int DEFAULT_BUFFER_SIZE = 4096;


    /**
     * 单文件下载
     *
     * @author lance
     * @time: 2017-12-25 16:03:55
     */
    @RequestMapping(value = "/${fileId}")
    public String download(@RequestParam("file_id") String fileId, @RequestParam("timestamp") String timestamp, HttpServletRequest request, HttpServletResponse response) {
        logger.info("文件下载;fileId:{},timestamp:{}", fileId, timestamp);

        //TODO 1.验证文件的合法性

        String ip = WebUtil.getIpAddress();
        //TODO 2.验证ip合法性

        File downFile = null;

        String fileName = null;
        long fileLength = downFile.length();
        /** 随机读取数据 */
        RandomAccessFile raf = null;

        /** 写出数据*/
        OutputStream os = null;

        /** 缓冲*/
        OutputStream out = null;

        try {
            this.setResponse(fileName, request, response);
            response.addHeader("Content-Length", String.valueOf(fileLength));
            os = response.getOutputStream();
            out = new BufferedOutputStream(os);

            InputStream inputStream = new FileInputStream(downFile);

            IOUtils.copyLarge(inputStream, out);

            out.flush();
            out.close();
            raf.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }


    /**
     * 文件压缩后进行下载
     *
     * @author lance
     * @time: 2017-12-25 16:04:13
     */
    @RequestMapping(value = "/zip/${fileId}")
    public String downloadZip(HttpServletRequest request, @RequestParam("file_id") String fileId) {

        return null;
    }


    /**
     * 设置头信息
     */
    private void setResponse(String fileName, HttpServletRequest request, HttpServletResponse response) {
        try {
            String agent = request.getHeader("USER-AGENT");

            String contentType = null;
            String attachment = null;
            String ext = FilenameUtils.getExtension(fileName);

            if (StringUtils.equalsIgnoreCase("swf", ext)) {
                contentType = "application/x-shockwave-flash";
                attachment = "application/x-shockwave-flash";
            } else {
                contentType = "application/octet-stream";
                attachment = "aattachment";
            }

            if (null != agent && (-1 != agent.indexOf("MSIE") || -1 != agent.indexOf("Trident"))) {
                fileName = URLEncoder.encode(fileName, "utf-8");
            } else {
                fileName = new String(fileName.getBytes(Charsets.UTF_8), Charsets.ISO_8859_1);
            }

            response.setContentType(contentType);
            response.addHeader("Content-Disposition", attachment + ";filename=" + makeValidFileName(fileName));
        } catch (Exception e) {
            throw new EbsException(e);
        }
    }

    /**
     * 过滤特殊字符
     */
    private String makeValidFileName(String str) throws PatternSyntaxException {

        //清除掉所有特殊字符
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\]<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

}
