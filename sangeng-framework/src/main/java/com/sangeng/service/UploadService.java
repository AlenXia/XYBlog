package com.sangeng.service;

import com.sangeng.domain.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author AlenXia
 * @Date 2022/12/8 11:14
 * @Description
 */
public interface UploadService {
    /**
     * 上传图片文件
     * @param file
     * @return
     */
    ResponseResult uploadImg(MultipartFile file);
}
