package com.added.accumulator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public class WithAtomicLongApp {

	public static void main(String args[]) throws InterruptedException {
		
		AtomicLong counter = new AtomicLong(0);
		
		ExecutorService executor = Executors.newFixedThreadPool(16);
		for(int i =0;i <100;i++) {
			executor.submit(new Task(counter));
		}

		executor.awaitTermination(500, TimeUnit.MILLISECONDS);
		executor.shutdown();
		System.out.println(counter.get());
	}
}

class Task implements Runnable {

	private AtomicLong counter;

	public Task(AtomicLong counter) {
		this.counter = counter;
	}

	@Override
	public void run() {
		counter.incrementAndGet();
	}

}
