package ejercicio12;

import java.util.LinkedList;
import java.util.Queue;

public class Museo {

	private int acceso;
	private int sala = 0;
	private Queue <Visitante> normales = new LinkedList<>();
	private Queue <Visitante> jubilados = new LinkedList<>();
	private int limitado;
	private int completo;
	private boolean aforoMax = true;
	public Museo(int acceso, int limitado, int completo) {
		this.acceso = acceso;
		this.limitado = limitado;
		this.completo = completo;
	}
	
	public synchronized void entrarSala(Visitante v) throws InterruptedException{ //tun tun, turun tun, turun turun tun, turin tirun tun
		normales.add(v);
		while(sala >= acceso && normales.peek() != v && !jubilados.isEmpty()) {
			System.out.println("---Sala llena. " +Thread.currentThread().getName()+", espera en la puta cola");
			wait();
		}
		normales.remove();
		System.out.println(">>>" +Thread.currentThread().getName()+" PASA A LA SALA" );
		sala++;	
	}
	public synchronized void entrarSalaJubilado(Visitante v) throws InterruptedException{ 
		normales.add(v);
		while(sala == acceso && jubilados.peek() != v) {
			System.out.println("---Sala llena. "+Thread.currentThread().getName()+", espera en la cola, si no es mucha molestia");
			wait();
		}
		normales.remove();
		System.out.println(">>>" +Thread.currentThread().getName()+" PASA A LA SALA" );
		sala++;	
	}
	public synchronized void salirSala(Visitante v) {
		sala--;
		System.out.println("<<<" +Thread.currentThread().getName()+" SALE DE LA SALA" );
		notifyAll();
	}
	
	public synchronized void notificarTemperatura(int temp) {
        if (temp > 30 && aforoMax) {
            this.acceso = this.limitado;
            System.out.println("[CALOR] Aforo bajado a " + this.limitado);
            aforoMax = false;
        } else if (temp <= 30 && !aforoMax) {
            this.acceso = this.completo;
            System.out.println("[TEMPERATURA OK] Aforo subido a " + this.completo);
            aforoMax =  true;
        }
        notifyAll(); // Importante: si el aforo sube, hay que avisar a los que esperan
    }
}
