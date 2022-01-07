package com.latchPhaserBarrier;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class CyclicBarrierUsingPhaserApp {

	public static void main(String[] args) throws InterruptedException {
		
		System.out.println(Runtime.getRuntime().availableProcessors());

		ExecutorService executor = Executors.newFixedThreadPool(4);
		Phaser phaser = new Phaser(3);
		executor.submit(new DependentService1(phaser));
		executor.submit(new DependentService1(phaser));
		executor.submit(new DependentService1(phaser));

		
		System.out.println("All phaser are done");
		
		Thread.sleep(3000);

	}

}

class DependentService1 implements Runnable {

	private Phaser phaser;

	public DependentService1(Phaser phaser) {
		this.phaser = phaser;
	}

	@Override
	public void run() {
		while(true) {
			System.out.println(Thread.currentThread().getName() + " before phaser");
			phaser.arriveAndAwaitAdvance();
			System.out.println(Thread.currentThread().getName() + " after phaser");
		}
	}

}