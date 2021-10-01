package theard.simple;
public class HowToStopThread {

    public static void main(String[] args) {
    	StoppableRunnable myRunnable = new StoppableRunnable();

        Thread thread = new Thread(myRunnable);

        thread.start();

        try {
            Thread.sleep(10L * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myRunnable.doStop();
    }
}


class StoppableRunnable implements Runnable {

    private boolean doStop = false;

    public synchronized void doStop() {//should be called from other thread
        this.doStop = true;
    }

    private synchronized boolean keepRunning() {
        return this.doStop == false;
    }

    @Override
    public void run() {
        while(keepRunning()) {
            // keep doing what this thread should do.
            System.out.println("Running");

            try {
                Thread.sleep(3L * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
/* output
Running
Running
Running
Running
http://tutorials.jenkov.com/java-concurrency/creating-and-starting-threads.html

 */