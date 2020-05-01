package burukeyou.im.api.enity.dto;

import burukeyou.common.core.entity.dto.BaseInputConverter;
import burukeyou.im.api.enity.pojo.ImsFriendRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel
public class FriendRequestDto implements BaseInputConverter<ImsFriendRequest> {

    @ApiModelProperty(value = "好友请求发向的那个人id")
    @NotBlank(message = "好友谊请求消息接收者id不能为空")
    private String acceptUserId;

    @ApiModelProperty(value = "好友请求备注消息")
    private String msg;

}
