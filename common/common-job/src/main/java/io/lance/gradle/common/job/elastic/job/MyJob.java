package io.lance.gradle.common.job.elastic.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author lance.
 * @time: 2018-05-08 17:44
 * @desc: 自定义任务
 */
public class MyJob implements SimpleJob {

    private static final Logger logger = LogManager.getLogger();


    @Override
    public void execute(ShardingContext content) {
        System.out.println("JobName:" + content.getJobName());
        System.out.println("JobParameter:" + content.getJobParameter());
        System.out.println("ShardingItem:" + content.getShardingItem());
        System.out.println("ShardingParameter:" + content.getShardingParameter());
        System.out.println("ShardingTotalCount:" + content.getShardingTotalCount());
        System.out.println("TaskId:" + content.getTaskId());
        System.out.println("---------------------------------------");
    }
}
