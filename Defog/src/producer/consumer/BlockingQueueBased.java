package producer.consumer;

public class BlockingQueueBased {

	public static void main(String[] args) {
		
		//BlockingQueue<Object> queue = new ArrayBlockingQueue<>(10);
		//MyBlockingQueue queue = new MyBlockingQueue(10);
		MyBasicBlockingQueue queue = new MyBasicBlockingQueue(5);
		
		Runnable producer = new Runnable() {			
			@Override
			public void run() {
				while(true) {
					Item item = new Item(System.currentTimeMillis());
					System.out.println("Produced "+item);
					try {
						queue.put(item);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		
		new Thread(producer).start();
		new Thread(producer).start();
		
		Runnable consumer = new Runnable() {			
			@Override
			public void run() {
				while(true) {
					Object item = null;
					try {
						item = queue.take();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Consumed "+item);	
				}
			}
		};
		
		new Thread(consumer).start();
		new Thread(consumer).start();
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

