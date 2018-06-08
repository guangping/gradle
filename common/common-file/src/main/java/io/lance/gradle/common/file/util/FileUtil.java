package io.lance.gradle.common.file.util;

import io.lance.gradle.common.core.codec.CodecUtils;
import io.lance.gradle.common.core.util.Assert;
import io.lance.gradle.common.core.util.Constants;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Lance.
 * @time: 2018-01-23 15:29
 * @desc:
 */
public class FileUtil {
    private static final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getLogger();

    public static final long M = 1048576;
    public static final long B = 1024;


    /**
     * 获取文件 size
     *
     * @author lance
     * @date 2018-01-23 15:31:49
     */
    public static String getFileSize(long fileSize) {
        String sizeStr = String.valueOf(fileSize + "B");
        if (fileSize > M) {
            sizeStr = (fileSize / M) + "M";
            return sizeStr;
        }
        if (fileSize > B) {
            sizeStr = (fileSize / B) + "K";
        }
        return sizeStr;
    }

    /**
     * 文件上传路径
     *
     * @author lance
     * @date 2018-06-07 17:50:43
     */
    public static List<String> getFilePath() {
        String year = DateFormatUtils.format(System.currentTimeMillis(), Constants.YEAR);
        String month = DateFormatUtils.format(System.currentTimeMillis(), Constants.MONTH);
        String day = DateFormatUtils.format(System.currentTimeMillis(), Constants.DAY);

        List<String> dirs = Stream.of(year, month, day).collect(Collectors.toList());

        return dirs;
    }

    public static String getFileName(String fileId, String extension) {
        Assert.isBlank(extension, "文件扩展名不存在");
        if (StringUtils.isBlank(fileId)) {
            fileId = getFileId();
        }
        StringBuffer buffer = new StringBuffer(64);
        buffer.append(fileId);
        buffer.append(".");
        buffer.append(extension);

        return buffer.toString();
    }

    /**
     * 根据文件后缀 生成新的文件名
     *
     * @author lance
     * @date 2018-06-07 17:06:23
     */
    public static String getFileName(String extension) {
        Assert.isBlank(extension, "文件扩展名不存在");

        String fileId = getFileId();
        return getFileName(fileId, extension);
    }

    /**
     * 生成文件编码
     *
     * @author lance
     * @date 2018-06-07 17:09:50
     */
    public static String getFileId() {
        StringBuffer buffer = new StringBuffer(100);

        String time = DateFormatUtils.format(System.currentTimeMillis(), Constants.DATETIME_FORMAT_SS);
        buffer.append(UUID.randomUUID().toString());
        buffer.append(time);
        buffer.append(System.nanoTime());

        return CodecUtils.md5(buffer.toString());
    }

}
