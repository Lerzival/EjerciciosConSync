package ejercicio6;

public class Cocinero implements Runnable {
    private Marmita marmita;

    public Cocinero(Marmita marmita) {
        this.marmita = marmita;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // El cocinero espera pasivamente a que le llamen dentro de este método
                marmita.rellenar();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}