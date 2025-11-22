package ejercicio6;

import java.util.Random;

public class Canibal implements Runnable {
    private int id;
    private Marmita marmita;
    private Random random = new Random();

    public Canibal(int id, Marmita marmita) {
        this.id = id;
        this.marmita = marmita;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Simular vida del caníbal antes de tener hambre
                Thread.sleep(random.nextInt(5000));
                
                // INTENTA COMER (Bloqueante si está vacía)
                marmita.comer(id);
                
                // Come tranquilamente fuera del monitor
                System.out.println("Caníbal " + id + " está masticando...");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
