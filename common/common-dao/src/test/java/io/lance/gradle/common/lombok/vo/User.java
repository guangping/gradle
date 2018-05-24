package io.lance.gradle.common.lombok.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author lance.
 * @time: 2018-05-24 16:25
 * @desc:
 */
@Data
public class User implements Serializable {

    private String name;

    private Integer age;

    private char sex;



}
