package io.lance.gradle.common.web.interceptor;


import io.lance.gradle.common.web.annotation.GuestPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Author: Lance.
 * Date: 2017-09-14 14:15
 * Desc:
 */
public class GuestPageInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LogManager.getLogger();

    private static final NamedThreadLocal<Long> endStartLocal =
            new NamedThreadLocal<Long>("end-start-local");

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("免登录拦截器....");
        endStartLocal.set(System.currentTimeMillis());
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            GuestPage annotation = handlerMethod.getMethodAnnotation(GuestPage.class);

        }

        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long endTime = System.currentTimeMillis();
        long startTime = endStartLocal.get();
        long executeTime = endTime - startTime;
        logger.info("[{}] executeTime : {} ms", handler, executeTime);
        if (executeTime > 1000) {
            logger.error("此服务耗时过长,请关注并优化,url:{},[{}] executeTime : {} ms", request.getRequestURI(), handler, executeTime);
        }

        super.afterCompletion(request, response, handler, ex);
    }
}
