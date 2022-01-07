package com.added.accumulator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

public class LongAdderApp {

	public static void main(String args[]) throws InterruptedException {
		
		LongAdder counter = new LongAdder();
		
		ExecutorService executor = Executors.newFixedThreadPool(16);
		for(int i =0;i <100;i++) {
			executor.submit(new Task1(counter));
		}

		executor.awaitTermination(500, TimeUnit.MILLISECONDS);
		executor.shutdown();
		System.out.println(counter.sum());
	}
}

class Task1 implements Runnable {

	private LongAdder counter;

	public Task1(LongAdder counter) {
		this.counter = counter;
	}

	@Override
	public void run() {
		counter.increment();
	}

}
