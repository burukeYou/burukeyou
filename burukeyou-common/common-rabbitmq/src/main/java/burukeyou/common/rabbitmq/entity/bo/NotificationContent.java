package burukeyou.common.rabbitmq.entity.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *      "谁"对什么"东西"'做了'什么事
 *    nickname    title   tt
 *                pd
 *
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class NotificationContent {

    //private String id;
    private String pd; // 被操作的实体的类型： 文章/评论/回复/沸点/用户

    private String title; // 被操作的实体类型的具体名:  "搭建微服务下统一认证授权服务,鉴权客户端大致流程(基于无状态)"

    private String tt; // 动作   "{%nickname%}评论了你的博文{%url%}{%title%}"

    private String commentId; // 动作所属实体id：

    private String url;   // 被操作的实体的url

    private String nickname; // 操作的用户昵称

    private String userId;  // 操作的用户id

    private String avatar; // 被操作的用户的头像

}
