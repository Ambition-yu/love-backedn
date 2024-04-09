package com.study.lovetoolbox.controller;

import com.study.lovetoolbox.common.BaseResponse;
import com.study.lovetoolbox.common.ResultUtils;
import com.study.lovetoolbox.manager.FileManager;
import com.study.lovetoolbox.model.dto.file.UploadFileDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 文件接口
 *
 */
@RestController
@RequestMapping("/file")
@Slf4j
public class FileController {

    @Resource
    private FileManager fileManager;

    /**
     * 文件上传
     *
     * @param multipartFile
     * @param uploadFileRequest
     * @param request
     * @return
     */
    @PostMapping("/upload")
    public BaseResponse<String> uploadFile(@RequestPart("file") MultipartFile multipartFile,
                                           UploadFileDTO uploadFileRequest, HttpServletRequest request) {

        return ResultUtils.success(fileManager.upload(multipartFile,uploadFileRequest));
    }
}
