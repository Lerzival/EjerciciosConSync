package ejercicio12;

public class Temperatura implements Runnable{

	// No necesitamos un array, solo el valor actual
    private int temperaturaActual = 20; 
    private Museo m;
    public Temperatura(Museo m) {
    	this.m = m;
    }
    @Override
    public void run() {
        boolean subiendo = true; // Esta bandera controla la dirección
        while (true) { // 1. Bucle infinito para que no pare nunca
            
            // 2. Simulación del tiempo (importante para que no sature la CPU)
            try {
                Thread.sleep(10); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 3. Lógica de oscilación
            if (subiendo) {
                temperaturaActual++;
            } else {
                temperaturaActual--;
            }

            // 4. Lógica de rebote (cambio de dirección)
            if (temperaturaActual >= 40) {
                subiendo = false; // Chocó arriba, empieza a bajar
             
            } else if (temperaturaActual <= 20) {
                subiendo = true;  // Chocó abajo, empieza a subir
                
            }
            
            System.out.println("Temperatura actual: " + temperaturaActual + "ºC");

            m.notificarTemperatura(temperaturaActual);
        }
    }

}

