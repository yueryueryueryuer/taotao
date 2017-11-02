package com.taotao.service.impl;

import com.taotao.common.utils.FtpUtil;
import com.taotao.common.utils.IDUtils;
import com.taotao.service.PictureService;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Taoxiao on 2017/6/21 0021.
 * Describe:图片上传服务
 */
@Service
public class PictureServiceImpl implements PictureService {
    //在applicationContext-dao.xml配置了properties，使用@Value就可以注入到这里
    @Value("${FTP_ADDRESS}")
    private String FTP_ADDRESS;
    @Value("${FTP_PORT}")
    private Integer FTP_PORT;
    @Value("${FTP_USERNAME}")
    private String FTP_USERNAME;
    @Value("${FTP_PASSWORD}")
    private String FTP_PASSWORD;
    @Value("${FTP_BASEPATH}")
    private String FTP_BASEPATH;
    @Value("${IMAGE_BASE_URL}")
    private String IMAGE_BASE_URL;

    @Override
    public Map uploadPicture(MultipartFile uploadFile) {
        Map resultMap = new HashMap<>();
        resultMap.put("error",1);
        try {
            //生成一个新的文件名
            //取原始文件名
            String oldName = uploadFile.getOriginalFilename();
            //生成新文件名
            //UUID.randomUUID();
            String newName = IDUtils.genImageName();
            newName=newName + oldName.substring(oldName.lastIndexOf("."));
            //图片上传
            String imagePath=new DateTime().toString("/yyyy/MM/dd");
            boolean result = FtpUtil.uploadFile(FTP_ADDRESS,FTP_PORT,FTP_USERNAME,FTP_PASSWORD,FTP_BASEPATH,
                    imagePath,newName,uploadFile.getInputStream());
            if(!result){
                resultMap.put("error",1);
                resultMap.put("message","文件上传失败");
                return resultMap;
            }
            resultMap.put("error",0);
            resultMap.put("url",IMAGE_BASE_URL+imagePath+"/"+newName);
            return resultMap;
        } catch (IOException e) {
            resultMap.put("error",1);
            resultMap.put("message","文件上传发生异常");
            return resultMap;
        }
    }
}
