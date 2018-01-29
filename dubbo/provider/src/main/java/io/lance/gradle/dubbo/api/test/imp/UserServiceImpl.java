package io.lance.gradle.dubbo.api.test.imp;

import io.lance.gradle.common.core.util.Constants;
import io.lance.gradle.common.core.util.JsonResult;
import io.lance.gradle.dubbo.api.test.UserService;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * @author Lance.
 * @time: 2018-01-29 17:22
 * @desc:
 */

public class UserServiceImpl implements UserService {
    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger();

    @Override
    public JsonResult getCurrentTime() {
        String currentTime = DateFormatUtils.format(System.currentTimeMillis(), Constants.DATETIME_FORMAT_SS);

        JsonResult jsonResult = new JsonResult();
        jsonResult.setData(currentTime);
        jsonResult.setSuccess(Constants.YES);
        jsonResult.setCode(Constants.SUCCESS_CODE);
        return jsonResult;
    }
}
