package com.sangeng.controller;

import com.sangeng.domain.ResponseResult;
import com.sangeng.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author AlenXia
 * @Date 2022/12/8 11:10
 * @Description
 */
@RestController
public class UploadController {

    @Autowired
    private UploadService uploadService;

    /**
     * 上传图片文件
     * @param img
     * @return
     */
    @PostMapping("/upload")
    public ResponseResult uploadImg(MultipartFile img) {
        return uploadService.uploadImg(img);
    }
}
