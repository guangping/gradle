package io.lance.gradle.common.core.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.StringUtils;

/**
 * @desc: 对象json转换
 * @author: lance
 * @time: 2017-09-15 10:18
 */
public class JsonUtils {

    /**
     * @desc: 转为json字符串
     * @author: lance
     * @time: 2017-09-15 10:19:44
     */
    public static String toJSONString(Object data) {
        return JSONObject.toJSONString(data);
    }

    /**
     * @desc: json转对象
     * @author: lance
     * @time: 2017-09-15 10:21:39
     */
    public static <T> T parseObject(String json, Class<T> clazz) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        return JSONObject.parseObject(json, clazz);
    }

    /**
     * @desc: 复杂对象转换 List<T>
     * @author: lance
     * @time: 2017-09-15 10:27:53
     */
    public static <T> T parseObject(String json, TypeReference<T> typeReference) {
        if (StringUtils.isBlank(json)) {
            return null;
        }
        return JSONObject.parseObject(json, typeReference);
    }
}
