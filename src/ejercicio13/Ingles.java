package ejercicio13;

public class Ingles implements Runnable{

	private Peluqueria pelu;
	
	public Ingles(Peluqueria pelu) {
		this.pelu = pelu;
	}
	@Override
	public  void run() {
		try {
			pelu.atender(this);
			pelu.simularCortePelo();
			pelu.atendido(this);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
