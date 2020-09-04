package com.ranyk.mybatis.questionbank;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * ClassName:Codec
 * Description:力扣第297题
 *
 * @author ranyi
 * @date 2020-06-16 21:35
 * Version: V1.0
 */
public class Codec {

    public static void main(String[] args) {
        Codec codec = new Codec();
        String[] str = {"1","2","3",null,null,"4","5"};
        System.out.println(Arrays.toString(str));
        TreeNode rdeserialize = codec.deserialize(Arrays.toString(str));
        System.out.println(rdeserialize);
        System.out.println(codec.serialize(rdeserialize));
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        return rserialize(root,"");
    }

    public String rserialize(TreeNode root, String str) {
        if (root == null) {
            str += "None,";
        } else {
            str += String.valueOf(root.val) + ",";
            str = rserialize(root.left, str);
            str = rserialize(root.right, str);
        }
        return str;
    }



    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] data_array = data.split(",");
        List<String> data_list = new LinkedList<String>(Arrays.asList(data_array));
        return rdeserialize(data_list);
    }

    public TreeNode rdeserialize(List<String> l) {
        if (l.get(0).equals("None")) {
            l.remove(0);
            return null;
        }

        TreeNode root = new TreeNode(Integer.valueOf(l.get(0)));
        l.remove(0);
        root.left = rdeserialize(l);
        root.right = rdeserialize(l);

        return root;
    }

}


class TreeNode{

    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
