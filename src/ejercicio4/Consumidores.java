package ejercicio4;

public class Consumidores extends Thread{

	private Buffer buffer;
	private int id;
	
	public Consumidores (Buffer buffer, int id) {
		this.buffer = buffer;
		this.id = id;
	}
	@Override
	public void run() {
		try {
			buffer.consumir(id);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

