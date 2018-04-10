package io.lance.gradle.common.core.util;

/**
 * @author lance
 * @desc: 常量类
 * @time: 2017-11-10 17:22:03
 */
public interface Constants {

    String CHARSET = "UTF-8";

    Integer SUCCESS_CODE = 1;

    Integer FAIL_CODE = 0;

    String YES = "1";

    String NO = "0";

    boolean SUCCESS = true;

    boolean FALSE = false;

    /**
     * 日期格式化格式
     **/
    String DATE_FORMAT = "yyyy-MM-dd";
    /**
     * 时间格式化格式
     **/
    String DATETIME_FORMAT_MM = "yyyy-MM-dd HH:mm";

    String DATETIME_FORMAT_SS = "yyyy-MM-dd HH:mm:ss";

    String TIME_FORMAT = "HH:mm:ss";
    /**
     * 日期格式化格式
     **/
    String DATE_FORMAT_CN = "yyyy年MM月dd日";
    /**
     * 时间格式化格式
     **/
    String DATETIME_FORMAT_CN = "yyyy年MM月dd日 HH时mm分";

    String YEAR = "yyyy";

    String MONTH = "MM";

    String DAY = "dd";


    /**
     * 常用符号
     */
    String COMMA = ",";
    String DOT = ".";
    String COMMA_CN = "，";


    /**
     * MediaType application/json;charset=UTF-8
     */
    String MEDIA_TYPE_JSON = "application/json;charset=UTF-8";

    /**
     * http status code
     */
    int STATUS_CODE_404 = 404;

    int STATUS_CODE_500 = 500;


    /**
     * 来源
     */
    String REFERER = "referer";

    String ERROR="/error";

}
