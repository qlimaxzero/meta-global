package com.shitouren.core.service;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.shitouren.core.config.interceptor.WebContextHolder;
import com.shitouren.core.mp.mapper.FilesMapper;
import com.shitouren.core.utils.OssConstantPropertiesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * @description: oss service 服务
 * @author: Donugh
 * @create: 2023-05-15 00:29
 **/
@Service
@Slf4j
public class OssServiceImpl implements OssService {

    private OSS ossClient;

    @Resource
    private OssConstantPropertiesUtil propertiesUtil;

    @Resource
    private FilesMapper filesMapper;

    @Resource
    private TaskExecutor taskExecutor;

    @PostConstruct
    public void init() {
        ossClient = new OSSClientBuilder().build(propertiesUtil.getEndpoint(),
                propertiesUtil.getKeyId(),
                propertiesUtil.getKeySecret());
    }

    @PreDestroy
    public void destroy() {
        ossClient.shutdown();
    }

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        return uploadOSS(file.getInputStream(), file.getOriginalFilename(), "profile");
    }

    @Override
    public String uploadFileX(Integer type, String fileName, InputStream is) {
        Integer userId = WebContextHolder.get();
        String dir = StrUtil.join(StrUtil.SLASH, "profile", userId, type);
        String path =  uploadOSS(is, fileName, dir);

        taskExecutor.execute(() -> {
            try {
                filesMapper.insert(userId, type, path);
            } catch (Exception e) {
                log.error("", e);
            }
        });
        return path;
    }

    private String uploadOSS(InputStream is, String fileName, String dir) {
        try {
            //获取上传文件输入流
            //获取文件名称
            assert fileName != null;
            String subName = fileName.substring(fileName.lastIndexOf("."));
            //生成新的文件名（下面根据自己需要决定是否使用）
            String fn = System.currentTimeMillis() + RandomUtil.randomNumbers(3);
            String newName = dir + "/" + fn + subName;

            //调用oss方法实现上传
            //参数一：Bucket名称  参数二：上传到oss文件路径和文件名称  比如 /aa/bb/1.jpg 或者直接使用文件名称  参数三：上传文件的流
            ossClient.putObject(propertiesUtil.getBucketName(), newName, is);

            //把上传之后的文件路径返回
            //需要把上传到阿里云路径返回    https://edu-guli-eric.oss-cn-beijing.aliyuncs.com/1.jpg
            return "/" + newName;
        } catch (Exception e) {
            log.error("", e);
            return null;
        }
    }

}
