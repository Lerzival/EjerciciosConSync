package ejercicio5;

import java.util.Random;

public class Filosofo implements Runnable{
	private int id;
    private Tenedor tenedorIzq;
    private Tenedor tenedorDer;
    private Random random = new Random();

    public Filosofo(int id, Tenedor izq, Tenedor der) {
        this.id = id;
        this.tenedorIzq = izq;
        this.tenedorDer = der;
    }

    @Override
    public void run() {
        try {
            while (true) {
                pensar();
                comer();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void pensar() throws InterruptedException {
        System.out.println("Filósofo " + id + " está pensando...");
        Thread.sleep(random.nextInt(1000)); // Piensa un rato aleatorio
    }

    // ---------------------------------------------------------
    // TU EJERCICIO ESTÁ AQUÍ
    // ---------------------------------------------------------
    private void comer() throws InterruptedException {
        // PISTAS MÍNIMAS:
        // 1. Tienes 'this.tenedorIzq' y 'this.tenedorDer'.
        // 2. Para coger un tenedor de forma exclusiva, usa 'synchronized(objeto)'.
        // 3. Tienes que coger los dos para poder imprimir "Filósofo X está comiendo".
        // 4. OJO: El orden en que los coges determina si hay bloqueo o no.
    	if (this.id % 2 == 0) { 
            // Los pares cogen Izquierda -> Derecha
            tenedorIzq.coger();
            tenedorDer.coger();
        } else {
            // Los impares cogen Derecha -> Izquierda
            // Esto evita que TODOS tengan el tenedor izquierdo a la vez.
            tenedorDer.coger();
            tenedorIzq.coger();
        }  
    	System.out.println("Filósofo " + id + " está comiendo ñam ñam");
        Thread.sleep(random.nextInt(1000) + 500); 

        tenedorDer.soltar();
        tenedorIzq.soltar();
        
        System.out.println("Filósofo " + id + " ha terminado.");
    	
    }
}

