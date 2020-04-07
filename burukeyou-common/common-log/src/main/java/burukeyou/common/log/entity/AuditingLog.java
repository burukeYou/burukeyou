package burukeyou.common.log.entity;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@Builder
@ToString
public class AuditingLog {

    private String userId;

    private String userNickname;

    private String operationInfo;

    private String interfaceName;

    private String applicationName;

    private LocalDateTime createTime;
}
