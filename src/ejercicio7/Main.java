package ejercicio7;
public class Main {
    public static void main(String[] args) {
        Mesa mesa = new Mesa();

        // Hilo del Estanquero
        new Thread(new Estanquero(mesa), "Estanquero").start();

        // Hilos de los 3 Fumadores
        // Fumador 0: Tiene TABACO (necesita Papel + Cerillas)
        new Thread(new Fumador(Constantes.TABACO, mesa), "Fumador Tabaco").start();
        
        // Fumador 1: Tiene PAPEL (necesita Tabaco + Cerillas)
        new Thread(new Fumador(Constantes.PAPEL, mesa), "Fumador Papel").start();
        
        // Fumador 2: Tiene CERILLAS (necesita Tabaco + Papel)
        new Thread(new Fumador(Constantes.CERILLAS, mesa), "Fumador Cerillas").start();
    }
}