package io.lance.gradle.common.web.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 * @desc: 国际化服务
 * @author: lance
 * @time: 2017-09-13 14:27:43
 */
@Service
public class I18nService {

    @Autowired
    private MessageSource messageSource;


    private Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }

    public String getMessage(String code) {
        return messageSource.getMessage(code, null, getLocale());
    }

    public String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, getLocale());
    }

    public String getMessage(String code, String defaultMessage) {
        return messageSource.getMessage(code, null, defaultMessage, getLocale());
    }

    public String getMessage(String code, String defaultMessage, Object[] args) {
        return messageSource.getMessage(code, args, defaultMessage, getLocale());
    }

}
