package io.lance.gradle.common.core.codec;

import io.lance.gradle.common.core.util.Constants;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author lance.
 * @time: 2018-06-07 17:16
 * @desc:
 */
public class CodecUtils {


    /**
     * md5 加密
     *
     * @author lance
     * @date 2018-06-07 17:17:09
     */
    public static String md5(String str) {
        return DigestUtils.md5Hex(str);
    }

    public static String sha(String str) {
        return DigestUtils.shaHex(str);
    }

    /**
     * base64加密
     *
     * @author lance
     * @date 2018-06-07 17:19:16
     */
    public static String encodeBase64(String str) {
        try {
            byte[] b = Base64.encodeBase64(str.getBytes(Constants.CHARSET), true);
            return new String(b);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * base64 解密
     *
     * @author lance
     * @date 2018-06-07 17:19:16
     */
    public static String decodeBase64(String str) {
        try {
            byte[] b = Base64.decodeBase64(str.getBytes(Constants.CHARSET));
            return new String(b);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
