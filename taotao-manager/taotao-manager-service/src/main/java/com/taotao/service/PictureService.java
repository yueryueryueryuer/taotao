package com.taotao.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Created by Taoxiao on 2017/6/21 0021.
 * Describe:图片上传接口
 */
public interface PictureService {
    /**
     * 图片上传
     * @param uploadFile
     * @return
     */
    Map uploadPicture(MultipartFile uploadFile);
}
