package ejercicio6;

public class Marmita {
    private int raciones;
    private int capacidad;
    // ¿Quizás necesites alguna bandera extra para saber si el cocinero está trabajando?
    private boolean cocineroAvisado = false; 

    public Marmita(int capacidad) {
        this.capacidad = capacidad;
        this.raciones = capacidad; // Empieza llena
        System.out.println("--- MARMITA LISTA CON " + raciones + " RACIONES ---");
    }

    // Ejecutado por CANÍBALES
    public synchronized void comer(int idCanibal) throws InterruptedException {
    	// 1. Si está vacía, activamos el protocolo de espera y aviso
        if (raciones == 0) {
            System.out.println("[ALERTA] Caníbal " + idCanibal + " encuentra vacía. Avisando...");
            
            // SOLO EL PRIMERO avisa. Los demás se duermen en silencio.
            if (!cocineroAvisado) { 
                cocineroAvisado = true;
                notifyAll(); // Despertar al Cocinero
            }

            // Los caníbales esperan DENTRO de un while robusto
            while (raciones == 0) { 
                wait(); 
            }
        }

        // 2. Consumo normal
        raciones--;
        System.out.println("Caníbal " + idCanibal + " se sirve. Quedan " + raciones + " raciones.");
    }

    // Ejecutado por el COCINERO
    public synchronized void rellenar() throws InterruptedException {
    	while(raciones > 0) {
    		System.out.println("[COCINERO] Está durmiendo. Hay " + raciones + " raciones.");
    		wait();
    	}

    	System.out.println(" [COCINERO] Marmita vacía, empieza a cocinar...");
        
    	for (int i = 0; i < capacidad; i++) {
    		Thread.sleep(100); // Simular el tiempo de cocción (1 segundo)
    		raciones++;
    	}
    	
    	System.out.println(" [COCINERO] ¡Raciones listas! Hay " + raciones + ".");
    	notifyAll();
    	cocineroAvisado = false;
    }
}
