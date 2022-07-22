package example.algorithm;

import java.util.StringJoiner;

public class Node {
	int val;
	Node next;
	Node random;

	public Node(int val) {
		this.val = val;
		this.next = null;
		this.random = null;
	}

	@Override
	public String toString() {
		Node node = this;
		StringJoiner builder = new StringJoiner(",", "[", "]");
		while (node != null) {
			builder.add(Integer.toString(node.val));
			node = node.next;
		}
		return builder.toString();
	}

//	public static Node toListNode(int[][] nodes) {
//		if (nodes == null || nodes.length == 0) {
//			return null;
//		}
//		Node dummy = new Node(-1);
//		Node node = dummy;
//		for (int[] nodeVal : nodes) {
//			node.next = new Node(nodeVal[0]);
//		}
//		for (String nodeVal : str.split(",") ) {
//			node.next = new ListNode(Integer.parseInt(nodeVal));
//			node = node.next;
//		}
//		return dummy.next;
//	}

}
