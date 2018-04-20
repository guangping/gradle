package io.lance.gradle.common.message.service;

import com.alibaba.fastjson.JSONObject;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import io.lance.gradle.common.core.disruptor.generic.GenericEvent;
import io.lance.gradle.common.core.exception.EbsException;
import io.lance.gradle.common.core.util.Constants;
import io.lance.gradle.common.message.pojo.MessageSend;
import io.lance.gradle.common.message.util.MessageTemplateCode;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

/**
 * @author lance.
 * @time: 2018-04-19 17:27
 * @desc:
 */
@Service
public class MessageService {
    private static final Logger logger = LogManager.getLogger();

    private Disruptor<GenericEvent<MessageSend>> disruptor = null;

    @PostConstruct
    public void init() {
        EventFactory<GenericEvent<MessageSend>> eventFactory = new EventFactory<GenericEvent<MessageSend>>() {
            @Override
            public GenericEvent<MessageSend> newInstance() {
                return new GenericEvent<MessageSend>();
            }
        };

        disruptor = new Disruptor<GenericEvent<MessageSend>>(
                GenericEvent<MessageSend>::new, Constants.RING_BUFFER_SIZE,
                Executors.defaultThreadFactory(),
                ProducerType.SINGLE,
                new YieldingWaitStrategy());

        disruptor.handleEventsWithWorkerPool(getWorkPool());

        //异常处理
        disruptor.setDefaultExceptionHandler(new ExceptionHandler<GenericEvent<MessageSend>>() {
            @Override
            public void handleEventException(Throwable ex, long sequence, GenericEvent<MessageSend> event) {
                logger.catching(ex);
            }

            @Override
            public void handleOnStartException(Throwable ex) {
                logger.catching(ex);
            }

            @Override
            public void handleOnShutdownException(Throwable ex) {
                logger.catching(ex);
            }
        });

        disruptor.start();
        logger.info("消息处理init ......");
    }


    /**
     * 消息发送
     *
     * @param param 参数
     * @author lance
     * @date 2018-04-19 17:40:01
     */
    public void sendMessage(MessageTemplateCode templateCode, HashMap param) {
        if (null == templateCode || null == param) {
            throw new EbsException("消息模块编码或参数不能为空");
        }
        MessageSend messageSend = new MessageSend();
        messageSend.setTemplateCode(templateCode);
        messageSend.setTemplateParam(param);
        disruptor.getRingBuffer().publishEvent((event, sequence, arg) -> event.set(arg), messageSend);
    }

    private MessageWorkerHandler[] getWorkPool() {
        MessageWorkerHandler[] pools = {
                new MessageWorkerHandler(),
                new MessageWorkerHandler()
        };
        return pools;
    }


    class MessageWorkerHandler implements WorkHandler<GenericEvent<MessageSend>> {

        @Override
        public void onEvent(GenericEvent<MessageSend> event) throws Exception {
            MessageSend msg = event.get();
            MessageTemplateCode templateCode = msg.getTemplateCode();
            Map templatParam = msg.getTemplateParam();

            System.out.println("模板code:" + String.valueOf(templateCode).toLowerCase() + ",参数:" + JSONObject.toJSONString(templatParam));

        }
    }


}
