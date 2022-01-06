package com.latchPhaserBarrier;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchApp {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(4);
		CountDownLatch latch =  new CountDownLatch(3);
		executor.submit(new Service(latch));
		executor.submit(new Service(latch));
		executor.submit(new Service(latch));
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("All latches are done");
		
	}

}

class Service implements Runnable {

	private CountDownLatch latch;

	public Service(CountDownLatch latch) {
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " before latch");
		latch.countDown();
	}

}