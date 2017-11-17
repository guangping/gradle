package io.lance.gradle.common.web.annotation;

import java.lang.annotation.*;


/**
 * @desc: GuestPage注解的定义，添加此注解则可以不登陆就能访问
 * @author: lance
 * @time: 2017-09-14 14:16:51
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface GuestPage {

}
