package com.shitouren.core.controller;

import com.shitouren.core.aop.Access;
import com.shitouren.core.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 照片上传工具类
 *
 * @author admin
 */
@RestController
@Api(value = "图片上传", tags = "图片上传 ")
public class UploaderController {

    @Autowired
    private OssService ossService;

    @PostMapping("/upload")
    @Access(value = false)
    @ApiOperation("图片上传")
    public String uploadPhoto(@RequestParam("uploadFile") MultipartFile uploadFile) throws IOException {
        return ossService.uploadFile(uploadFile);
    }

//    @PostMapping("/uploadX")
//    @Access(value = false)
//    @ApiOperation("图片上传")
//    public String uploadX(@RequestHeader("type") Integer type, HttpServletRequest request) throws IOException, MimeTypeException {
//        AssertUtil.isTrue(type > 0, ExceptionConstant.参数异常);
//        String detect = new Tika().detect(request.getInputStream());
//        MimeTypes allTypes = MimeTypes.getDefaultMimeTypes();
//        MimeType mimeType = allTypes.forName(detect);
//        return IoUtil.readHex28Upper(request.getInputStream()) + " " + StrUtil.join("|", mimeType.getExtensions()) + " " + FileTypeUtil.getType(request.getInputStream());
//        return ossService.uploadFileX(type, "", request.getInputStream());
//    }

}



