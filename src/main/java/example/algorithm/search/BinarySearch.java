package example.algorithm.search;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 实现一个插入数据自动排序的List；
 * 核心实现：插入自动排序，说明该list是排好序的，需要定位具体的插入位置；
 * 对于一个元素，list中已经存在相同排序顺序的元素。应该排在这些元素的后面
 *
 * @author LR<br>
 * @date 2021/7/2 17:55
 */
public class BinarySearch {

	// Collections.sort
	// 如果排序比较相等，会插入到相同顺序的前面，不符合
	public static <E> int binarySearchIndex(List<E> list, E e, Comparator<E> comparator) {
		int low = 0;
		int high = list.size() - 1;
		while (low <= high) {
			int mid = (low + high) >>> 1;

			int cmp = comparator.compare(list.get(mid), e);

			if (cmp < 0)
				low = mid + 1;
			else if (cmp > 0)
				high = mid - 1;
			else
				return mid;  // 相等，应该排在后面
		}
		return low;

	}

	// joda -> SortedArrayList
	protected static <E> int findInsertionPoint(List<E> list, E e, Comparator<E> comparator) {
		int low = 0;
		int high = list.size() - 1;
		while (low <= high) {
			int mid = (low + high) >>> 1;
			int delta = comparator.compare(list.get(mid), e);

			if (delta > 0) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}

		return low;
	}

	public static void main(String[] args) {
		List<Integer> list = IntStream.of(1, 2, 4, 4, 5, 6).boxed().collect(Collectors.toList());
		// list中已经有元素 4 了，在插入 4，找出插入的下标
//		System.out.println(binarySearchIndex(list, 1, Comparator.naturalOrder()));
//		System.out.println(binarySearchIndex(list, 2, Comparator.naturalOrder()));
//		System.out.println(binarySearchIndex(list, 3, Comparator.naturalOrder()));
		System.out.println(binarySearchIndex(list, 4, Comparator.naturalOrder()));
		System.out.println(findInsertionPoint(list, 4, Comparator.naturalOrder()));
//		System.out.println(binarySearchIndex(list, 5, Comparator.naturalOrder()));
//		System.out.println(binarySearchIndex(list, 6, Comparator.naturalOrder()));
//		System.out.println(binarySearchIndex(list, 7, Comparator.naturalOrder()));
	}

}
