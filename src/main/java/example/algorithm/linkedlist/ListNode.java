package example.algorithm.linkedlist;

import java.util.StringJoiner;

public class ListNode {
	public int val;
	public ListNode next;

	public ListNode() {
	}

	public ListNode(int val) {
		this.val = val;
	}

	public ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}

	@Override
	public String toString() {
		ListNode node = this;
		StringJoiner builder = new StringJoiner(",", "[", "]");
		while (node != null) {
			builder.add(Integer.toString(node.val));
			node = node.next;
		}
		return builder.toString();
	}

	public static ListNode toListNode(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		ListNode dummy = new ListNode(-1);
		ListNode node = dummy;
		for (String nodeVal : str.split(",") ) {
			node.next = new ListNode(Integer.parseInt(nodeVal));
			node = node.next;
		}
		return dummy.next;
	}

}
