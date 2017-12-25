package io.lance.gradle.common.dao.mybatis.interceptor;

import io.lance.gradle.common.core.util.ReflectHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.BaseStatementHandler;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.util.Properties;

/**
 * @desc: mybatis拦截器 可拦截sql,动态拼装实现权限控制
 * @author: lance
 * @time: 2017-10-12 16:50
 * http://www.jb51.net/article/111971.htm
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class MyBatisInterceptor implements Interceptor {

    private static final Logger logger = LogManager.getLogger();

    private static String pageSqlId = "";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        if (target instanceof RoutingStatementHandler) {
            RoutingStatementHandler statementHandler = (RoutingStatementHandler) invocation.getTarget();

            BaseStatementHandler delegate = (BaseStatementHandler) ReflectHelper.getValueByFieldName(statementHandler, "delegate");
            MappedStatement mappedStatement = (MappedStatement) ReflectHelper.getValueByFieldName(delegate, "mappedStatement");

            //对匹配的方法进行sql改写
            if (StringUtils.isNotBlank(pageSqlId) && mappedStatement.getId().matches(pageSqlId)) {
                BoundSql boundSql = delegate.getBoundSql();
                Object parameterObject = boundSql.getParameterObject();

                //设置改变sql  TODO 动态改变sql
                String newSql = "";
                ReflectHelper.setValueByFieldName(boundSql, "sql", newSql);
            }

        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object o) {
        return Plugin.wrap(o, this);
    }

    @Override
    public void setProperties(Properties properties) {
        logger.info("读取参数");
        pageSqlId = properties.getProperty("pageSqlId");

    }
}
