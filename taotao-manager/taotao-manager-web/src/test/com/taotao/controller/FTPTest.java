package com.taotao.controller;

import com.taotao.common.utils.FtpUtil;
import org.apache.commons.net.ftp.FTPClient;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Taoxiao on 2017/6/20 0020.
 * Describe:
 */
public class FTPTest {
    @Test
    public void testFtp() {
        //1、连接ftp服务器
        try {
            FTPClient ftpClient = new FTPClient();
            ftpClient.connect("119.29.162.189", 21);
            //2、登录ftp服务器
            ftpClient.login("ftpuser", "txf123456");
            //3、读取本地文件
            FileInputStream inputStream = new FileInputStream(new File("F:\\公司\\1.jpg"));
            //4、上传文件
            //1）指定上传目录
            ftpClient.changeWorkingDirectory("/home/ftpuser/images");
            //2）指定文件类型
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
            //ftpClient.setFileTransferMode(ftpClient.BINARY_FILE_TYPE);
            ftpClient.enterLocalPassiveMode();//Switch to passive mode //PASV模式
            //第一个参数：文件在远程服务器的名称
            //第二个参数：文件流
            ftpClient.storeFile("h31.jpg", inputStream);
            //5、退出登录
            ftpClient.logout();
        }catch (Exception e){
            System.out.println(e.toString());
            System.out.println("--------------------");
            System.out.println(e.getMessage());
            System.out.println("--------------------");
            e.printStackTrace();
        }
    }



    @Test
    public void testFtpUtil() throws Exception{
        FileInputStream inputStream = new FileInputStream(new File("F:\\公司\\1.jpg"));
        FtpUtil.uploadFile("119.29.162.189",21,"ftpuser", "txf123456","/home/ftpuser/images",
                "/2017/11/01","h1.jpg",inputStream);
    }
}

