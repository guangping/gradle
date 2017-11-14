package io.lance.gradle.common.core.util;


import io.lance.gradle.common.core.exception.EbsException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Map;

/**
 * @desc: 断言
 * @author: lance
 * @time: 2017-09-28 15:16
 */
public class Assert {

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new EbsException(message);
        }
    }

    public static void isNotBlank(String str, String message) {
        if (StringUtils.isNotBlank(str)) {
            throw new EbsException(message);
        }
    }

    public static void isNotEmpty(String str, String message) {
        if (StringUtils.isNotEmpty(str)) {
            throw new EbsException(message);
        }
    }

    public static void notEmpty(Collection<?> collection, String message) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new EbsException(message);
        }
    }

    public static void notEmpty(Map<?, ?> map, String message) {
        if (CollectionUtils.isEmpty(map)) {
            throw new EbsException(message);
        }
    }
}
