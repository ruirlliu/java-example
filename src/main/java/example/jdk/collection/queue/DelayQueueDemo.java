package example.jdk.collection.queue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 描述:TOTO 请补全模块<br>
 *
 * @author LR<br>
 * @version 1.0 <br>
 * @date 2022/3/2 16:38
 */
public class DelayQueueDemo {

	public static void main(String[] args) throws InterruptedException {
		DelayQueue<DelayedEle> queue = new DelayQueue<>();
		queue.add(new DelayedEle("ele01"));
		queue.add(new DelayedEle("ele02"));
		while (!queue.isEmpty()) {
			queue.take().out();
		}
	}


	static class DelayedEle implements Delayed {

		private long time = System.currentTimeMillis() + 5000;

		String str;
		DelayedEle(String str) {
			this.str = str;
		}
		@Override
		public long getDelay(TimeUnit unit) {
			return unit.convert(time - System.currentTimeMillis() , TimeUnit.NANOSECONDS);
		}

		@Override
		public int compareTo(Delayed o) {
			return 0;
		}

		public void out() {
			System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")) + " : " + str);
		}

	}
}
