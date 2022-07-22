package example.algorithm.linkedlist.reverse;

import example.algorithm.linkedlist.ListNode;

/**
 * 描述: 链表部分反转<br>
 *
 * @author LR<br>
 * @version 1.0 <br>
 * @date 2022/7/21 17:51
 */
public class Reverse03 {

	/**
	 * 迭代方式
	 */
	public ListNode reverseBetween01(ListNode head, int left, int right) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode dummy = new ListNode(-1, head);
		ListNode prev = dummy;
		for (int i = 1; i < left; i++) {
			prev = prev.next;
		}
		ListNode cur = prev.next;
		for (int i = left; i < right; i++) {
			ListNode next = cur.next;
			cur.next = next.next;
			next.next = prev.next;
			prev.next = next;
		}
		return dummy.next;
	}

	/**
	 * 迭代方式
	 */
	public ListNode reverseBetween02(ListNode head, int left, int right) {
		if (head == null || head.next == null) {
			return head;
		}
		// base case, 当 left = 1时，相当于翻转链表前N个元素
		if (left == 1) {
			Reverse02 reverse02 = new Reverse02();
			return reverse02.reverseN01(head, right);
		} else {
			head.next = reverseBetween02(head.next, left - 1, right - 1);
			return head;
		}
	}



	public static void main(String[] args) {
		Reverse03 reverse = new Reverse03();
		ListNode reverseBetween01 = reverse.reverseBetween01(ListNode.toListNode("1,2,3,4,5,6,7"), 1, 5);
		ListNode reverseBetween02 = reverse.reverseBetween02(ListNode.toListNode("1,2,3,4,5,6,7"), 1, 5);
		System.out.println(reverseBetween01);
		System.out.println(reverseBetween02);
	}

}
