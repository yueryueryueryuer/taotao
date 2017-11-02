package com.taotao.controller;

import com.taotao.common.utils.JsonUtils;
import com.taotao.service.PictureService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Taoxiao on 2017/6/21 0021.
 * Describe:接收页面传递过来的图片。调用service上传到图片服务器。返回结果json
 */
@Controller
public class PictureController {

    @Resource
    private PictureService pictureService;

    /**
     * 返回图片上传的路径，转化为json格式，拦截的路径kingEditorParams中配好
     * @param uploadFile kingEditorParams传来的参数 就叫uploadFile
     * @return json error->0 url->XXX
     */
    @RequestMapping("/pic/upload")
    @ResponseBody
    public String pictureUpload(MultipartFile uploadFile){
        Map result = pictureService.uploadPicture(uploadFile);
        //为了保证功能的兼容性，需要把Result转换为json格式的字符串
        return JsonUtils.objectToJson(result);

    }
}
