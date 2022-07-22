package example.algorithm.linkedlist;

/**
 * 描述:TOTO 请补全模块<br>
 *
 * @author LR<br>
 * @version 1.0 <br>
 * @date 2022/7/19 15:09
 */
public class Solution {

	// 1 -> 2 -> 3 -> 4 -> 5
	// successor 4

	// 1 > 2 > 4 > 5
	ListNode successor = null; // 后驱节点
	// 反转以 head 为起点的 n 个节点，返回新的头结点
	ListNode reverseN(ListNode head, int n) {
		if (n == 1) {
			// 记录第 n + 1 个节点
			successor = head.next;
			return head;
		}
		// 以 head.next 为起点，需要反转前 n - 1 个节点
		ListNode last = reverseN(head.next, n - 1);

		head.next.next = head;
		// 让反转之后的 head 节点和后面的节点连起来
		head.next = successor;
		return last;
	}

	ListNode after = null; // 后驱节点
	ListNode reverseN02(ListNode head, int n) {
		ListNode reverseHead = reverse(null, head, n);
		head.next = after;
		return reverseHead;
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
		ListNode listNode = ListNode.toListNode("1,2,3,4,5,6,7,8,9");
		Solution solution = new Solution();
		ListNode res = solution.reverseN(listNode, 8);
		System.out.println(res);


		ListNode listNode02 = ListNode.toListNode("1,2,3,4,5,6,7,8,9");
		ListNode res02 = solution.reverseN02(listNode02, 8);
		System.out.println(res02);
	}

}
