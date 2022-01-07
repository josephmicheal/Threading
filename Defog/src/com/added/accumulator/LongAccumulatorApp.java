package com.added.accumulator;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAccumulator;

public class LongAccumulatorApp {

	public static void main(String args[]) throws InterruptedException {
		
		LongAccumulator counter = new LongAccumulator((x,y)-> x+y,0);
		
		ExecutorService executor = Executors.newFixedThreadPool(16);
		for(int i =0;i <100;i++) {
			executor.submit(new Task2(counter));
		}

		executor.awaitTermination(500, TimeUnit.MILLISECONDS);
		executor.shutdown();
		System.out.println(counter.get());
	}
}

class Task2 implements Runnable {

	private LongAccumulator counter;

	public Task2(LongAccumulator counter) {
		this.counter = counter;
	}

	@Override
	public void run() {
		counter.accumulate(1);
	}

}
