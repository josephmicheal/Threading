package producer.consumer;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyBlockingQueue {

	int max;
	Queue<Item> queue = new LinkedBlockingQueue<>();

	Lock lock = new ReentrantLock(true);
	Condition notEmptyQueue = lock.newCondition();
	Condition notFullQueue = lock.newCondition();

	public MyBlockingQueue(int max) {
		this.max = max;
	}

	public void put(Item item) throws InterruptedException {

		lock.lockInterruptibly();

		try {
			while (queue.size() == max) {
				notFullQueue.wait();
			}

			queue.add(item);
			
			notEmptyQueue.signalAll();
		} finally {

			lock.unlock();
		}
	}

	public Item take() throws InterruptedException {

		lock.lockInterruptibly();

		try {
			Item item = null;

			while (queue.size() == 0) {
				notEmptyQueue.await();
			}

			item = queue.remove();

			notFullQueue.signalAll();
			return item;
		} finally {
			lock.unlock();
		}

	}
}
