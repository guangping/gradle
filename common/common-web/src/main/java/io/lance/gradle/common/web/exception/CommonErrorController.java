package io.lance.gradle.common.web.exception;


import io.lance.gradle.common.core.util.Constants;
import io.lance.gradle.common.core.util.JsonResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lance
 * @desc: 覆盖springboot默认异常处理
 * @time: 2017-11-17 15:16:45
 */
@Controller
@RequestMapping("/error")
public class CommonErrorController implements ErrorController {

    private static final Logger logger = LogManager.getLogger(CommonErrorController.class);

    @RequestMapping(produces = "text/html")
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView view = new ModelAndView();
        view.setViewName("error/errorPage");
        int statusCode = response.getStatus();
        if (Constants.STATUS_CODE_404 == statusCode) {
            view.setViewName("error/404");
        }
        return view;
    }

    @RequestMapping
    @ResponseBody
    public JsonResult error(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();

        return jsonResult;
    }

    @Override
    public String getErrorPath() {
        return "error/errorPage";
    }
}
