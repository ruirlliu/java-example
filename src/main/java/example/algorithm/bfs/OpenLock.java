package example.algorithm.bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 打开转盘锁 力扣第 752 题「打开转盘锁」
 * https://leetcode-cn.com/problems/open-the-lock/
 */
public class OpenLock {

	public int openLock(String[] deadends, String target) {
		Queue<String> queue = new LinkedList<>();
		Set<String> visited = new HashSet<>();
		queue.offer("0000");
		int step = 0;
		while (!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				String cur = queue.poll();
				if (visited.contains(cur)) {
					continue;
				}
				if (target.equals(cur)) {
					return step;
				}
				visited.add(cur);
				if (isDeadEnd(deadends, cur)) {
					continue;
				}
				for (int j = 0; j < 4; j++) {
					queue.offer(turnUp(cur, j));
					queue.offer(turnDown(cur, j));
				}
			}
			step++;
		}
		return step == 0 ? -1 : step;

	}

	private boolean isDeadEnd(String[] deadends, String cur) {
		for (String deadend : deadends) {
			if (deadend.equals(cur)) {
				return true;
			}
		}
		return false;
	}

	private String turnUp(String cur, int i) {
		char[] arr = cur.toCharArray();
		if (arr[i] == '9') {
			arr[i] = '0';
		} else {
			arr[i] += 1;
		}
		return new String(arr);
	}

	private String turnDown(String cur, int i) {
		char[] arr = cur.toCharArray();
		if (arr[i] == '0') {
			arr[i] = '9';
		} else {
			arr[i] -= 1;
		}
		return new String(arr);
	}


}
