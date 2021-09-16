package example.jdk.concurrent.thread;

import java.util.concurrent.TimeUnit;

/**
 * @author LR<br>
 * @version 1.0 <br>
 * @date 2021/9/16 15:41
 */
public class DaemonDemo {

	/**
	 * 注意点：不要用 Junit 进行开启非daemon线程测试。主线程执行完后，会直接退出，不关心非daemon线程是否执行结束。
	 * https://stackoverflow.com/questions/13550922/why-non-daemon-thread-is-terminating-if-in-junit-test
	 * https://www.benrowland.net/software/junit-and-non-daemon-threads/
	 * @param args
	 */

	public static void main(String[] args) {

		System.out.println(Thread.currentThread().getName() + "::start");
		Thread thread = new Thread(() -> {
			System.out.println(Thread.currentThread().getName() + "::start");
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "::end");
		});
		if (thread.isDaemon()) {
			thread.setDaemon(false);
		}
		thread.start();
		System.out.println(Thread.currentThread().getName() + "::end");
//		thread.join();
	}

}
