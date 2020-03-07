package burukeyou.file.service.impl;

import burukeyou.file.entity.properties.OssProperties;
import burukeyou.file.service.FileService;
import burukeyou.file.utils.AliyunOssClientUtil;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.ObjectMetadata;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;

public class AliyunOssServiceImpl implements FileService {

    private OSSClient ossClient;

    private OssProperties ossProperties;

    public AliyunOssServiceImpl(OSSClient ossClient, OssProperties fileProperties) {
        this.ossClient = ossClient;
        this.ossProperties = fileProperties;
    }


    @Override
    public void uploadFile(MultipartFile file) throws Exception {

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
            // logger.error("{}", "创建Bucket失败,请核对Bucket名称(规则：只能包含小写字母、数字和短横线，必须以小写字母和数字开头和结尾，长度在3-63之间)");
            //throw new OSSCreateBucketRuntimeException("创建Bucket失败,请核对Bucket名称(规则：只能包含小写字母、数字和短横线，必须以小写字母和数字开头和结尾，长度在3-63之间)");
        }
    }


    /**
     * 2 -  上传文件资源
     * @param file
     * @param fileDir
     * @return
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
            //logger.error("{}", "上传文件失败");
            //throw new OssPutObjectRuntimeException("上传文件失败");
            return null;
        }
    }



    //删除单个
    public void deleteFile(String url){
        String filename = url.substring(url.lastIndexOf(AliyunOssClientUtil.FileDirType.avatar.getDir()),url.length());
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
