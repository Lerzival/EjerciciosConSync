package ejercicio12;

import java.util.Random;

public class Visitante implements Runnable{

	private int priority;
	private Random random = new Random();
	private Museo m;
	public Visitante(Museo m) {
		this.priority = random.nextInt(2);
		this.m = m;
	}

	@Override
	public void run() {
		
		try {
			if (priority == 1) {
				System.out.println("Se presenta un VIEJECITO");
			
				m.entrarSalaJubilado(this);
			try {
                Thread.sleep(100); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
			m.salirSala(this);
		}else {
			System.out.println("Se presenta un VISITANTE NORMAL");
			m.entrarSala(this);
			try {
                Thread.sleep(70); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
			m.salirSala(this);
		}
	} catch (InterruptedException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}
}