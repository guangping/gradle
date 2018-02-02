package io.lance.gradle.dubbo.api.test.imp;

import com.alibaba.dubbo.config.annotation.Service;
import io.lance.gradle.common.core.util.Constants;
import io.lance.gradle.dubbo.api.test.UserService;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lance.
 * @time: 2018-01-29 17:22
 * @desc:
 */
@Service(interfaceClass = UserService.class)
public class UserServiceImpl implements UserService {
    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger();

    @Override
    public Map getCurrentTime() {
        String currentTime = DateFormatUtils.format(System.currentTimeMillis(), Constants.DATETIME_FORMAT_SS);

        Map jsonResult = new HashMap();
        jsonResult.put("time", currentTime);
        return jsonResult;
    }
}
