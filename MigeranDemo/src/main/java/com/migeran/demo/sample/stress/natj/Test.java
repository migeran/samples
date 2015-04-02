package com.migeran.demo.sample.stress.natj;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Test {

	public static final int ITERATION_COUNT_SHORT = 10000;
	public static final int ITERATION_COUNT_NORMAL = 100000;
	public static final int ITERATION_COUNT_LONG = 1000000;
	public static int DEFAULT_ITERATION_COUNT = ITERATION_COUNT_NORMAL;

	public static final int MULTITHREAD_COUNT = 2;

	private final String name;

	private final AtomicBoolean isInterrupted = new AtomicBoolean(false);

	private long singleThreadedTime = 0L;

	private long multiThreadedTime = 0L;

	protected Test(String name) {
		this.name = name;
	}

	public final String getName() {
		return name;
	}

	public String getSingleThreadedTime() {
		return singleThreadedTime == 0L ? null : Long
				.toString(singleThreadedTime) + "ms";
	}

	public String getMultiThreadedTime() {
		return multiThreadedTime == 0L ? null : Long
				.toString(multiThreadedTime) + "ms";
	}

	public int getIterationCount() {
		return DEFAULT_ITERATION_COUNT;
	}

	public void reset() {
		isInterrupted.set(false);
		singleThreadedTime = 0L;
		multiThreadedTime = 0L;
	}

	public final void run() {
		if (isInterrupted.get()) {
			return;
		}
		setUpInternal();
		setUp();
		long start = new Date().getTime();
		waitForThreads(getRunnerThreads(1));
		singleThreadedTime = new Date().getTime() - start;
		if (isInterrupted.get()) {
			tearDown();
			tearDownInternal();
			return;
		}
		start = new Date().getTime();
		waitForThreads(getRunnerThreads(MULTITHREAD_COUNT));
		multiThreadedTime = new Date().getTime() - start;
		tearDown();
		tearDownInternal();
	}

	public void interrupt() {
		isInterrupted.set(true);
	}

	public boolean isInterrupted() {
		return isInterrupted.get();
	}

	protected void setUp() {
		// Do nothing
	}

	private void setUpInternal() {
		isInterrupted.set(false);
	}

	protected void tearDown() {
		// Do nothing
	}

	private void tearDownInternal() {
	}

	protected Thread getRunnerThread() {
		return new Thread(new Runnable() {
			@Override
			public void run() {
				final int it = getIterationCount();
				for (int i = 0; i < it; ++i) {
					if (isInterrupted.get()) {
						System.out.println("test interrupted " + getName());
						return;
					}
					doStep();
				}
			}
		});
	}

	protected ArrayList<Thread> getRunnerThreads(int count) {
		ArrayList<Thread> threads = new ArrayList<>();
		for (int i = 0; i < count; ++i) {
			Thread thread = getRunnerThread();
			threads.add(thread);
			thread.start();
		}
		return threads;
	}

	protected void waitForThreads(ArrayList<Thread> threads) {
		for (Thread thread : threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// Do nothing
			}
		}
	}

	protected abstract void doStep();

}
