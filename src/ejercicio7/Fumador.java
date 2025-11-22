package ejercicio7;
public class Fumador implements Runnable {
    private int miIngrediente; // Lo que este fumador tiene infinito
    private Mesa mesa;

    public Fumador(int miIngrediente, Mesa mesa) {
        this.miIngrediente = miIngrediente;
        this.mesa = mesa;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // 1. Intenta coger los ingredientes que le faltan
                // (Si no están, se quedará bloqueado dentro de este método)
                mesa.cogerIngredientes(miIngrediente);

                // 2. Si sale del método anterior, es que ha conseguido liar el cigarro
                System.out.println("Fumador con " + miIngrediente + " está fumando...");
                Thread.sleep(1000); // Simular fumar
                System.out.println("Fumador con " + miIngrediente + " ha terminado.");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
