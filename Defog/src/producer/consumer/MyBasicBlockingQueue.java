package producer.consumer;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class MyBasicBlockingQueue {

	int max;
	Queue<Item> queue = new LinkedBlockingQueue<>();

	Object notEmptyQueue = new Object();
	Object notFullQueue = new Object();

	public MyBasicBlockingQueue(int max) {
		this.max = max;
	}

	public synchronized void put(Item item) throws InterruptedException {

		try {
			while (queue.size() == max) {
				notFullQueue.wait();
			}

			queue.add(item);

			notEmptyQueue.notifyAll();
		} catch (InterruptedException e) {

		}
	}

	public synchronized Item take() {

		Item item = null;
		try {

			while (queue.size() == 0) {
				notEmptyQueue.wait();
			}

			item = queue.remove();

			notFullQueue.notifyAll();

		} catch (InterruptedException e) {

		}
		return item;

	}
}
