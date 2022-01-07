package com.synchronous.queue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class WithoutSynchronousQueueApp {

	public static void main(String args[]) throws InterruptedException {
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
		AtomicInteger sharedState = new AtomicInteger();
		CountDownLatch countDownLatch = new CountDownLatch(1);
		
		Runnable producer = () -> {
		    Integer producedElement = ThreadLocalRandom
		      .current()
		      .nextInt();
		    sharedState.set(producedElement);
		    System.out.println("Produced: "+producedElement);
		    countDownLatch.countDown();
		};
		
		Runnable consumer = () -> {
		    try {
		        countDownLatch.await();
		        Integer consumedElement = sharedState.get();
		        System.out.println("Consumed: "+consumedElement);
		    } catch (InterruptedException ex) {
		        ex.printStackTrace();
		    }
		};

		executor.execute(producer);
		executor.execute(consumer);

		executor.awaitTermination(500, TimeUnit.MILLISECONDS);
		executor.shutdown();
		System.out.println(countDownLatch.getCount());
	}
}
