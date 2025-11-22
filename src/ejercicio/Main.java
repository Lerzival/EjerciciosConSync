package ejercicio;


public class Main {

    public static void main(String[] args) {
        RecursoMonitor recurso = new RecursoMonitor();
        int lectores = 10;
        int escritores = 3;
        
        System.out.println("--- INICIO SIMULACIÓN LECTORES-ESCRITORES (MONITOR MANUAL) ---");
        System.out.println("Configuración: " + lectores + " Lectores | " + escritores + " Escritores.");

        // Crear y lanzar hilos Lectores
        for (int i = 0; i < lectores; i++) {
            new Thread(new Reader(recurso, i + 1), "Lector-" + (i + 1)).start();
        }

        // Crear y lanzar hilos Escritores
        for (int i = 0; i < escritores; i++) {
            new Thread(new Writer(recurso, i + 1), "Escritor-" + (i + 1)).start();
        }
        
        // Nota: Las hebras son hilos Daemon y seguirán ejecutándose hasta que el main termine.
        // En un programa real, usaríamos Thread.join() o un ExecutorService para esperar.
    }
}
