package com.latchPhaserBarrier;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class CountDownLatchUsingPhaserApp {

	public static void main(String[] args) {

		ExecutorService executor = Executors.newFixedThreadPool(4);
		Phaser phaser = new Phaser(3);
		executor.submit(new DependentService1(phaser));
		executor.submit(new DependentService1(phaser));
		executor.submit(new DependentService1(phaser));
		
		
		phaser.awaitAdvance(1);
		
		System.out.println("All phaser are done");

	}

}

class DependentService implements Runnable {

	private Phaser phaser;

	public DependentService(Phaser phaser) {
		this.phaser = phaser;
	}

	@Override
	public void run() {
			System.out.println(Thread.currentThread().getName() + " before phaser");
			phaser.arrive();
			System.out.println(Thread.currentThread().getName() + " after phaser");
	}

}