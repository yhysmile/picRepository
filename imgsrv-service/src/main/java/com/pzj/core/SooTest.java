package com.pzj.core;

import java.io.File;
import java.io.IOException;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.GetObjectRequest;

public class SooTest {

	private static String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
    private static String accessKeyId = "LTAId3icLKmsH1Ui";
    private static String accessKeySecret = "MR575eggAa5puX4s1ycV6MHq0SYxGn";
    private static String bucketName = "hu6";
    private static String key = "IMG_2941.JPG";
    
    
    public static void main(String[] args) throws IOException {        

        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret); 
        
        try {
        	long starttime = System.currentTimeMillis();
            // 缩放
            String style = "image/resize,m_fixed,w_100,h_100";  
            GetObjectRequest request = new GetObjectRequest(bucketName, key);
            request.setProcess(style);
            
            ossClient.getObject(request, new File("D:/example-resize.jpg"));
            long endtime = System.currentTimeMillis();
            System.out.println("shijian = "+(endtime -starttime ));
            
            // 裁剪
            style = "image/crop,w_100,h_100,x_100,y_100,r_1"; 
            request = new GetObjectRequest(bucketName, key);
            request.setProcess(style);
            
            ossClient.getObject(request, new File("D:/example-crop.jpg"));
            
            // 旋转
            style = "image/rotate,90"; 
            request = new GetObjectRequest(bucketName, key);
            request.setProcess(style);
            
            ossClient.getObject(request, new File("D:/example-rotate.jpg"));
            
            // 锐化
            style = "image/sharpen,100"; 
            request = new GetObjectRequest(bucketName, key);
            request.setProcess(style);
            
            ossClient.getObject(request, new File("D:/example-sharpen.jpg"));
            
            // 水印
            style = "image/watermark,text_SGVsbG8g5Zu-54mH5pyN5YqhIQ"; 
            request = new GetObjectRequest(bucketName, key);
            request.setProcess(style);
            
            ossClient.getObject(request, new File("D:/example-watermark.jpg"));
            
            // 格式转换
            style = "image/format,png"; 
            request = new GetObjectRequest(bucketName, key);
            request.setProcess(style);
            
            ossClient.getObject(request, new File("D:/example-format.png"));
            
            // 图片信息
            style = "image/info"; 
            request = new GetObjectRequest(bucketName, key);
            request.setProcess(style);
            
            ossClient.getObject(request, new File("D:/example-info.txt"));
            
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message: " + oe.getErrorCode());
            System.out.println("Error Code:       " + oe.getErrorCode());
            System.out.println("Request ID:      " + oe.getRequestId());
            System.out.println("Host ID:           " + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message: " + ce.getMessage());
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
    }

}
