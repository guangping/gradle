package io.lance.gradle.common.core.exception;

import io.lance.gradle.common.core.spring.context.SpringApplicationContext;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * @author lance
 * @desc: 系统异常
 * @time: 2017-11-14 17:42:59
 */
public class EbsException extends RuntimeException {

    private static final Logger logger = LogManager.getLogger(EbsException.class);

    /**
     * 错误编码
     **/
    private Integer errCode;

    /**
     * @desc: 描述信息
     * @author: lance
     * @time: 2017-09-13 15:00:38
     */
    private String message;

    /**
     * 文本替换的参数
     */
    private String[] args;

    /**
     * 国际化code
     */
    private String i18nCode;


    public EbsException(Integer errCode, String message, String[] args) {
        this.errCode = errCode;
        this.message = message;
        this.args = args;
    }

    public EbsException(String message) {
        super(message);
        this.message = message;
    }

    public EbsException(String message, String i18nCode) {
        super(message);
        this.message = message;
        this.i18nCode = i18nCode;
    }

    public EbsException(Throwable cause) {
        super(cause);
    }

    public EbsException(String message, String... args) {
        this.message = message;
        this.args = args;
    }

    @Override
    public String getMessage() {
        try {
            if (StringUtils.isNotBlank(i18nCode)) {
                MessageSource messageSource = SpringApplicationContext.getBean(MessageSource.class);
                //获取当前语言环境
                Locale locale = LocaleContextHolder.getLocale();
                String message = messageSource.getMessage(i18nCode, null, locale);
                if (StringUtils.isNotBlank(message)) {
                    return message;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.getMessage();
    }

    public Integer getErrCode() {
        return errCode;
    }


    public String getI18nCode() {
        return i18nCode;
    }

    public void setI18nCode(String i18nCode) {
        this.i18nCode = i18nCode;
    }

    public String[] getArgs() {
        return args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }
}
