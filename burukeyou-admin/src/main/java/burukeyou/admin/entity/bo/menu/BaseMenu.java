package burukeyou.admin.entity.bo.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseMenu {

    // 节点id
    protected String id;

    // 父节点 id
    protected String parentId;

    // 排位
    private Integer orderNum;

    // 子节点
    List<BaseMenu> children = new ArrayList<>();

    // 添加子节点
    public void addSubNode(BaseMenu baseMenu){
        children.add(baseMenu);
    }


}
