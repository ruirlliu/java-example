package example.jdk.concurrent.future;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/**
 * @author lr
 * @date 2021/1/28
 */
public class MyFuture<V> implements Future<V>, Runnable {

	private volatile int state;

	// 初始化状态
	private static final int NEW = 0;
	// 运行状态
	private static final int RUN = 1;
	// 结束状态
	private static final int DONE = 2;
	// 异常状态
	private static final int EXCEPTION = 3;
	private static final int CANCELLED = 4;

	// 此线程为任务执行线程
	private volatile Thread runner;

	private Callable<V> callable;

	private Object outcome;

	private volatile WaitNode waiters;

	public MyFuture(Callable<V> callable) {
		this.callable = callable;
		state = NEW;
	}

	@Override
	public boolean cancel(boolean mayInterruptIfRunning) {
		if (state == RUN) {
			if (!Thread.interrupted()) {
				runner.interrupt();
				stateOffset.compareAndSet(state, CANCELLED);
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean isCancelled() {
		return state == CANCELLED;
	}

	@Override
	public boolean isDone() {
		return state > RUN;
	}

	@Override
	public V get() throws InterruptedException, ExecutionException {
		if (state < DONE) {
			waiters = new WaitNode();
			LockSupport.park();
		}
		return report(state);
	}

	@Override
	public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
		if (state < DONE) {
			waiters = new WaitNode();
			LockSupport.parkNanos(unit.toNanos(timeout));
		}
		return report(state);
	}

	@SuppressWarnings("unchecked")
	private V report(int state) throws ExecutionException {
		if (state == DONE) {
			return (V) outcome;
		}
		if (state >= CANCELLED) {
			throw new CancellationException();
		}
		throw new ExecutionException((Throwable) outcome);
	}

	@Override
	public void run() {
		if (state != NEW || !runnerOffset.compareAndSet(null, Thread.currentThread())) {
			return;
		}
		try {
			V result;
			try {
				result = callable.call();
				set(result);
			} catch (Exception e) {
				setException(e);
			}
		} finally {
			runner = null;
		}
	}

	private void set(V result) {
		if (stateOffset.compareAndSet(NEW, RUN)) {
			outcome = result;
			stateOffset.set(DONE);
			finishCompletion();
		}
	}

	private void setException(Exception e) {
		if (stateOffset.compareAndSet(NEW, RUN)) {
			outcome = e;
			stateOffset.set(EXCEPTION);
			finishCompletion();
		}
	}

	private void finishCompletion() {
		if (waiters != null) {
			LockSupport.unpark(waiters.thread);
		}
	}


	static class WaitNode {
		// 此线程对象是调用 get 方法获取执行结果的，非任务执行线程
		final Thread thread;

		WaitNode() {
			thread = Thread.currentThread();
		}
	}

	private static final AtomicInteger stateOffset = new AtomicInteger();
	private static final AtomicReference<Thread> runnerOffset = new AtomicReference<>();
	private static final AtomicReference<WaitNode> waitersOffset = new AtomicReference<>();
}
