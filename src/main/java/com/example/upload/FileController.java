package com.example.upload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.example.upload.Common.*;

/**
 * @author liurui
 * @date 2020/11/23
 */

@RestController
@RequestMapping(value = "/file")
@Slf4j
public class FileController {


    @Resource
    BaseFileService baseFileService;


    @PostMapping(value = "/v3.1/upload/{fileType}")
    public Object uploadFileResume(@Valid MultipartFileParam param,
                                   @PathVariable String fileType) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS"));
        String fileUploadDir;
        //文件需要改动名称
        switch (fileType) {
            case DOCKERFILE:
            case IMAGE:
            case WAR:
                fileUploadDir = UPLOAD_BASE_PATH + fileType + LINUX_FILE_SEPARATOR + timestamp;
                break;
            case PICTURE:
                fileUploadDir = UPLOAD_BASE_PATH + fileType;
                break;
            case LOGO:
            case LICENSE:
            case ARTIFACTORY:
            case PACKAGE:
            case HELM:
                fileUploadDir = UPLOAD_BASE_PATH + fileType;
                break;
            default:
                return "DataResponse.error(BaseFileExceptionCode.FILE_TYPE_ERROR)";
        }
        File fileDir = new File(fileUploadDir);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        log.info("file upload begin...");
        baseFileService.chunkUpload(fileUploadDir, param.getFile(), param.getChunk(), param.getChunkTotal());
        return "DataResponse.success()";

    }
}
