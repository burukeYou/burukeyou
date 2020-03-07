package burukeyou.file.entity.properties;

import lombok.Data;


@Data
public class OssProperties {
    //OSS 的地址
    private String END_POINT;

    //OSS 的key值
    private String ACCESS_KEY_ID;

    //OSS 的secret值
    private String ACCESS_KEY_SECRET;

    //OSS 的bucket名字
    private  String BUCKET_NAME;

    //设置URL过期时间
    private String URL_EXPIRATION;
}
