package io.lance.gradle.common.web.interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @desc: 权限拦截
 * @author: lance
 * @time: 2017-09-15 11:38
 */
public class PermissionInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("权限拦截控制");
        return super.preHandle(request, response, handler);
    }
}
