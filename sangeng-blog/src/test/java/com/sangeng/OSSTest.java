// package com.sangeng;
//
// import com.aliyun.oss.ClientException;
// import com.aliyun.oss.OSS;
// import com.aliyun.oss.OSSClientBuilder;
// import com.aliyun.oss.OSSException;
// import org.junit.jupiter.api.Test;
// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.boot.test.context.SpringBootTest;
//
// import java.io.FileInputStream;
// import java.io.InputStream;
//
// /**
//  * @Author AlenXia
//  * @Date 2022/12/8 0:34
//  * @Description
//  */
// @SpringBootTest
// @ConfigurationProperties(prefix = "oss")
// public class OSSTest {
//
//     private String accessKeyId;
//     private String accessKeySecret;
//     private String bucketName;
//
//     @Test
//     public void testOSS() throws Exception {
//
//         // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
//         String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
//         // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
//         String objectName = "xiazhiyuan.docx";
//
//         // 创建OSSClient实例。
//         OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//
//         try {
//             InputStream inputStream=new FileInputStream("C:\\Users\\AlenXia\\Desktop\\夏志远总结.docx");
//             // 创建PutObject请求。
//             ossClient.putObject(bucketName, objectName, inputStream);
//         } catch (OSSException oe) {
//             System.out.println("Caught an OSSException, which means your request made it to OSS, "
//                     + "but was rejected with an error response for some reason.");
//             System.out.println("Error Message:" + oe.getErrorMessage());
//             System.out.println("Error Code:" + oe.getErrorCode());
//             System.out.println("Request ID:" + oe.getRequestId());
//             System.out.println("Host ID:" + oe.getHostId());
//         } catch (ClientException ce) {
//             System.out.println("Caught an ClientException, which means the client encountered "
//                     + "a serious internal problem while trying to communicate with OSS, "
//                     + "such as not being able to access the network.");
//             System.out.println("Error Message:" + ce.getMessage());
//         } finally {
//             if (ossClient != null) {
//                 ossClient.shutdown();
//             }
//         }
//     }
//
//     public String getAccessKeyId() {
//         return accessKeyId;
//     }
//
//     public void setAccessKeyId(String accessKeyId) {
//         this.accessKeyId = accessKeyId;
//     }
//
//     public String getAccessKeySecret() {
//         return accessKeySecret;
//     }
//
//     public void setAccessKeySecret(String accessKeySecret) {
//         this.accessKeySecret = accessKeySecret;
//     }
//
//     public String getBucketName() {
//         return bucketName;
//     }
//
//     public void setBucketName(String bucketName) {
//         this.bucketName = bucketName;
//     }
// }
//
