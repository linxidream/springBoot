package com.benmei.sale.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.UUID;

/**
 * 阿里云OSS操作工具类
 * Created by Peter on 2017/4/19.
 */
public class AliyunOssUtil {
    public static final Logger logger = LoggerFactory.getLogger(AliyunOssUtil.class);

    public static String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";

    @Value("oss.accessKeyId")
    public static String accessKeyId = "dzhm6kIZDL8VpgPP";

    @Value("oss.accessKeySecret")
    public static String accessKeySecret = "4qSofG2TiEABy1Gcs0IhgHXpdLKust";

    @Value("oss.bucketName")
    public static String bucketName = "waijiaojun";

    private final static OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);

    /**
     * 获取文件的后缀名，如fileName="test.png"，返回".png"。
     *
     * @param fileName
     * @return 文件的后缀名，包含"."
     * @throws Exception
     */
    public static String getFileSuffix(String fileName) throws Exception {
        if (fileName == null || "".equals(fileName.trim())) {
            throw new Exception("文件名不能为空");
        }
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        return suffix;
    }

    /**
     * 获取文件名，不包含后缀
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    public static String getFilePrefix(String fileName) throws Exception {
        if (fileName == null || "".equals(fileName.trim())) {
            throw new Exception("文件名不能为空");
        }
        String suffix = fileName.substring(0, fileName.lastIndexOf("."));
        return suffix;
    }

    /**
     * 根据给定的文件名，返回带后缀的随机文件名，格式：系统当前的毫秒+"_"+uuid+原后缀名
     *
     * @param fileName
     * @return
     * @throws Exception
     */
    public static String getRandomFileName(String fileName) throws Exception {
        if (fileName == null || "".equals(fileName.trim())) {
            throw new Exception("文件名不能为空");
        }
        String uuid = UUID.randomUUID().toString();
        String name = System.currentTimeMillis() + "_" + uuid + getFileSuffix(fileName);
        return name;
    }

    /**
     * 上传图片到阿里云
     * @param key
     * @param inputStream
     * @param size
     */
    public static void upload(String key, InputStream inputStream, long size) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(size);
        client.putObject(bucketName, key, inputStream);
    }
}
