package io.lance.gradle.common.file.controller;

import io.lance.gradle.common.core.util.JsonResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Lance.
 * @time: 2017-12-25 15:52
 * @desc:
 */
@RestController
@RequestMapping("/upload")
public class UploadController {
    private static final Logger logger = LogManager.getLogger();

    /**
     * 文件上传
     */
    public JsonResult upload(@RequestParam(value = "Filedata") MultipartFile file) {
        logger.info("文件上传.....");

        //处理文件上传逻辑

        return null;
    }
}
