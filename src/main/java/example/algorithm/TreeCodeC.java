package example.algorithm;

import java.util.LinkedList;
import java.util.Queue;

public abstract class TreeCodeC {

	private static final String NULL = "null";

	public abstract String serialize(TreeNode root);

	public abstract TreeNode deserialize(String data);

	// Pre-Order
	public static class Dfs extends TreeCodeC {
		@Override
		public String serialize(TreeNode root) {
			StringBuilder builder = new StringBuilder();
			serializeDfs(root, builder);
			return builder.toString();
		}

		private void serializeDfs(TreeNode root, StringBuilder builder) {
			if (root == null) {
				builder.append(NULL).append(",");
				return;
			}
			builder.append(root.val).append(",");
			serializeDfs(root.left, builder);
			serializeDfs(root.right, builder);
		}

		@Override
		public TreeNode deserialize(String data) {
			LinkedList<String> nodes = new LinkedList<>();
			for (String node : data.split(",")) {
				nodes.offer(node);
			}
			return deserializeDfs(nodes);
		}

		private TreeNode deserializeDfs(LinkedList<String> nodes) {
			if (nodes.isEmpty()) {
				return null;
			}
			String rootVal = nodes.pollFirst();
			if (rootVal.equals(NULL)) {
				return null;
			}
			TreeNode root = new TreeNode(Integer.parseInt(rootVal));
			root.left = deserializeDfs(nodes);
			root.right = deserializeDfs(nodes);
			return root;
		}
	}

	public static class Bfs extends TreeCodeC {

		@Override
		public String serialize(TreeNode root) {
			if (root == null) {
				return "";
			}
			StringBuilder builder = new StringBuilder();
			Queue<TreeNode> queue = new LinkedList<>();
			queue.offer(root);
			while (!queue.isEmpty()) {
				int size = queue.size();
				for (int i = 0; i < size; i++) {
					TreeNode node = queue.poll();
					if (node == null) {
						builder.append(NULL).append(",");
						continue;
					}
					builder.append(node.val).append(",");
					queue.offer(node.left);
					queue.offer(node.right);
				}
			}
			return builder.toString();
		}

		@Override
		public TreeNode deserialize(String data) {
			String[] nodes = data.split(",");
			TreeNode root = new TreeNode(Integer.parseInt(nodes[0]));
			Queue<TreeNode> queue = new LinkedList<>();
			queue.offer(root);
			for (int i = 1; i < nodes.length; ) {
				TreeNode node = queue.poll();
				String leftVal = nodes[i++];
				if (!leftVal.equals(NULL)) {
					TreeNode left = new TreeNode(Integer.parseInt(leftVal));
					node.left = left;
					queue.offer(left);
				} else {
					node.left = null;
				}

				String rightVal = nodes[i++];
				if (!rightVal.equals(NULL)) {
					TreeNode right = new TreeNode(Integer.parseInt(rightVal));
					node.right = right;
					queue.offer(right);
				} else {
					node.right = null;
				}
			}
			return root;
		}
	}

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.right = new TreeNode(4);

		TreeCodeC.Dfs dfs = new TreeCodeC.Dfs();
		String dfsSerialize = dfs.serialize(root);
		TreeNode dfsDeserialize = dfs.deserialize(dfsSerialize);
		System.out.println(dfsSerialize);

		TreeCodeC.Bfs bfs = new TreeCodeC.Bfs();
		String bfsDerialize = bfs.serialize(root);
		System.out.println(bfsDerialize);
	}

}