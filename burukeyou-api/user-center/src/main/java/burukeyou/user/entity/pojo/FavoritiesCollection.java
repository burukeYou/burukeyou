package burukeyou.user.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@TableName("ums_favorites_collection")
@AllArgsConstructor
@NoArgsConstructor
public class FavoritiesCollection {

    @TableId(type = IdType.INPUT)
    private String favoritesId;

    @TableId(type = IdType.INPUT)
    private String collectionId;


    @TableId(type = IdType.INPUT)
    private String collectionType;

}
