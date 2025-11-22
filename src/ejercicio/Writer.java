package ejercicio;

public class Writer extends Thread{

	private RecursoMonitor recurso;
	private int id;
	
	public Writer(RecursoMonitor recurso,int id){
		this.recurso = recurso;
		this.id = id;
	}
	@Override
	public void run() {
		try {
			
			for (int i = 0; i < 2; i++) {
			recurso.adquirirEscritura(id);
			
			int nuevo = recurso.escribirDato();
			System.out.println(">>> ESCRITOR " + id + " escribió el valor: " + nuevo);
			Thread.sleep(200);
			
			recurso.liberarEscritura(id);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
