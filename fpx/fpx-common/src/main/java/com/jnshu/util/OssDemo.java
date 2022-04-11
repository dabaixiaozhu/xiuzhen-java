package com.jnshu.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URLEncoder;

/**
 * @author admin
 */
public class OssDemo {

    private static String endPoint = "oss-cn-hangzhou.aliyuncs.com";
    private static String bucket = "codicar";
    private static String apiAccessKeyId = "LTAI4FgKzvEC24PqmuQnQLFW";
    private static String apiAccessKeySecret = "F7cIrqzilW02Gyoz9EyQ46r6napIpY";
    private static String path = "jnshu/";

    public static String uploadFile(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        fileName = URLEncoder.encode(fileName, "UTF-8");
//        String path="C:/Users/ALIENWARE/Desktop";
        File filex = new File(path + File.separator + fileName);
        file.transferTo(filex);
        OSS ossClient = new OSSClientBuilder().build(endPoint, apiAccessKeyId, apiAccessKeySecret);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, fileName, filex);
        ossClient.putObject(putObjectRequest);
        ossClient.shutdown();
        filex.delete();
        String url = "http://" + bucket + "." + endPoint + "/";
        return url + URLEncoder.encode(fileName, "UTF-8");
    }
}
