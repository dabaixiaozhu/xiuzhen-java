package com.jnshu.util;

import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.event.ProgressEvent;
import com.aliyun.oss.event.ProgressEventType;
import com.aliyun.oss.event.ProgressListener;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.PutObjectRequest;
import org.apache.log4j.Logger;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

/**
 * @author admin
 */
public class OssUtil implements ProgressListener {

    private static final Logger logger = Logger.getLogger(OssUtil.class);

    private long bytesWritten = 0;
    private long totalBytes = -1;
    private boolean succeed = false;

    private static String endpoint = "oss-cn-hangzhou.aliyuncs.com";
    private static String accessKeyId = "LTAI4FgKzvEC24PqmuQnQLFW";
    private static String accessKeySecret = "F7cIrqzilW02Gyoz9EyQ46r6napIpY";
    private static String bucketName = "codicar";
    private static String folder = "jnshu/";


    @Override
    public void progressChanged(ProgressEvent progressEvent) {
        long bytes = progressEvent.getBytes();
        ProgressEventType eventType = progressEvent.getEventType();
        switch (eventType) {
            case TRANSFER_STARTED_EVENT:
                logger.info("开始上传......");
                break;
            case REQUEST_CONTENT_LENGTH_EVENT:
                this.totalBytes = bytes;
                logger.info(this.totalBytes + " 总共字节将被上传到OSS");
                break;
            case REQUEST_BYTE_TRANSFER_EVENT:
                this.bytesWritten += bytes;
                if (this.totalBytes != -1) {
                    int percent = (int)(this.bytesWritten * 100.0 / this.totalBytes);
                    logger.info(bytes + " 此时已写入字节，上传进度: " + percent + "%(" + this.bytesWritten + "/" + this.totalBytes + ")");
                } else {
                    logger.info(bytes + " 此时已写入字节，上传比例：未知" + "(" + this.bytesWritten + "/...)");
                }
                break;
            case TRANSFER_COMPLETED_EVENT:
                this.succeed = true;
                logger.info("上传成功, " + this.bytesWritten + " 总共已传输字节");
                break;
            case TRANSFER_FAILED_EVENT:
                logger.info("上传失败, " + this.bytesWritten + " 字节已传输");
                break;
            default:
                break;
        }
    }

    public boolean isSucceed() {
        return succeed;
    }

    // 1 新建OSSClient

    public static OSS getOSSClient(){
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        return ossClient;
    }


    // 2 上传本地文件

    public static String uploadImg(File file) {
        OSS ossClient = getOSSClient();
        String fileName = file.getName();
        String uuid = UUID.randomUUID().toString().replace("-", "");
        fileName = uuid+"_"+fileName;
        logger.info("filename:"+fileName);
        ossClient.putObject(new PutObjectRequest (bucketName, folder+fileName, file).
                <PutObjectRequest>withProgressListener(new OssUtil()));
        //设置权限 这里是公开读
        ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
        return folder+fileName;
    }

    // 3 下载文件到本地
    // 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。

    public static void downLoading(String objectName,String url){
        OSS ossClient = getOSSClient();
        File file = new File(url);
        String fileName = file.getName();
        logger.info("filename:"+fileName);
        ossClient.getObject(new GetObjectRequest(bucketName, objectName), file);
    }


    // 4 获取图片url

    public static String getUrl(String objectName) {
        OSS ossClient = getOSSClient();
        // 指定过期时间为两个月
        Date expiration = new Date(System.currentTimeMillis() + 60 * 60 * 24 * 60 );
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, objectName, HttpMethod.GET);
        req.setExpiration(expiration);
        URL url = ossClient.generatePresignedUrl(req);
        String url2 = url.toString();
        return url2;
    }

}


