package io.lance.gradle.common.job.elastic.config;

import com.dangdang.ddframe.job.api.dataflow.DataflowJob;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import io.lance.gradle.common.job.elastic.job.MyDataFlowJob;
import io.lance.gradle.common.job.elastic.job.MyJob;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

/**
 * @author lance.
 * @time: 2018-05-08 17:39
 * @desc:
 */
@Configuration
public class SimpleJobConfig {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private ZookeeperRegistryCenter registryCenter;

    @Bean
    public SimpleJob simpleJob() {
        return new MyJob();
    }

    @Bean
    public DataflowJob dataflowJob() {
        return new MyDataFlowJob();
    }

    @Bean(initMethod = "init")
    public JobScheduler simpleJobScheduler(final SimpleJob simpleJob) {
        String cron="0/10 * * * * ? *",shardingItemParameters="1=name,2=sex,0=age";
        int shardingTotalCount=3;
        return new SpringJobScheduler(simpleJob, registryCenter, getLiteJobConfiguration(simpleJob.getClass(), cron, shardingTotalCount, shardingItemParameters));

        // return new SpringJobScheduler(simpleJob, regCenter, getLiteJobConfiguration(simpleJob.getClass(), cron, shardingTotalCount, shardingItemParameters), jobEventConfiguration);
    }

    /* 作业配置
     * 作业配置分为3级，分别是JobCoreConfiguration，JobTypeConfiguration和LiteJobConfiguration。
     * LiteJobConfiguration使用JobTypeConfiguration，JobTypeConfiguration使用JobCoreConfiguration，层层嵌套。
     * JobTypeConfiguration根据不同实现类型分为SimpleJobConfiguration，DataflowJobConfiguration和ScriptJobConfiguration。
     * */
    private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends SimpleJob> jobClass, final String cron, final int shardingTotalCount, final String shardingItemParameters) {
        return LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder(
                jobClass.getName(), cron, shardingTotalCount).shardingItemParameters(shardingItemParameters).build(), jobClass.getCanonicalName())).overwrite(true).build();
    }

    /**
     * 动态添加任务
     *
     * @author lance
     * @date 2018-05-08 17:55:04
     */
/*    public void test() {
        int shardingTotalCount = 2;
        String jobName = UUID.randomUUID().toString() + "-test123";
        JobCoreConfiguration jobCoreConfiguration = JobCoreConfiguration.newBuilder(jobName, "* * * * * ?", shardingTotalCount).shardingItemParameters("0=A,1=B").build();

        SimpleJobConfiguration simpleJobConfiguration = new SimpleJobConfiguration(jobCoreConfiguration, MyJob.class.getCanonicalName());

        JobScheduler jobScheduler = new JobScheduler(regCenter, LiteJobConfiguration.newBuilder(simpleJobConfiguration).build());
        try {
            jobScheduler.init();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("定时任务创建失败");
        }
    }*/
}
