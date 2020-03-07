package burukeyou.file.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    /**
     * @param file  file resource
     * @return the url of file upload later
     */
    void uploadFile(MultipartFile file) throws Exception;


    /**
     * @param id file unique id
     */
    void deleteFile(String id);



}
