package io.lance.gradle.common.job.elastic.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.google.common.collect.Lists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author lance.
 * @time: 2018-05-08 17:46
 * @desc: 自定义流式人物
 */
public class MyDataFlowJob implements DataflowJob<String> {

    private static final Logger logger = LogManager.getLogger();


    @Override
    public List<String> fetchData(ShardingContext shardingContext) {
        logger.info("数据抓取");

        List<String> list = Lists.newArrayList();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        return list;
    }

    @Override
    public void processData(ShardingContext context, List<String> list) {
        logger.info("数据处理");

    }
}
