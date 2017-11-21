package io.lance.gradle.common.core.service;


import com.alibaba.fastjson.JSONObject;
import io.lance.gradle.common.core.bean.LogRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;

/**
 * @desc: 日志记录
 * @author: lance
 * @time: 2017-10-23 15:12
 */
@Service
public class LogService {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private Executor executor;


    /**
     * @desc: 记录操作日志
     * @author: lance
     * @time: 2017-10-23 15:16:39
     */
    public void save(LogRecord record) {
        this.executor.execute(() -> {
            //TODO 调用日志记录服务
            String json = JSONObject.toJSONString(record);
            logger.info("记录日志:{}", json);


        });
    }
}
