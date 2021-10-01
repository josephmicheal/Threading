package producer.consumer;

public class Item{
	long id;
	Item(long id){
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "id : "+this.id;
	}
}
