package io.lance.gradle.common.core.spring.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

/**
 * @desc: 获取bean
 * @author: lance
 * @time: 2017-09-13 13:56:14
 */
@Service
public class SpringApplicationContext implements ApplicationContextAware {

    private static ConfigurableApplicationContext applicationContext;

    /**
     *
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringApplicationContext.applicationContext = (ConfigurableApplicationContext) applicationContext;
    }

    /**
     * 获取bean
     *
     * @param name
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        checkApplicationContext();
        return (T) applicationContext.getBean(name);
    }

    /**
     * 获取bean
     *
     * @param clazz
     */
    public static <T> T getBean(Class<T> clazz) {
        checkApplicationContext();
        return (T) applicationContext.getBean(clazz);
    }

    /**
     * @desc:获取bean
     * @author lance
     * @time: 2017-08-08 16:19:01
     */
    public static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }

    /**
     * @desc:检查上下文是否注入
     * @author lance
     * @time: 2017-08-08 16:20:09
     */
    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new RuntimeException("applicaitonContext未注入,请在spring配置文件中定义SpringContextHolder");
        }
    }

    /**
     * @desc:是否单例
     * @author lance
     * @time: 2017-08-08 16:16:21
     */
    public static boolean isSingleton(String name) {
        return applicationContext.isSingleton(name);
    }


}
