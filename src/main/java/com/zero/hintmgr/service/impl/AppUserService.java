package com.zero.hintmgr.service.impl;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.zero.hintmgr.dao.DummyDao;

@Component
public class AppUserService extends BaseServiceImpl {
    Logger log = LoggerFactory.getLogger(DummyServiceImpl.class);

    final static String LOG_DIR = "/hintmgr/logs/"; //refer to logback.xml
    final static String LOG_ZIP_FULL_FILE_NAME = LOG_DIR + "logs.zip";
    
    @Autowired
    protected DummyDao dao;
    
    @Value("${oss.endpoint}")
    String endpoint;
    @Value("${oss.accessKey}")
    String accessKey;
    @Value("${oss.secretKey}")
    String secretKey;
    @Value("${oss.bucket}")
    String bucket;    
    
    OSS getOssClient() {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKey, secretKey);
        return ossClient;
    }    

    // https://sw-clue.oss-cn-guangzhou.aliyuncs.com/202111/3b06d551e14d48f5927180de3674205b.png?Expires=1637191176&OSSAccessKeyId=LTAI5t8M6cuo3dFJHoN1ZrqM&Signature=vrIPXmEVl5xu9n1KCVHvaGv%2B4es%3D
    // https://sw-clue.oss-cn-guangzhou.aliyuncs.com/20211112/9bd38be12cbb4999b3316cb8546d6c68.png?Expires=1639319087&OSSAccessKeyId=LTAI5t8M6cuo3dFJHoN1ZrqM&Signature=q90La8gwmS58E38L28zegT6qbAQ%3D
    // https://sw-clue.oss-cn-guangzhou.aliyuncs.com/20211113/5c3706fee02e4029817cb05c2a23fd75.jpeg?Expires=1636849097&OSSAccessKeyId=LTAI5t8M6cuo3dFJHoN1ZrqM&Signature=W4YtWJGpxETt9x4bq9AlmbwEwlg%3D
    public Map<String, String> uploadFile(InputStream inputStream, String originalFileName) throws OSSException, ClientException, FileNotFoundException {
        String folderName = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String fileMainName = UUID.randomUUID().toString().replace("-", "");
        String extensionName = originalFileName.substring(originalFileName.lastIndexOf("."));
        String objectName = folderName + "/" + fileMainName + extensionName;
        
        OSS oss = getOssClient();
        oss.putObject(bucket, objectName, inputStream);
        oss.shutdown();
        log.info("Upload file {} Object Name {} ", originalFileName, objectName);
        
        Map<String, String> map = new HashMap<>();
        map.put("objectName", objectName);
        map.put("url", getFileUrl(objectName));
        
        return map;
    }
    
    // https://sw-clue.oss-cn-guangzhou.aliyuncs.com/20211113/5c3706fee02e4029817cb05c2a23fd75.jpeg?Expires=1636849097&OSSAccessKeyId=LTAI5t8M6cuo3dFJHoN1ZrqM&Signature=W4YtWJGpxETt9x4bq9AlmbwEwlg%3D
    // Return "20211113/5c3706fee02e4029817cb05c2a23fd75.jpeg"
    String getObjectName(String url) {
        int startIndex = "https://sw-clue.oss-cn-guangzhou.aliyuncs.com/".length();
        int endIndex = url.indexOf("?");
        return url.substring(startIndex, endIndex);
    }    
    
    /** Get file URL for 1-day */
    public String getFileUrl(String objectName) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, 1);
        Date date1Day = c.getTime();
        
        OSS oss = getOssClient();
        String url = oss.generatePresignedUrl(bucket, objectName, date1Day).toString();
        return url;
    }
    
    /** Save file to OSS, return objectName */
    public String saveUrlToOss(String url) {
        try {            
            HttpURLConnection conn = (HttpURLConnection) (new URL(url)).openConnection();
            //conn.setConnectTimeout(3 * 1000);
            //conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
            InputStream inputStream = conn.getInputStream();
            
            String folderName = new SimpleDateFormat("yyyyMM").format(new Date());
            String fileMainName = UUID.randomUUID().toString().replace("-", "");
            String objectName = folderName + "/" + fileMainName;
            
            OSS oss = getOssClient();
            oss.putObject(bucket, objectName, inputStream);
            oss.shutdown();
            
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DAY_OF_MONTH, 1);
            Date date1Day = c.getTime();
            String fileUrl = oss.generatePresignedUrl(bucket, objectName, date1Day).toString();
            System.out.println("URL: " + fileUrl);
            //System.out.println(String.format("%1$tF %1$tT", System.currentTimeMillis())); // .%1$tL
            return objectName;
        } catch (Exception e) {
            log.error("{}", e);
            return "";
        }
    }
    
}
