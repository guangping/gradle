package io.lance.gradle.common.dao.druid;


import io.lance.gradle.common.dao.config.DruidDBConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @desc: dataSource配置
 * @author: lance
 * @time: 2017-10-24 11:48
 */
@Configuration
public class DruidDataSource {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private DruidDBConfig druidDBConfig;

    @Primary
    @Bean("dataSource")
    public DataSource dataSource() {
        logger.info("初始化 dataSource .....");

        com.alibaba.druid.pool.DruidDataSource datasource = new com.alibaba.druid.pool.DruidDataSource();

        datasource.setUrl(this.druidDBConfig.getUrl());
        datasource.setUsername(this.druidDBConfig.getUsername());
        datasource.setPassword(this.druidDBConfig.getPassword());
        datasource.setDriverClassName(this.druidDBConfig.getDriverClassName());

        //configuration
        datasource.setInitialSize(this.druidDBConfig.getInitialSize());
        datasource.setMinIdle(this.druidDBConfig.getMinIdle());
        datasource.setMaxActive(this.druidDBConfig.getMaxActive());
        datasource.setMaxWait(this.druidDBConfig.getMaxWait());
        datasource.setTimeBetweenEvictionRunsMillis(this.druidDBConfig.getTimeBetweenEvictionRunsMillis());
        datasource.setMinEvictableIdleTimeMillis(this.druidDBConfig.getMinEvictableIdleTimeMillis());
        datasource.setValidationQuery(this.druidDBConfig.getValidationQuery());
        datasource.setTestWhileIdle(this.druidDBConfig.isTestWhileIdle());
        datasource.setTestOnBorrow(this.druidDBConfig.isTestOnBorrow());
        datasource.setTestOnReturn(this.druidDBConfig.isTestOnReturn());
        datasource.setPoolPreparedStatements(this.druidDBConfig.isPoolPreparedStatements());
        datasource.setMaxPoolPreparedStatementPerConnectionSize(this.druidDBConfig.getMaxPoolPreparedStatementPerConnectionSize());
        try {
            datasource.setFilters(this.druidDBConfig.getFilters());
        } catch (SQLException e) {
            logger.error("druid configuration initialization filter", e);
        }
        return datasource;
    }

}
