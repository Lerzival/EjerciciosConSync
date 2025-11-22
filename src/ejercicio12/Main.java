package ejercicio12;

public class Main {

    public static void main(String[] args) {
        // Instanciamos tu Museo: (acceso inicial, limitado, completo)
        // Empieza con 50, si hace calor baja a 35, si hace bueno sube a 50.
        Museo Faluas = new Museo(50, 35, 50);

        // 1. Hebra de Temperatura (La que hicimos antes)
        // Asegúrate de que tu clase Temperatura tenga el método run() que llama a museo.notificarTemperatura
        Temperatura termostato = new Temperatura(Faluas);
        new Thread(termostato, "Termostato").start();

        // 2. Hebras de Visitantes (Avalancha para probar colas)
        for (int i = 1; i <= 70; i++) {

            // Nota: Asumo que tu clase Visitante recibe (Museo, boolean)
            Visitante v = new Visitante(Faluas);
            new Thread(v).start();
            
            try {
                Thread.sleep(50); // Entran poco a poco
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

