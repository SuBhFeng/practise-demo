package com.sufeng.algorithm;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 树的后序遍历
 */
public class Solution4 {
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        if(root == null){
            return null;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        while(!stack.isEmpty() || root != null){
            stack.push(root);
            while(root.left != null){
                stack.push(root.left);
                queue.offer(root.left);
                root = root.left;
            }
            if(root.right != null){
                stack.push(root.right);
                queue.offer(root.right);
                root = root.right;
            }
            if(root.left == null && root.right == null){

            }
        }
        return null;
    }
}

class TreeNode{
    TreeNode left;
    TreeNode right;
    int value;
    public TreeNode(int val) {
        this.value = val;

    }
}