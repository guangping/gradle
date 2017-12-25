package io.lance.gradle.common.core.util;

import java.util.Random;

/**
 * @desc: 随机字符串
 * @author: lance
 * @time: 2017-09-21 17:24:20
 */
public class RandomUtil {

    private static final String TEMPLATE_STRING = "qwertyuiopasdfghjklzxcvbnm1234567890";

    private static final String TEMPLATE_STRING_WITH_UNUSUAL = "qwertyuiopasdfghjklzxcvbnm1234567890@!$#%^&*?<>(){}[]=,.|':";

    /**
     * @desc: 获取32位随机字符串
     * @author: lance
     * @time: 2017-09-21 17:24:39
     */
    public static String getRandomStr() {
        StringBuffer s = new StringBuffer(32);
        for (int i = 0; i < 32; i++) {
            s.append(TEMPLATE_STRING.charAt(getRandomNum()));
        }
        return s.toString().toUpperCase();
    }

    public static String getRandomStrWithUnusual() {
        StringBuffer s = new StringBuffer(32);
        for (int i = 0; i < 32; i++) {
            s.append(TEMPLATE_STRING_WITH_UNUSUAL.charAt(getRandomNumWithUnusual()));
        }
        return s.toString().toUpperCase();
    }

    /**
     * @desc: 获取0-35之间的随机数
     * @author: lance
     * @time: 2017-09-21 17:24:48
     */
    private static Integer getRandomNum() {
        return new Random().nextInt(35);
    }

    private static Integer getRandomNumWithUnusual() {
        return new Random().nextInt(58);
    }

}
