package burukeyou.admin.entity.bo;

import burukeyou.admin.entity.bo.menu.TreeNode;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 *    字典树(前缀树) Trie
 *
 *    特点： 是一棵多叉树
 *          根节点不包含字符，除根节点外的每一个子节点都包含一个字符
 *          从根节点到某一节点，路径上经过的字符连接起来，就是该节点对应的字符串。
 *          每个单词的公共前缀作为一个字符节点保存。
 *
 *
 *    应用：
 *            1：词频统计。
 *            2: 前缀匹配
 *
 *    如图：
 *                    root
 *                  /      \
 *                 a        c
 *               / | \    /  \
 *              n  s t   n    o
 *             /            / | \
 *            d            d  e  m
 *
 *
 *   节点设计： 每个节点存放一个映射集合,这样每个节点实际不存放字符本身
 *
 *            例如对于a节点，存储了映射集合为： { 字符n - n节点地址
                                            字符s - s节点地址
                                            字符t - t节点地址
 *                                      }
 *
 */
@Data
public class Trie<T extends TreeNode> {

    @Data
    @ToString
    public class Node {
        //是否是一个单词的尾
        public boolean isWord;

        private T data;

        //每个节点有n个指向下一节点的指针    Node[] nexts = new Node[26];
        public TreeMap<Character, Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }
        public Node(){
            this(false);
        }


        public TreeMap<Character, Node> getNext() {
            return next;
        }
    }


    //根节点
    private Node root;

    //字典树中单词的个数
    private int size;


    public Trie(){
        root = new Node();
        size = 0;
    }

    // cab
    public void  addNode(String word, T data){
        Node cur = root;

        //遍历每个添加的字符处理
        for(int i = 0 ; i < word.length() ; i ++){
            char c = word.charAt(i);

            //查看本节点的儿子节点们是否存在该字符
            if(cur.next.get(c) == null)
                cur.next.put(c,new Node());

            //已存在则指向该儿子节点
            cur = cur.next.get(c);
        }

        cur.setData(data);

        //添加一个单词结束，在该单词表示的最后一个字符的节点作标记
        if(!cur.isWord){
            cur.isWord = true;  //标记单词结束
            size ++;

        }
    }



    public  List<T> converTo(){
        return getSublist(root);
    }

    //递归： 获得跟节点node的所有子节点
    private  List<T> getSublist(Node node){
        if (node.getNext() == null || node.getNext().size() <= 0){
            return null;
        }

        List<T> menuList = new ArrayList<>();

        for (Node subNode : node.getNext().values()) {
            T data = subNode.getData();

            List<T> sublist = getSublist(subNode);
            data.setChildren((List<TreeNode>) sublist);
            menuList.add(data);
        }

        return menuList;
    }






}
