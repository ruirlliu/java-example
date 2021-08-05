package example.jdk.concurrent.future;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author LR<br>
 * @date 2021/7/16 15:16
 */
public class FutureTest {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<String> future = executor.submit(() -> {
			try {
				TimeUnit.MINUTES.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return "Hello Future!";
		});
		String v = future.get();
		System.out.println(v);
	}

}
