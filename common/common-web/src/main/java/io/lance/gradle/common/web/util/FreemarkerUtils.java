package io.lance.gradle.common.web.util;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.lance.gradle.common.core.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

/**
 * @desc: freemarker工具类
 * @author: lance
 * @time: 2017-09-19 11:38:48
 */
public class FreemarkerUtils {

    private static final Logger logger = LogManager.getLogger(FreemarkerUtils.class);

    private static final String TEMPLATE_NAME = "templateName";

    /**
     * 获取模板内容
     */
    public static String getTemplateContent(String template, Map param) throws TemplateException, IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setLocale(Locale.CHINA);
        cfg.setDefaultEncoding(Constants.CHARSET);
        cfg.setCacheStorage(new freemarker.cache.MruCacheStorage(20, 250));
        StringTemplateLoader templateLoader = new StringTemplateLoader();
        templateLoader.putTemplate(TEMPLATE_NAME, template);
        cfg.setTemplateLoader(templateLoader);
        Template templateTPL = cfg.getTemplate(TEMPLATE_NAME, Locale.CHINA);

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
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
        cfg.setLocale(Locale.CHINA);
        cfg.setDefaultEncoding(Constants.CHARSET);
        cfg.setCacheStorage(new freemarker.cache.MruCacheStorage(20, 250));
        StringTemplateLoader templateLoader = new StringTemplateLoader();
        templateLoader.putTemplate(TEMPLATE_NAME, template);
        cfg.setTemplateLoader(templateLoader);
        Template templateTPL = cfg.getTemplate(TEMPLATE_NAME, Locale.CHINA);

        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "UTF-8"));
        templateTPL.process(param, out);
        out.flush();
        out.close();
    }
}
