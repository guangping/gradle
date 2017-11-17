package io.lance.gradle.common.web.freemarker.template;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;
import io.lance.gradle.common.core.util.ChineseUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.List;

/**
 * Author: Lance.
 * Date: 2017-09-14 15:38
 * Desc: 自定义函数 截取中文
 */
public class SubStringCn implements TemplateMethodModelEx {


    @Override
    public Object exec(List arguments) throws TemplateModelException {
        if (null == arguments || 2 != arguments.size()) {
            return null;
        }
        Object object = arguments.get(0); //获取第一个参数
        int length = NumberUtils.toInt(arguments.get(1).toString());
        String str = "";
        try {
            str = ChineseUtils.subString(object.toString(), length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }
}
