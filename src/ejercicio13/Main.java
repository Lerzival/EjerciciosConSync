package ejercicio13;
import java.util.Random;

public class Main {
	public static void main(String[] args) {
        int NUM_CLIENTES = 50;
        Peluqueria peluqueria = new Peluqueria(5);
        Random rand = new Random();

        System.out.println("--- Iniciando Simulación 2.13 (1 Silla, 5 Sofás) ---");

        for (int i = 0; i < NUM_CLIENTES; i++) {
            Ingles cliente = new Ingles(peluqueria);
            Thread hebraCliente = new Thread(cliente, "Cliente-" + i);
            hebraCliente.start();
            
            try {
                // Hacemos que los clientes lleguen en intervalos aleatorios
                Thread.sleep(rand.nextInt(100)); // Un nuevo cliente cada 0-100 ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("--- Todos los clientes han sido generados ---");
    }
}
