package io.lance.gradle.dubbo.api.test;

import io.lance.gradle.common.core.util.JsonResult;

/**
 * @author Lance.
 * @time: 2018-01-29 17:16
 * @desc: 测试
 */
public interface UserService {

    /**
     * 获取当前时间
     *
     * @author lance
     * @date 2018-01-29 17:19:40
     */
    JsonResult getCurrentTime();


}
