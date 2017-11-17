package io.lance.gradle.common.web.freemarker.template;

import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import io.lance.gradle.common.web.util.DirectiveWrapperUtils;

import java.io.IOException;
import java.util.Map;

/**
 * @desc: 用户信息
 * @author: lance
 * @time: 2017-09-19 11:31
 */
public class UserInfoDirective implements TemplateDirectiveModel {

    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        BeansWrapper beansWrapper = DirectiveWrapperUtils.getBeansWrapper();

        env.setVariable("userInfo", beansWrapper.wrap(""));

        body.render(env.getOut());
    }
}
