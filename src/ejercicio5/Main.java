package ejercicio5;

public class Main {

    public static void main(String[] args) {
        int numComensales = 5;
        Tenedor[] tenedores = new Tenedor[numComensales];
        Filosofo[] filosofos = new Filosofo[numComensales];

        // 1. Crear los tenedores
        for (int i = 0; i < numComensales; i++) {
            tenedores[i] = new Tenedor(i);
        }

        // 2. Crear los filósofos y asignarles sus tenedores (Mesa Circular)
        for (int i = 0; i < numComensales; i++) {
            // El de la izquierda es el 'i'
            // El de la derecha es el '(i + 1) % 5' (Para que el último use el 0)
            Tenedor izquierda = tenedores[i];
            Tenedor derecha = tenedores[(i + 1) % numComensales];
            
            filosofos[i] = new Filosofo(i, izquierda, derecha);
            new Thread(filosofos[i], "Filósofo " + i).start();
        }
    }
}
