package io.lance.gradle.api.test.controller;

import io.lance.gradle.common.core.util.Constants;
import io.lance.gradle.common.core.util.JsonResult;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author Lance.
 * @time: 2017-11-17 16:47
 * @desc: 测试代码
 */
@RestController
@RequestMapping("/api/test")
public class TestController {


    @RequestMapping(value = "/index", method = {RequestMethod.POST, RequestMethod.GET})
    public JsonResult index() {
        JsonResult jsonResult = new JsonResult();
        String currentTime = DateFormatUtils.format(new Date(), Constants.DATETIME_FORMAT_SS);
        jsonResult.setData(currentTime);
        jsonResult.setCode(1);
        jsonResult.setSuccess(Constants.YES);

        return jsonResult;
    }


}
