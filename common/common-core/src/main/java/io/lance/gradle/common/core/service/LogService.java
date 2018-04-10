package io.lance.gradle.common.core.service;


import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.ExceptionHandler;
import com.lmax.disruptor.WorkHandler;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import io.lance.gradle.common.core.bean.LogRecord;
import io.lance.gradle.common.core.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.concurrent.Executors;

/**
 * @desc: 日志记录
 * @author: lance
 * @time: 2017-10-23 15:12
 */
@Service
public class LogService {

    private static final Logger logger = LogManager.getLogger();

    private Disruptor<LogEvent> disruptor = null;

    @PostConstruct
    public void init() {
        logger.info("日志记录init ......");
        EventFactory<LogEvent> eventFactory = new EventFactory<LogEvent>() {
            @Override
            public LogEvent newInstance() {
                return new LogEvent();
            }
        };

        disruptor = new Disruptor<LogEvent>(
                LogEvent::new, Constants.RING_BUFFER_SIZE,
                Executors.defaultThreadFactory(),
                ProducerType.SINGLE,
                new YieldingWaitStrategy());

        disruptor.handleEventsWithWorkerPool(getWorkPool());

        //异常处理
        disruptor.setDefaultExceptionHandler(new ExceptionHandler<LogEvent>() {
            @Override
            public void handleEventException(Throwable ex, long sequence, LogEvent event) {
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
    }

    /**
     * @desc: 记录操作日志
     * @author: lance
     * @time: 2017-10-23 15:16:39
     */
    public void save(LogRecord record) {
        disruptor.getRingBuffer().publishEvent((event, sequence, arg) -> event.logRecord = arg, record);
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
    class LogWorkerHandler implements WorkHandler<LogEvent> {

        private String name;

        public LogWorkerHandler(String name) {
            this.name = name;
        }


        @Override
        public void onEvent(LogEvent event) throws Exception {
            logger.info("{}->记录日志:{}", name, event.logRecord.toString());
        }
    }

    class LogEvent<LogRecord> implements Serializable {
        private LogRecord logRecord;
    }
}
