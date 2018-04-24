package io.lance.gradle.common.log.service;


import com.alibaba.fastjson.JSONObject;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import io.lance.gradle.common.core.disruptor.generic.GenericEvent;
import io.lance.gradle.common.core.util.Constants;
import io.lance.gradle.common.log.pojo.LogRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.concurrent.Executors;

/**
 * @desc: 日志记录
 * @author: lance
 * @time: 2017-10-23 15:12
 */
@Service
public class LogService {

    private static final Logger logger = LogManager.getLogger();

    private Disruptor<GenericEvent<LogRecord>> disruptor = null;


    @PostConstruct
    public void init() {//TODO 初始化两种方案 方案一:当前实现的方式  方案二:可放在静态块中执行 考虑spring注入的问题
        EventFactory<GenericEvent> eventFactory = new EventFactory<GenericEvent>() {
            @Override
            public GenericEvent newInstance() {
                return new GenericEvent();
            }
        };

        disruptor = new Disruptor<GenericEvent<LogRecord>>(
                GenericEvent<LogRecord>::new, Constants.RING_64,
                Executors.defaultThreadFactory(),
                ProducerType.SINGLE,
                new SleepingWaitStrategy());

        disruptor.handleEventsWithWorkerPool(getWorkPool());

        //异常处理
        disruptor.setDefaultExceptionHandler(new ExceptionHandler<GenericEvent>() {
            @Override
            public void handleEventException(Throwable ex, long sequence, GenericEvent event) {
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
        logger.info("日志记录init ......");
    }

    @PreDestroy
    public void shutdown() {
        disruptor.shutdown();
    }

    /**
     * @desc: 记录操作日志
     * @author: lance
     * @time: 2017-10-23 15:16:39
     */
    public void save(LogRecord record) {
        disruptor.getRingBuffer().publishEvent((event, sequence, arg) -> event.set(arg), record);
    }


    private LogWorkerHandler[] getWorkPool() {
        LogWorkerHandler[] pools = {
                new LogWorkerHandler("log-work-1"),
                new LogWorkerHandler("log-work-2"),
                new LogWorkerHandler("log-work-3"),
                new LogWorkerHandler("log-work-4"),
                new LogWorkerHandler("log-work-5")
        };
        return pools;
    }


    /**
     * log记录
     *
     * @author lance
     * @date 2018-04-10 16:40:46
     */
    class LogWorkerHandler implements WorkHandler<GenericEvent<LogRecord>> {

        private String name;

        public LogWorkerHandler(String name) {
            this.name = name;
        }


        @Override
        public void onEvent(GenericEvent<LogRecord> event) throws Exception {
            LogRecord logRecord = event.get();

            handle(logRecord);
        }
    }

    private void handle(LogRecord logRecord) {
        System.out.println(JSONObject.toJSONString(logRecord));
    }


}
