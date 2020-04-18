package burukeyou.admin.utils;

import burukeyou.admin.entity.bo.menu.BaseMenu;

import java.util.*;

/**
 *      菜单多叉树
 */
public class TreeUtil {

    /**
     * 两层循环(暴力循环):
     *
     */
    public static <T extends BaseMenu> List<T> bulid(List<T> treeNodes, Object root) {

        List<T> trees = new ArrayList<>();

        for (T treeNode : treeNodes) {
            // 顶级节点直接加入
            if (root.equals(treeNode.getParentId())) {
                trees.add(treeNode);
            }

            boolean flag = false;
            for (T it : treeNodes) {
                if (it.getParentId().equals(treeNode.getId())) {
                    if (treeNode.getChildren() == null) {
                        treeNode.setChildren(new ArrayList<>());
                    }
                    treeNode.addSubNode(it);
                    flag = true;
                }
            }

            if (flag) Collections.sort(treeNode.getChildren(), Comparator.comparingInt(BaseMenu::getOrderNum));

        }

        Collections.sort(trees, Comparator.comparingInt(BaseMenu::getOrderNum));
        return trees;
    }



    /**
     * 使用递归方法建树
     *
     * @param treeNodes
     * @return
     */
    public static <T extends BaseMenu> List<T> buildByRecursive(List<T> treeNodes, Object root) {
        List<T> trees = new ArrayList<T>();
        for (T treeNode : treeNodes) {
            if (root.equals(treeNode.getParentId())) {
                trees.add(findChildren(treeNode, treeNodes));
            }
        }
        return trees;
    }

    // 递归找到菜单节点treeNode，的子节点
    public static <T extends BaseMenu> T findChildren(T treeNode, List<T> treeNodes) {
        for (T it : treeNodes) {
            if (treeNode.getId().equals(it.getParentId())) {
                if (treeNode.getChildren() == null) {
                    treeNode.setChildren(new ArrayList<>());
                }
                treeNode.addSubNode(findChildren(it, treeNodes));
            }
        }
        return treeNode;
    }





}
