package io.lance.gradle.common.file.service.util;

import io.lance.gradle.common.core.util.Constants;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.File;

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

    public static String getFilePath() {
        String year = DateFormatUtils.format(System.currentTimeMillis(), Constants.YEAR);
        String month = DateFormatUtils.format(System.currentTimeMillis(), Constants.MONTH);
        String day = DateFormatUtils.format(System.currentTimeMillis(), Constants.DAY);

        //FilenameUtils.getExtension();

        StringBuffer buffer = new StringBuffer(100);
        buffer.append(year);
        buffer.append(File.separator);
        buffer.append(month);
        buffer.append(File.separator);
        buffer.append(day);
        buffer.append(File.separator);


        return null;
    }


}
