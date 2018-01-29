package io.lance.gradle.common.file.vo;

import java.io.Serializable;

/**
 * @author Lance.
 * @time: 2018-01-23 14:02
 * @desc: 文件
 */
public class EbsFile implements Serializable {

    // 文件编码
    private String fileId;

    //文件名称
    private String fileName;

    //大小 字节
    private long fileSize = 0;

    //文件类型
    private String fileType;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Override
    public String toString() {
        return "EbsFile{" +
                "fileId='" + fileId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileSize=" + fileSize +
                ", fileType='" + fileType + '\'' +
                '}';
    }
}
