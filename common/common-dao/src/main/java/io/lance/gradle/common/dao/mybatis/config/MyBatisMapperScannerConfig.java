package io.lance.gradle.common.dao.mybatis.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lance.
 * @time: 2017-11-22 17:00
 * @desc:
 */
@Configuration
@AutoConfigureAfter(MyBatisTransactionConfig.class)// 因为这个对象的扫描，需要在MyBatisTransactionConfig的后面注入，所以加上下面的注解
public class MyBatisMapperScannerConfig {

    private static final Logger logger = LogManager.getLogger();


    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        //获取之前注入的beanName为sqlSessionFactory的对象
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        //指定xml配置文件的路径
        mapperScannerConfigurer.setBasePackage("io.lance.gradle.common.dao.modules.**.mapper");
        return mapperScannerConfigurer;
    }
}
