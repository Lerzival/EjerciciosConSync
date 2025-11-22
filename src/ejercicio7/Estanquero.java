package ejercicio7;

import java.util.Random;

public class Estanquero implements Runnable {
    private Mesa mesa;
    private Random random = new Random();

    public Estanquero(Mesa mesa) {
        this.mesa = mesa;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // 1. Elige dos ingredientes al azar que NO sean iguales
                // (Lógica matemática simple para elegir 2 distintos de 0,1,2)
                int ing1 = random.nextInt(3);
                int ing2 = random.nextInt(3);
                while (ing1 == ing2) {
                    ing2 = random.nextInt(3);
                }

                // 2. Pone los ingredientes en la mesa y espera a que se consuman
                
                mesa.ponerIngredientes(ing1, ing2);
                System.out.println("Estanquero pone ingredientes: " + ing1 + " y " + ing2);
                
                // 3. Espera un poco antes de la siguiente ronda (opcional)
                // Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}