package io.lance.gradle.common.message.util;

/**
 * @author lance.
 * @time: 2018-04-20 15:58
 * @desc:
 */
public enum MessageEnum {

    SMS("1"), EMAIL("2"), SITE("0");

    private String code;

    MessageEnum(String code) {
        this.code = code;
    }
}
