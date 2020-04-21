package burukeyou.system.entity.vo;

import burukeyou.common.core.entity.dto.BaseOutputConverter;
import burukeyou.system.entity.pojo.SysChannel;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChannelVo implements BaseOutputConverter<ChannelVo, SysChannel> {

    private String id;

    private String name;

    private String createdBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private LocalDateTime createdTime;

    private String updatedBy;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm",timezone = "GMT+8")
    private LocalDateTime updatedTime;

    private Integer visitsCount;
}
