package burukeyou.file.service.impl;

import burukeyou.file.entity.properties.OssProperties;
import burukeyou.file.service.FileService;
import burukeyou.file.utils.AliyunOssClientUtil;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.ObjectMetadata;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
public class AliyunOssServiceImpl implements FileService {

    private OSSClient ossClient;

    private OssProperties ossProperties;

    public AliyunOssServiceImpl(OSSClient ossClient, OssProperties fileProperties) {
        this.ossClient = ossClient;
        this.ossProperties = fileProperties;
    }


    @Override
    public String uploadFile(MultipartFile file) throws Exception {
        return null;
    }

    @Override
    public List<String> uploadFileList(String type,List<MultipartFile> fileList) throws Exception {
       return fileList.stream().map(e -> this.upload(e, type)).collect(Collectors.toList());
    }


    /**
     * 1 - 当Bucket不存在时创建Bucket
     *
     Bucket命名规则：
     *                         1.只能包含小写字母、数字和短横线，
     *                         2.必须以小写字母和数字开头和结尾
     *                         3.长度在3-63之间
     */
    public  void createBucket() {
        try {
            //判断是否存在该Bucket，不存在时再重新创建
            if (!ossClient.doesBucketExist(ossProperties.getBUCKET_NAME())) {
                ossClient.createBucket(ossProperties.getBUCKET_NAME());
            }
        } catch (Exception e) {
            log.error("{}", "创建Bucket失败,请核对Bucket名称(规则：只能包含小写字母、数字和短横线，必须以小写字母和数字开头和结尾，长度在3-63之间)");
        }
    }



    /**
     * 2-  上传文件---去除URL中的？后的时间戳
     *              参数：
     *                  file 文件
     *                  fileDir 上传到OSS上文件的路径
     */
    public String upload(MultipartFile file, String fileDir) {
        //1 -
        createBucket();

        // 2- 上传
        String fileName = this.uploadFile(file, fileDir);

        // 3 - 生成地址
        String fileOssURL = getImgUrl(fileName, fileDir);

        int firstChar = fileOssURL.indexOf("?");
        if (firstChar > 0) {
            fileOssURL = fileOssURL.substring(0, firstChar);
        }
        return fileOssURL;    //文件的访问地址
    }


    private  String getImgUrl(String fileUrl, String fileDir) {
        if (StringUtils.isEmpty(fileUrl)) {
            throw new RuntimeException("文件地址为空");
        }
        String[] split = fileUrl.split("/");

        //获取oss图片URL失败
        URL url =  ossClient.generatePresignedUrl(ossProperties.getBUCKET_NAME(), fileDir + split[split.length - 1], DateUtils.addDays(new Date(), 365 * 10));
        if (url == null) {
            throw new RuntimeException("获取oss图片URL失败");
        }
        return url.toString();
    }




    /**
     * 2 -  上传文件资源
     */
    public String uploadFile(MultipartFile file, String fileDir) {
        String fileName = String.format("%s.%s", UUID.randomUUID().toString(), FilenameUtils.getExtension(file.getOriginalFilename()));

        try (InputStream inputStream = file.getInputStream()) {
            //创建上传Object的Metadata
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma", "no-cache");
            objectMetadata.setContentType(getContentType(FilenameUtils.getExtension("." + file.getOriginalFilename())));
            objectMetadata.setContentDisposition("inline;filename=" + fileName);

            //上传文件
            ossClient.putObject(ossProperties.getBUCKET_NAME(), fileDir + fileName, inputStream, objectMetadata);

            return fileName;

        } catch (Exception e) {
            return null;
        }
    }



    //删除单个
    public void deleteFile(String url){
        String filename = url.substring(url.lastIndexOf("/")+1,url.length());
        if (ossClient.doesObjectExist(ossProperties.getBUCKET_NAME(),filename))
            ossClient.deleteObject(ossProperties.getBUCKET_NAME(),filename);
    }

    //批量删除
    public void deleteFileList(List<String> urls){
        List<String> rs = ossClient.deleteObjects(new DeleteObjectsRequest(ossProperties.getBUCKET_NAME())
                .withKeys(urls))
                .getDeletedObjects();

        if (rs.size() > 0)
            System.out.println("删除失败");
    }



    /**
     * 判断OSS服务文件上传时文件的contentType
     @param FilenameExtension 文件后缀
     @return 后缀
     */
    private static String getContentType(String FilenameExtension) {
        if (FilenameExtension.equalsIgnoreCase("bmp")) {
            return "image/bmp";
        }
        if (FilenameExtension.equalsIgnoreCase("gif")) {
            return "image/gif";
        }
        if (FilenameExtension.equalsIgnoreCase("jpeg") ||
                FilenameExtension.equalsIgnoreCase("jpg") ||
                FilenameExtension.equalsIgnoreCase("png")) {
            return "image/jpeg";
        }
        if (FilenameExtension.equalsIgnoreCase("html")) {
            return "text/html";
        }
        if (FilenameExtension.equalsIgnoreCase("txt")) {
            return "text/plain";
        }
        if (FilenameExtension.equalsIgnoreCase("vsd")) {
            return "application/vnd.visio";
        }
        if (FilenameExtension.equalsIgnoreCase("pptx") ||
                FilenameExtension.equalsIgnoreCase("ppt")) {
            return "application/vnd.ms-powerpoint";
        }
        if (FilenameExtension.equalsIgnoreCase("docx") ||
                FilenameExtension.equalsIgnoreCase("doc")) {
            return "application/msword";
        }
        if (FilenameExtension.equalsIgnoreCase("xml")) {
            return "text/xml";
        }
        return "image/jpeg";
    }











}
