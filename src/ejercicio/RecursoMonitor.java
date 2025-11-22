package ejercicio;

import java.util.Random;

public class RecursoMonitor {
    
    // --- Variables de Estado del Monitor ---
    private int dato = 0;
    private int lectoresActivos = 0;
    private boolean escritorActivo = false;
    private int escritoresPendientes = 0; // Clave para evitar el Starvation
    private final Random random = new Random();
    
    // --- LÓGICA DE ESCRITURA (Adquisición Exclusiva) ---
    public synchronized void adquirirEscritura(int id) throws InterruptedException {
        escritoresPendientes++;
        
        // El escritor espera si:
        // 1. Hay otro escritor activo (escritorActivo == true)
        // 2. Hay lectores activos (lectoresActivos > 0)
        while (escritorActivo || lectoresActivos > 0) {
            System.out.println("ESCRITOR " + id + " esperando para entrar...");
            wait(); // Libera el cerrojo y se va a dormir
        }
        
        // Adquisición del acceso
        escritoresPendientes--;
        escritorActivo = true;
    }

    public synchronized void liberarEscritura(int id) {
        escritorActivo = false;
        System.out.println("<<< ESCRITOR " + id + " libera el recurso >>>");
        // Despertar a todos (lectores y escritores) para que reevalúen la entrada
        notifyAll();
    }
    
    // --- LÓGICA DE LECTURA (Adquisición Compartida) ---
    public synchronized void adquirirLectura(int id) throws InterruptedException {
        
        // El lector espera si:
        // 1. Hay un escritor activo (escritorActivo == true)
        // 2. Hay escritores esperando (escritoresPendientes > 0) -> Regla Anti-Starvation
        while (escritorActivo || escritoresPendientes > 0) {
             System.out.println("Lector " + id + " esperando para entrar...");
             wait(); // Libera el cerrojo y se va a dormir
        }
        
        // Adquisición del acceso (Lectura compartida)
        lectoresActivos++;
    }

    public synchronized void liberarLectura(int id) {
        lectoresActivos--;
        
        // Si es el último lector saliendo, notificar para que el escritor pendiente entre.
        if (lectoresActivos == 0) {
            notifyAll(); 
        }
    }
    
    // --- MÉTODOS DE OPERACIÓN (Fuera del Monitor para no bloquear) ---
    public int leerDato() {
        return dato;
    }

    public int escribirDato() {
        int nuevoValor = random.nextInt(10001);
        dato = nuevoValor;
        return nuevoValor;
    }
    
    public int getLectoresActivos() {
    	return lectoresActivos;
    }
}
