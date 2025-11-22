package ejercicio4;

public class Productores extends Thread{

	private Buffer buffer;
	private int id;
	
	public Productores(Buffer buffer, int id) {
		this.buffer = buffer;
		this.id = id;
	}
	@Override
	public void run() {
		try {
			buffer.meter(id);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
