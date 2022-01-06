package com.latchPhaserBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierApp {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(4);
		CyclicBarrier barrier = new CyclicBarrier(3);
		executor.submit(new Service1(barrier));
		executor.submit(new Service1(barrier));
		executor.submit(new Service1(barrier));
		System.out.println("All barriers are done");

	}

}

class Service1 implements Runnable {

	private CyclicBarrier barrier;

	public Service1(CyclicBarrier barrier) {
		this.barrier = barrier;
	}

	@Override
	public void run() {
		while (true) {
			//System.out.println(Thread.currentThread().getName() + " before barrier");
			try {
				barrier.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + " after barrier");
		}
	}

}