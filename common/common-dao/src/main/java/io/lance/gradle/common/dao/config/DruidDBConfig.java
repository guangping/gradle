package io.lance.gradle.common.dao.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @desc: druid配置
 * @author: lance
 * @time: 2017-09-15 16:34
 */
@Component
@ConfigurationProperties(prefix = "spring.datasource")
public class DruidDBConfig {

    private static final Logger logger = LogManager.getLogger();

    private String url;

    private String username;

    private String password;

    private String driverClassName;

    private int initialSize;

    private int minIdle;

    private int maxActive;

    private int maxWait;

    private int timeBetweenEvictionRunsMillis;

    private int minEvictableIdleTimeMillis;

    private String validationQuery;

    private boolean testWhileIdle;

    private boolean testOnBorrow;

    private boolean testOnReturn;


    private boolean poolPreparedStatements;


    private int maxPoolPreparedStatementPerConnectionSize;

    private String filters;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }

    public void setInitialSize(int initialSize) {
        this.initialSize = initialSize;
    }

    public void setMinIdle(int minIdle) {
        this.minIdle = minIdle;
    }

    public void setMaxActive(int maxActive) {
        this.maxActive = maxActive;
    }

    public void setMaxWait(int maxWait) {
        this.maxWait = maxWait;
    }

    public void setTimeBetweenEvictionRunsMillis(int timeBetweenEvictionRunsMillis) {
        this.timeBetweenEvictionRunsMillis = timeBetweenEvictionRunsMillis;
    }

    public void setMinEvictableIdleTimeMillis(int minEvictableIdleTimeMillis) {
        this.minEvictableIdleTimeMillis = minEvictableIdleTimeMillis;
    }

    public void setValidationQuery(String validationQuery) {
        this.validationQuery = validationQuery;
    }

    public void setTestWhileIdle(boolean testWhileIdle) {
        this.testWhileIdle = testWhileIdle;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public void setTestOnReturn(boolean testOnReturn) {
        this.testOnReturn = testOnReturn;
    }

    public void setPoolPreparedStatements(boolean poolPreparedStatements) {
        this.poolPreparedStatements = poolPreparedStatements;
    }

    public void setMaxPoolPreparedStatementPerConnectionSize(int maxPoolPreparedStatementPerConnectionSize) {
        this.maxPoolPreparedStatementPerConnectionSize = maxPoolPreparedStatementPerConnectionSize;
    }

    public void setFilters(String filters) {
        this.filters = filters;
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public int getInitialSize() {
        return initialSize;
    }

    public int getMinIdle() {
        return minIdle;
    }

    public int getMaxActive() {
        return maxActive;
    }

    public int getMaxWait() {
        return maxWait;
    }

    public int getTimeBetweenEvictionRunsMillis() {
        return timeBetweenEvictionRunsMillis;
    }

    public int getMinEvictableIdleTimeMillis() {
        return minEvictableIdleTimeMillis;
    }

    public String getValidationQuery() {
        return validationQuery;
    }

    public boolean isTestWhileIdle() {
        return testWhileIdle;
    }

    public boolean isTestOnBorrow() {
        return testOnBorrow;
    }

    public boolean isTestOnReturn() {
        return testOnReturn;
    }

    public boolean isPoolPreparedStatements() {
        return poolPreparedStatements;
    }

    public int getMaxPoolPreparedStatementPerConnectionSize() {
        return maxPoolPreparedStatementPerConnectionSize;
    }

    public String getFilters() {
        return filters;
    }

    @Override
    public String toString() {
        return "DruidDBConfig{" +
                "url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", driverClassName='" + driverClassName + '\'' +
                ", initialSize=" + initialSize +
                ", minIdle=" + minIdle +
                ", maxActive=" + maxActive +
                ", maxWait=" + maxWait +
                ", timeBetweenEvictionRunsMillis=" + timeBetweenEvictionRunsMillis +
                ", minEvictableIdleTimeMillis=" + minEvictableIdleTimeMillis +
                ", validationQuery='" + validationQuery + '\'' +
                ", testWhileIdle=" + testWhileIdle +
                ", testOnBorrow=" + testOnBorrow +
                ", testOnReturn=" + testOnReturn +
                ", poolPreparedStatements=" + poolPreparedStatements +
                ", maxPoolPreparedStatementPerConnectionSize=" + maxPoolPreparedStatementPerConnectionSize +
                ", filters='" + filters + '\'' +
                '}';
    }
}
