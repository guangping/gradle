package io.lance.gradle.common.shiro.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author lance.
 * @time: 2018-04-25 16:35
 * @desc: shiro 配置
 */
@Configuration
public class ShiroConfig {

    private static final Logger logger = LogManager.getLogger();

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        factoryBean.setSecurityManager(securityManager);

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        factoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        factoryBean.setSuccessUrl("/index");
        // 未授权界面;
        factoryBean.setUnauthorizedUrl("/403");

        //自定义拦截器 TODO 可实现自定义功能
        Map<String, Filter> filtersMap = new LinkedHashMap<String, Filter>();
        //限制同一帐号同时在线的个数。
        //filtersMap.put("kickout", kickoutSessionControlFilter());
        factoryBean.setFilters(filtersMap);


        //拦截器.
        Map<String, String> definitionMap = new LinkedHashMap<String, String>();
        definitionMap.put("/static/**", "anon");
        definitionMap.put("/resources/**", "anon");
        definitionMap.put("/anon/**", "anon");
        definitionMap.put("/logout", "logout");//配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
        /*authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问*/
        definitionMap.put("/**", "authc");
        factoryBean.setFilterChainDefinitionMap(definitionMap);

        return factoryBean;
    }

}
