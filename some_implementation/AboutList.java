package algorithm.some_implementation;

import java.awt.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * 链表 (反转，合并有序链表，倒数第k个结点,环的入口结点,从尾到头打印链表，两个链表的第一个公共结点)
 * 
 * @author dong
 *
 */
public class AboutList {
	public class ListNode { // 链表
		int val;
		ListNode next = null;
		ListNode random = null;
		ListNode() {}
		ListNode(int val) {
			this.val = val;
		}

		void append(ListNode head) {

		}
	}
	public ListNode buildList(int[] a){		//通过数组构建一个链表
		ListNode node= new ListNode();
		ListNode temp = new ListNode();
		ListNode rec = node;
		for(int i=0;i<a.length;i++){
			node.val = a[i];				
			if(i==a.length-1) break;		//防止最后一个有下一个不为空的结点
			node.next = new ListNode();
			node = node.next;
		}
		return rec;
	}
	public ListNode reverseList(ListNode head) { // 反转链表
		ListNode pre = null;
		ListNode next = null;

		while (head != null) {
			next = head.next; // 持有下一个节点的引用
			head.next = pre; // 将当前节点对下一个节点的引用指向前一个节点
			pre = head; // 将前一个节点指向当前节点
			head = next; // 将当前节点指向下一个节点
		}
		return pre;
	}

	public ListNode Merge(ListNode list1, ListNode list2) { // 合并两个递增链表
		if (list1 == null) {
			return list2;
		}
		if (list2 == null) {
			return list1;
		}
		ListNode cur = null;
		if (list1.val < list2.val) {
			cur = list1;
			cur.next = Merge(list1.next, list2);
		} else {
			cur = list2;
			cur.next = Merge(list1, list2.next);
		}
		return cur;
	}

	public ListNode FindKthToTail(ListNode head, int k) { // 找出倒数第k个结点
		ListNode pro = head;
		ListNode cur = head;
		if (head == null || k == 0) {
			return null;
		}
		for (int i = 0; i < k - 1; i++) {
			pro = pro.next;
			if (pro == null) {
				return null;
			}
		}
		while (pro != null) {
			if (pro.next == null)
				return cur;
			pro = pro.next;
			cur = cur.next;
		}
		return cur;
	}

	public ListNode EntryNodeOfLoop(ListNode pHead) // 环的入口 破坏链表结构
	{
		ListNode pre = null;
		if (pHead == null || pHead.next == null)
			return null;
		while (pHead.next != null) {
			pre = pHead.next;
			pHead.next = null;
			pHead = pre;
		}
		return pHead;
	}

	public ArrayList<Integer> printListFromTailToHead(ListNode listNode) { // 从尾到头打印链表
		ArrayList<Integer> res = new ArrayList<Integer>();
		if (listNode != null)
			res = printListFromTailToHead(listNode.next);
		else
			return res;
		res.add(listNode.val);
		return res;
	}

	public ListNode Clone(ListNode pHead) // 克隆复杂链表
	{
		if (pHead == null)
			return null;
		ListNode clone = new ListNode(pHead.val);
		clone.random = pHead.random;
		clone.next = Clone(pHead.next);
		return clone;
	}

	public ListNode deleteDuplication(ListNode pHead) // 删除排序链表重复结点
	{
		if (pHead == null)
			return null;
		ListNode pre = pHead;
		ListNode temp = null;
		int rec = pHead.val;
		while (pre.next != null) {
			if (rec == pre.next.val) {
				temp = pre.next;
				pre.next = temp.next;
				temp = null;
			} else {
				rec = pre.next.val;
				pre = pre.next;
			}
		}
		return pHead;
		/*
		 * if(pHead == null) return null; //删掉的可射为空 错的 ListNode head = pHead;
		 * int rec = pHead.val; while(pHead.next!=null){ if(rec !=
		 * pHead.next.val){ pHead = pHead.next; rec = pHead.val; }else{
		 * if(pHead.next.next!=null) pHead = pHead.next.next; else{ pHead =
		 * null; break; } if(pHead.next!=null) while(pHead.val == rec){ pHead =
		 * pHead.next; } } } return head;
		 */
	}

	public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) { //两个链表的第一个公共结点
    	 if(pHead1==null||pHead2==null) return null;
    	 int p1len = getLength(pHead1);
    	 int p2len = getLength(pHead2);
    	 ListNode p1 = pHead1;			//规定p1是长的那个
    	 ListNode p2 = pHead2;
    	 int extra = p1len-p2len;
    	 if(p1len < p2len){		//交换
    		 extra = p2len - p1len;
    		 p1 = pHead2;
    		 p2 = pHead1;
    	 }
    	 while(extra>0){			//从最短的开始处查找
    		 p1 = p1.next;
    		 extra--;
    	 }
    	 ListNode first = null;
    	 while(p1!=null&&p2!=null){
    		 if(p1==p2){
    			 return p1;
    		 }
    		 p1 = p1.next;
    		 p2 = p2.next;
    	 }
    	 return null;
    }
	public int getLength(ListNode head){	//获取链表长度
		int counter = 0;
		ListNode cur = head;
		while(cur!=null){
			counter++;
			cur = cur.next;
		}
		return counter;
	}
	public static void main(String[] args) {
		AboutList aboutList = new AboutList();
		int[] a= {2,3,4,5};
		int[] b ={6,7,8};
		ListNode head = aboutList.buildList(a);
		ListNode second = aboutList.buildList(b);
		//ListNode test = aboutList.Merge(first, second);
		ListNode test = aboutList.reverseList(head);
		while(test.next!=null){
			System.out.println(test.val);
			test = test.next;
		}
		/*
		 * ArrayList<Integer> n=null; n =
		 * aboutList.printListFromTailToHead(first); for(int i:n){
		 * System.out.println(i); }
		 */

	}
}
