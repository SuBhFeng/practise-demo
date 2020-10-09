package com.sufeng.algorithm;/*
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
import java.util.*;

public class Solution7 {
    
    public static final int NULL = '#';

    public static void main(String[] args) {
        String str = "";
        Solution7 solution7 = new Solution7();
        TreeNode root = solution7.Deserialize(str);
        System.out.println(solution7.Serialize(root));
    }

    String Serialize(TreeNode root) {
        if(root == null){
            return "";
        }
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        StringBuffer sb = new StringBuffer();
        queue.add(root);
        while(!queue.isEmpty()){
            if(root.value != '#'){
                // 左
                if(root.left != null){
                    queue.add(root.left);
                }else{
                    queue.add(new TreeNode(NULL));
                }
                // 右
                if(root.right != null){
                    queue.add(root.right);
                }else{
                    queue.add(new TreeNode(NULL));
                }
            }
            sb.append((char)queue.poll().value+"");
            root = queue.peek();
        }
        return sb.append("!").toString();
    }

    TreeNode Deserialize(String str) {
        if(str.length() == 0){
            return null;
        }
        char[] chars = str.toCharArray();
        int index = 0;
        Queue queue1 = new LinkedList();
        Queue<TreeNode> queue2 = new LinkedList();
        while(index < chars.length){
            if(chars[index] == '!'){
                break;
            }
            queue1.add(chars[index]);
            index++;
        }
        queue2.add(new TreeNode((Character)queue1.poll()));
        // 构建树
        TreeNode root = queue2.peek();
        int nodeNum = 1;
        while(!queue2.isEmpty() && nodeNum < chars.length-1){
            TreeNode leftNode = null;
            TreeNode rightNode = null;
            if(queue2.peek() != null){
                if(!queue1.isEmpty()){
                    int leftVal = (Character)queue1.poll();
                    if(leftVal != NULL){
                        leftNode = new TreeNode(leftVal);
                    }else{
                        leftNode = null;
                    }
                    queue2.add(leftNode);
                }

                if(!queue1.isEmpty()){
                    int rightVal = (Character)queue1.poll();
                    if(rightVal != NULL){
                        rightNode = new TreeNode(rightVal);
                    }else{
                        rightNode = null;
                    }
                    queue2.add(rightNode);
                }

                queue2.peek().left = leftNode;
                nodeNum++;

                queue2.peek().right = rightNode;
                nodeNum++;
            }

            queue2.poll();
        }
        return root;
    }
}
