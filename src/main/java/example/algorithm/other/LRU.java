/**
 * 版权申明: 苏州朗动科技有限公司<br>
 * 项目描述: 企查查云聚接口平台
 */

package example.algorithm.other;

import java.util.HashMap;
import java.util.Map;

/**
 * 描述:TOTO 请补全模块<br>
 *
 * @author LR<br>
 * @version 1.0 <br>
 * @date 2022/10/24 19:50
 */
public class LRU {

	private int capacity;
	private Node head;
	private Node tail;
	private Map<Integer, Node> table;

	public LRU(int c) {
		capacity = c;
		table = new HashMap<>();
		head = new Node();
		tail = new Node();
		head.after = tail;
		tail.before = head;
	}



	public int get(int key) {
		Node node = table.get(key);
		if (node == null) {
			return -1;
		}
		moveToHead(node);
		return node.value;
	}


	public void set(int key, int value) {
		Node node = table.get(key);
		if (node == null) {
			node = new Node(key, value);
			table.put(key, node);
			addHead(node);
			if (table.size() > capacity) {
				Node removeTail = removeTail();
				table.remove(removeTail.key);
			}
		} else {
			node.value = value;
			moveToHead(node);
		}
	}


	private void moveToHead(Node node) {
		removeNode(node);
		addHead(node);
	}

	private void addHead(Node node) {
		node.before = head;
		node.after = head.after;
		head.after.before = node;
		head.after = node;
	}

	private void removeNode(Node node) {
		node.before.after = node.after;
		node.after.before = node.before;
	}

	private Node removeTail() {
		Node node = tail.before;
		removeNode(node);
		return node;
	}



	private static class Node {
		private int key;
		private int value;
		private Node before;
		private Node after;
		Node() {}
		Node(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}


}
