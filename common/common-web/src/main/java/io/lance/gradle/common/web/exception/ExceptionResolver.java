package io.lance.gradle.common.web.exception;

import com.alibaba.fastjson.JSONObject;
import io.lance.gradle.common.core.exception.EbsException;
import io.lance.gradle.common.core.spring.context.SpringApplicationContext;
import io.lance.gradle.common.core.util.JsonResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

/**
 * Author: Lance.
 * Date: 2017-09-13 15:24
 * Desc: 异常处理
 */
@ControllerAdvice
public class ExceptionResolver extends SimpleMappingExceptionResolver {

    private static final Logger logger = LogManager.getLogger(ExceptionResolver.class);

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        String viewName = determineViewName(ex, request);
        ex.printStackTrace();
        logger.error("Resolving exception from handler ：[{}]", handler);
        try {
            if (handler instanceof HandlerMethod) {
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                //返回json的情况
                ResponseBody responseBody = handlerMethod.getMethodAnnotation(ResponseBody.class);
                if (null != responseBody) {
                    handleException(ex, response);
                    return null;
                }
                Class<?> beanType = handlerMethod.getBeanType();
                responseBody = beanType.getAnnotation(ResponseBody.class);
                RestController restController = beanType.getAnnotation(RestController.class);
                if (null != responseBody || null != restController) {
                    handleException(ex, response);
                    return null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ModelAndView view = new ModelAndView();
        view.setViewName(ExceptionPage.DEFAULT_PAGE);
        if (ex instanceof EbsException) {
            EbsException ebs = (EbsException) ex;
            view.addObject("ex", ebs);
            view.setViewName(ExceptionPage.PAGE_MSG);
        }
        return view;
    }

    private void handleException(Exception ex, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();
        JsonResult jsonBean = new JsonResult();
        MessageSource messageSource = SpringApplicationContext.getBean(MessageSource.class);
        //获取当前语言环境
        Locale locale = LocaleContextHolder.getLocale();
        String message = messageSource.getMessage("common_error", null, "server error", locale);

        jsonBean.setMessage(message);
        if (ex instanceof EbsException) {
            EbsException ebs = (EbsException) ex;
            jsonBean.setCode(ebs.getErrCode());
            jsonBean.setMessage(ex.getMessage());
        }
        writer.write(JSONObject.toJSONString(jsonBean));
        writer.flush();
        writer.close();
    }
}
