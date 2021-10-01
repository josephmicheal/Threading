package theard.simple;

public class Run100Programs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Runnable runnable = () -> {
			System.out.println("Thread :"+Thread.currentThread().getName());
			try {
				Thread.sleep(50000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		
		for(int i = 0;i < 100;i++) {
			Thread thread1 = new Thread(runnable);
			thread1.start();
		}
	}

}
