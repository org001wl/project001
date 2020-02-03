package com.bigdata.coreweb.util;

import com.bigdata.coreweb.common.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 图片上传帮助类
 */
public class ImageUploadUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(ImageUploadUtil.class);

    /**
     * 上传图片
     * @param filePath
     * @param
     * @param file
     * @return
     * @throws SystemException
     */
    public static String upload(String filePath, MultipartFile file) throws SystemException {
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        fileName = UUIDUtil.uuid() + suffixName;
        String datePath = DateTimeUtil.buildDatePath();
        String path = filePath + datePath + fileName;
        String result = datePath + fileName;
        File dest = new File(path);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            LOGGER.info("update rotation image, server exception: {}", e);
            throw  new SystemException("服务内部异常,请联系管理员");
        }
        return result;
    }
    /**
     * 删除图片
     * @param path
     */
    public static void remove(String path){
        File file = new File(path);
        if (file.exists()) {
            file.delete();
        }
    }
}
