package burukeyou.file.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    /**  upload one
     * @param file  file resource
     * @return the url of file upload later
     */
    String uploadFile(MultipartFile file) throws Exception;

    /** upload list
     *
     * @param fileList  file resource
     * @return the url of file upload later
     */
    List<String> uploadFileList(String type,List<MultipartFile> fileList) throws Exception;




    /**
     * @param id file unique id
     */
    void deleteFile(String id);



}
