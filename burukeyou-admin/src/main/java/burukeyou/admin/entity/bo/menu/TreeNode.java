package burukeyou.admin.entity.bo.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {

    // 父节点 id
    protected String path;

    // 子节点
    List<TreeNode> children = new ArrayList<>();

    // 添加子节点
    public void addSubNode(TreeNode baseMenu){
        children.add(baseMenu);
    }


}
