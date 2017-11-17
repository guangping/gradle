package io.lance.gradle.common.web.freemarker.template;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import freemarker.core.Environment;
import freemarker.ext.beans.StringModel;
import freemarker.template.*;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * Author: Lance.
 * Date: 2017-09-14 15:40
 * Desc: 对象转json
 */
public class JsonDirectiveModel implements TemplateDirectiveModel {

    @Override
    public void execute(Environment env, Map params, TemplateModel[] templateModels, TemplateDirectiveBody templateDirectiveBody) throws TemplateException, IOException {
        Object value = params.get("value");
        if (value == null) return;

        Object obj = null;
        if (value instanceof StringModel) {
            StringModel strignModel = (StringModel) value;
            obj = strignModel.getWrappedObject();
        } else if (value instanceof SimpleHash) {
            SimpleHash simpleHash = (SimpleHash) value;
            obj = simpleHash.toMap();
        }
        String str = "";
        if (null != obj) {
            str = JSONObject.toJSONString(obj, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty);
        }
        Writer out = env.getOut();
        out.write(str);
    }
}
