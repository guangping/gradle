package io.lance.gradle.common.web.util;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Locale;
import java.util.Map;

/**
 * @desc: freemarker工具类
 * @author: lance
 * @time: 2017-09-19 11:38:48
 */
public class FreemarkerUtils {

    private static final Logger logger = LogManager.getLogger(FreemarkerUtils.class);

    /**
     * 获取模板内容
     */
    public static String getTemplateContent(String template, Map param) throws TemplateException, IOException {
        String templateName = "templateName";
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
        cfg.setLocale(Locale.CHINA);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setCacheStorage(new freemarker.cache.MruCacheStorage(20, 250));
        StringTemplateLoader templateLoader = new StringTemplateLoader();
        templateLoader.putTemplate(templateName, template);
        cfg.setTemplateLoader(templateLoader);
        Template templateTPL = cfg.getTemplate(templateName, Locale.CHINA);

        StringWriter writer = new StringWriter();
        templateTPL.process(param, writer);
        return writer.toString();
    }


    /**
     * 根据模板生成文件
     */
    public static void createFile(String template, Map param, File outFile) throws TemplateException, IOException {
        if (null != outFile) {
            if (!outFile.getParentFile().exists()) {
                outFile.getParentFile().mkdirs();
            }
            if (!outFile.exists()) {
                outFile.createNewFile();
            }
        }
        String templateName = "templateName";
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
        cfg.setLocale(Locale.CHINA);
        cfg.setDefaultEncoding("UTF-8");
        cfg.setCacheStorage(new freemarker.cache.MruCacheStorage(20, 250));
        StringTemplateLoader templateLoader = new StringTemplateLoader();
        templateLoader.putTemplate(templateName, template);
        cfg.setTemplateLoader(templateLoader);
        Template templateTPL = cfg.getTemplate(templateName, Locale.CHINA);

        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
        templateTPL.process(param, out);
        out.flush();
        out.close();
    }
}
