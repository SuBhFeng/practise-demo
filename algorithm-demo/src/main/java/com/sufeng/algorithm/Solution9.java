package com.sufeng.algorithm;

/**
 * @author xiekangkang
 * @date 2020/9/10 16:17
 */
public class Solution9 {
    public static void main(String[] args) {

    }

    public static int TreeDepth(TreeNode root) {
        if(root == null){
            return 0;
        }
        int leftDepth = 1;
        int rightDepth = 1;
        if(root.left != null){
            leftDepth = TreeDepth(root.left) + 1;
        }
        if(root.right != null){
            rightDepth = TreeDepth(root.right) + 1;
        }
        return Math.max(leftDepth,rightDepth);
    }

}
