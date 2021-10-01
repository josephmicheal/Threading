package theard.simple;

import java.util.Map;

public class RaceConditions {
	
	/*
	 Two Types of Race Conditions
	 
	 Read-modify-write
	 Check-then-act
	 
	 */

	public class Counter {

	     protected long count = 0;

	     public void add(long value){
	         this.count = this.count + value;
	     }
	  }
	
	/*
	this.count = 0;

   A:  Reads this.count into a register (0)
   B:  Reads this.count into a register (0)
   B:  Adds value 2 to register
   B:  Writes register value (2) back to memory. this.count now equals 2
   A:  Adds value 3 to register
   A:  Writes register value (3) back to memory. this.count now equals 3
   
   finally it is not 5
	 */
}

class CheckThenActExample {

    public void checkThenAct(Map<String, String> sharedMap) {
        if(sharedMap.containsKey("key")){
            String val = sharedMap.remove("key");
            if(val == null) {
                System.out.println("Value for 'key' was null");
            }
        } else {
            sharedMap.put("key", "value");
        }
    }
}

/*
Larger critical sections it may be beneficial to break the critical section 
into smaller critical sections, to allow multiple threads to execute each a 
smaller critical section.  
 */
class TwoSums {
    
    private int sum1 = 0;
    private int sum2 = 0;
    
    public void add(int val1, int val2){
        synchronized(this){
            this.sum1 += val1;   
            this.sum2 += val2;
        }
    }
}

class TwoSumsUpdated {
    
    private int sum1 = 0;
    private int sum2 = 0;

    private Integer sum1Lock = new Integer(1);
    private Integer sum2Lock = new Integer(2);

    public void add(int val1, int val2){
        synchronized(this.sum1Lock){
            this.sum1 += val1;   
        }
        synchronized(this.sum2Lock){
            this.sum2 += val2;
        }
    }
}



//---------------------------
class NotThreadSafe{
    StringBuilder builder = new StringBuilder();

    public void add(String text){
        this.builder.append(text);
    }
}

class MyRunnable implements Runnable{
  NotThreadSafe instance = null;

  public MyRunnable(NotThreadSafe instance){
    this.instance = instance;
  }

  public void run(){
    this.instance.add("some text");
  }
  
  public static void main(String args[]) {
	  NotThreadSafe sharedInstance = new NotThreadSafe();

	  new Thread(new MyRunnable(sharedInstance)).start();
	  new Thread(new MyRunnable(sharedInstance)).start();
	  
	  // below using diff objects
	  
	  new Thread(new MyRunnable(new NotThreadSafe())).start();
	  new Thread(new MyRunnable(new NotThreadSafe())).start();
  }
}