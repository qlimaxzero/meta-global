/**
 * @description: oss service 服务
 * @author: Donugh
 * @create: 2023-05-15 00:27
 **/
package com.shitouren.core.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

public interface OssService {
    //上传文件到oss
    String uploadFile(MultipartFile file) throws IOException;

    String uploadFileX(Integer type, String fileName, InputStream is);

}
