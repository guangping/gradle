package io.lance.gradle.common.dao.mybatis.config;

import com.google.common.collect.Lists;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.List;

/**
 * mybatis 相关配置
 *
 * @author Lance.
 * @time: 2017-11-22 16:57
 * @desc:
 */
@Configuration
@EnableTransactionManagement //事物支持
public class MyBatisTransactionConfig implements TransactionManagementConfigurer {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MyBatisConfig myBatisConfig;

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
        return transactionManager;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactoryBean() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        try {
            String configLocation = myBatisConfig.getConfigLocation();
            ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
            Resource configResource = resolver.getResource(configLocation);
            bean.setConfigLocation(configResource);

            String typeAliasesPackage = myBatisConfig.getTypeAliasesPackage();
            bean.setTypeAliasesPackage(typeAliasesPackage);

            String[] mapperLocations = myBatisConfig.getMapperLocations();
            List<Resource> list = Lists.newArrayList();
            for (String location : mapperLocations) {
                Resource[] resources = resolver.getResources(location);
                list.addAll(Arrays.asList(resources));
            }
            bean.setMapperLocations(list.toArray(new Resource[]{}));

            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
