package com.synchronous.queue;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class SynchronousQueueApp {

	public static void main(String args[]) throws InterruptedException {
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
		SynchronousQueue<Integer> queue = new SynchronousQueue<>();
		
		Runnable producer = () -> {
		    Integer producedElement = ThreadLocalRandom.current().nextInt();
		    try {
		        queue.put(producedElement);
		        System.out.println("Produced: "+producedElement);
		    } catch (InterruptedException ex) {
		        ex.printStackTrace();
		    }
		};
		
		Runnable consumer = () -> {
		    try {
		        Integer consumedElement = queue.take();
		        System.out.println("Consumed: "+consumedElement);
		    } catch (InterruptedException ex) {
		        ex.printStackTrace();
		    }
		};
		
		executor.execute(producer);
		executor.execute(consumer);

		executor.awaitTermination(500, TimeUnit.MILLISECONDS);
		executor.shutdown();
		System.out.println(queue.size());
	}
}
