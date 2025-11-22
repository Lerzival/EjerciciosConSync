package ejercicio;

public class Reader extends Thread{

		private RecursoMonitor recurso;
	    private int id;

	    public Reader (RecursoMonitor recurso, int id) { 
	        this.recurso = recurso; 
	        this.id = id;
	    }
	    
	    @Override
	    public void run() {
	        try {
	            // Un hilo lector puede hacer múltiples operaciones
	            for (int i = 0; i < 3; i++) {
	                
	                // 1. Intenta adquirir el derecho a leer (Entrada al Monitor)
	                recurso.adquirirLectura(id);
	                
	                // --- SECCIÓN CRÍTICA ---
	                int valorLeido = recurso.leerDato();
	                System.out.println("Lector " + id + " leyó: " + valorLeido + 
	                                   " (Lectores activos: " + (recurso.getLectoresActivos()) + ")");
	                Thread.sleep(50); // Simula el tiempo que tarda la lectura
	                // --- FIN SECCIÓN CRÍTICA ---
	                
	                // 2. Libera el derecho (Salida del Monitor)
	                recurso.liberarLectura(id);
	                
	                Thread.sleep(1000); // Tiempo fuera del recurso
	            }
	        } catch (InterruptedException e) {
	            Thread.currentThread().interrupt();
	        }
	    }
	}