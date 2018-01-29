package io.lance.gradle.common.file.service;

import io.lance.gradle.common.file.vo.EbsFile;

import java.io.File;

/**
 * @author Lance.
 * @time: 2018-01-23 14:01
 * @desc: 文件上传 下载
 */
public interface FileService {

    /**
     * 文件上传
     *
     * @param src 源文件地址
     * @author lance
     * @date 2018-01-23 15:22:14
     */
    EbsFile put(String src);


    /**
     * 文件上传
     *
     * @param file 文件
     * @author lance
     * @date 2018-01-23 15:54:16
     */
    EbsFile put(File file);


}
