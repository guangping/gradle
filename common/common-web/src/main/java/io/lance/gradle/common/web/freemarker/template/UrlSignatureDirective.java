package io.lance.gradle.common.web.freemarker.template;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.apache.commons.lang3.ObjectUtils;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * @desc: url加密
 * @author: lance
 * @time: 2017-09-19 11:17
 */
public class UrlSignatureDirective implements TemplateDirectiveModel {

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        String url = ObjectUtils.identityToString(params.get("url"));
        //调用加密方法
        String urlSignature = "";
        Writer out = env.getOut();
        out.write(urlSignature);
    }
}
