package io.lance.gradle.common.job.elastic.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;

/**
 * @author lance.
 * @time: 2018-05-09 16:39
 * @desc:
 */
public class MyJob2 implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        System.out.println("任务2");
    }
}
