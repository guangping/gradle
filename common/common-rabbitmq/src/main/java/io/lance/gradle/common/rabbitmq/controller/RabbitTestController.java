package io.lance.gradle.common.rabbitmq.controller;

import com.google.common.collect.Maps;
import io.lance.gradle.common.core.util.Constants;
import io.lance.gradle.common.core.util.JsonResult;
import io.lance.gradle.common.core.util.JsonUtils;
import io.lance.gradle.common.rabbitmq.util.RabbitQueue;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author lance.
 * @time: 2018-04-23 13:54
 * @desc:
 */
@Controller
@RequestMapping("/rabbit")
public class RabbitTestController {

    private static final Logger logger = LogManager.getLogger(RabbitTestController.class);

    private static final AtomicInteger atom = new AtomicInteger();

    @Autowired
    private RabbitTemplate rabbitTemplate;


    @ResponseBody
    @RequestMapping(value = "/send", method = {RequestMethod.GET, RequestMethod.POST})
    public JsonResult send() {
        JsonResult jsonResult = new JsonResult();
        jsonResult.setMessage(Constants.OK);

        String currentTime = DateFormatUtils.format(new Date(), Constants.DATETIME_FORMAT_SS);
        Map<String, String> msg = Maps.newHashMap();
        msg.put("id", String.valueOf(atom.getAndIncrement()));
        msg.put("message", "ok");
        msg.put("currentTime", currentTime);

        String s = JsonUtils.toJSONString(msg);

        this.rabbitTemplate.convertAndSend(RabbitQueue.NOTICE, s);

        return jsonResult;
    }
}
