package ejercicio4;

public class Main {
	public static void main(String[] args) {
		Buffer buf = new Buffer(10);
		for (int i = 0; i < 110; i ++) {
			try {
				Thread.sleep((int) (Math.random() * 50));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Productores p = new Productores(buf,i);
			Consumidores c = new Consumidores(buf,i);
			p.start();
			try {
				Thread.sleep((int) (Math.random() * 400));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			c.start();
		}
	}
}