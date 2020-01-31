package common.entity.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.Instant;
import java.time.ZonedDateTime;

@ApiModel(description = "restful请求统一的返回类型" )
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ResultVo<T> {


    @ApiModelProperty(value = "状态码", required = true)
    private String code;

    @ApiModelProperty(value = "返回结果的描述信息")
    private String message;

    @ApiModelProperty(value = "返回结果生成的时间戳")
    private Instant time;

    @ApiModelProperty(value = "返回结果携带的数据") // 属性值为null的不参与序列化
    @JsonInclude()
    private T data;


    public static ResultVo success(){
        return new ResultVo("200","",ZonedDateTime.now().toInstant(),null);
    }

    public static ResultVo success(Object data){
        return new ResultVo("200","",ZonedDateTime.now().toInstant(),data);
    }

    public static ResultVo error(String msg){
        return new ResultVo("400",msg,ZonedDateTime.now().toInstant(),null);
    }





}
