package example.algorithm.linkedlist.reverse;

import example.algorithm.linkedlist.ListNode;

/**
 * 反转链表前N个节点<br>
 *
 * @author LR<br>
 * @version 1.0 <br>
 * @date 2022/7/21 16:47
 */
public class Reverse02 {
	/**
	 * 递归方式其实就是一定找到最后的一个节点，递归的返回值也是这个节点，作为头节点
	 * 新的头节点是第n个位置的节点
	 * 所以判断是：如果n=0，返回的就是前一个节点；如果n=1，返回的就是当前节点
	 */

	/**
	 * 迭代方式
	 */
	public ListNode reverseN01(ListNode head, int n) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode prev = dummy;
		ListNode cur = prev.next;
		for (int i = 1; i < n; i++) {
			ListNode next = cur.next;
			cur.next = next.next;
			next.next = prev.next;
			prev.next = next;
		}
		return dummy.next;
	}

	/**
	 * 递归方式
	 */
	ListNode successor = null;
	public ListNode reverseN02(ListNode head, int n) {
		if (n == 1) {
			successor = head.next;
			return head;
		}
		ListNode newHead = reverseN02(head.next, n - 1);
		head.next.next = head;
		head.next = successor;
		return newHead;
	}

	/**
	 * 递归方式
	 */
	ListNode after = null;
	public ListNode reverseN03(ListNode head, int n) {
		if (head == null || head.next == null) {
			return head;
		}
		// 相当于先把前N个翻转之后，翻转部分原头节点要到最后一个节点，所以原头节点的后缀节点就是after
		ListNode newHead = reverse(null, head, n);
		head.next = after;
		return newHead;
	}

	private ListNode reverse(ListNode prev, ListNode cur, int n) {
		if (n == 0) {
			after = cur;
			return prev;
		}
		ListNode next = cur.next;
		cur.next = prev;
		return reverse(cur, next, n - 1);
	}

	public static void main(String[] args) {
		Reverse02 reverse02 = new Reverse02();
		ListNode reverseN01 = reverse02.reverseN01(ListNode.toListNode("1,2,3,4,5"), 5);
		ListNode reverseN02 = reverse02.reverseN02(ListNode.toListNode("1,2,3,4,5"), 5);
		ListNode reverseN03 = reverse02.reverseN03(ListNode.toListNode("1,2,3,4,5"), 5);
		System.out.println(reverseN01);
		System.out.println(reverseN02);
		System.out.println(reverseN03);
	}

}
