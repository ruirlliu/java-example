package example.algorithm.linkedlist.reverse;

import example.algorithm.linkedlist.ListNode;

/**
 * 反转整个链表<br>
 *
 * @author LR<br>
 * @version 1.0 <br>
 * @date 2022/7/21 16:47
 */
public class Reverse01 {
	/**
	 * 递归方式其实就是一定找到最后的一个节点，递归的返回值也是这个节点，作为头节点
	 * 新的头节点就是最后一个节点
	 * 所以递归中终止判断是：如果为当前null，返回前面一个节点；或者下一个为null，返回当前节点
	 */

	/**
	 * 迭代方式
	 */
	public ListNode reverse01(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode prev = null;
		ListNode cur = head;
		while (cur != null) {
			ListNode next = cur.next;
			cur.next = prev;
			prev = cur;
			cur = next;
		}
		return prev;
	}

	/**
	 * 递归方式
	 */
	public ListNode reverse02(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		return reverse(null, head);
	}

	private ListNode reverse(ListNode prev, ListNode cur) {
		if (cur == null) {
			return prev;
		}
		ListNode next = cur.next;
		cur.next = prev;
		return reverse(cur, next);
	}

	/**
	 * 递归方式
	 */
	public ListNode reverse03(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode newHead = reverse03(head.next);
		head.next.next = head;
		head.next = null;
		return newHead;
	}

	public static void main(String[] args) {
		Reverse01 reverse = new Reverse01();
		ListNode listNode = reverse.reverse01(ListNode.toListNode("1,2,3,4,5,6"));
		System.out.println(listNode);
	}

}
