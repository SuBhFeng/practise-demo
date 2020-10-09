package com.sufeng.algorithm;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class Solution6 {
    public ListNode deleteDuplication(ListNode pHead)
    {
        Queue<ListNode> queue = initQueue(init());
        ListNode newHead = null;
        ListNode tail = null;
        while(queue.size() > 0){
            if(newHead == null){
                newHead = queue.poll();
                tail = newHead;
                tail.next = null;
                continue;
            }
            tail.next = queue.poll();
            tail = tail.next;
            tail.next = null;
        }
        return newHead;
    }

    public Queue initQueue(ListNode pHead){
        Deque<ListNode> queue = new LinkedList<ListNode>();
        if(pHead == null){
            return queue;
        }
        queue.addLast(pHead);
        pHead = pHead.next;
        while(pHead != null){
            boolean flag = false;
            int peekValue = queue.peekLast().val;
            while(null != pHead && pHead.val == peekValue){
                pHead = pHead.next;
                flag = true;
            }
            if(flag){
                queue.pollLast();
            }
            if(null != pHead){
                queue.addLast(pHead);
                pHead = pHead.next;
            }

        }
        return queue;
    }

    // 1233445
    public ListNode init(){
        ListNode head = null;
        ListNode tail = null;
        int[] vals = new int[]{};
        for (int i = 0; i < vals.length; i++) {
            if(head == null){
                head = new ListNode(vals[i]);
                tail = head;
                tail.next = null;
                continue;
            }
            tail.next = new ListNode(vals[i]);
            tail = tail.next;
            tail.next = null;
        }
        return head;
    }

    public static void main(String[] args) {    
        Solution6 solution6 = new Solution6();
        ListNode init = solution6.init();
//        while(init != null){
//            System.out.println(init.val);
//            init = init.next;
//        }
        Queue<ListNode> queue = solution6.initQueue(init);
//        while(!queue.isEmpty()){
//            System.out.println(queue.poll().val);
//        }
        System.out.println("==============");
        ListNode listNode = solution6.deleteDuplication(new ListNode(1));
        while(listNode != null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}


class ListNode{
    public int val = 0;
    ListNode next;
    ListNode(int val){
        this.val = val;
    }
}