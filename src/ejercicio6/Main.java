package ejercicio6;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int capacidadMarmita = 5; // M raciones
        int numCanibales = 10;    // N caníbales

        // EL MONITOR (Tu trabajo estará aquí dentro)
        Marmita marmita = new Marmita(capacidadMarmita);

        // 1. Crear al Cocinero (Solo hay 1)
        new Thread(new Cocinero(marmita), "Cocinero").start();

        // 2. Crear a los Caníbales
        for (int i = 1; i <= numCanibales; i++) {
            new Thread(new Canibal(i, marmita), "Caníbal-" + i).start();
        }
    }
}
