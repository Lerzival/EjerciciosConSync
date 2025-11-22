package ejercicio13;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class Peluqueria {

    private Queue<Ingles> sofas = new LinkedList<>();
    private int maxSofa;
    private int contador = 0; // 0 = Barbero libre, 1 = Ocupado
    private Random random = new Random();
    
    public Peluqueria(int maxSofa) {
        this.maxSofa = maxSofa;
    }
    
    public synchronized void atender(Ingles i) throws InterruptedException {
        sofas.add(i); // El cliente entra "virtualmente" a la lista de espera
        
        // CORRECCIÓN 1: Lógica de aforo. 
        // Si el barbero está ocupado Y los sofás están llenos (size > max), se va.
        // (Nota: Si el barbero está libre, pasa directo aunque los sofás estén llenos de gente esperando, lo cual es eficiente).
        if (contador >= 1 && sofas.size() > maxSofa) {
             System.out.println("<<<El ingles " + Thread.currentThread().getName() + " ve el local petado y se va");
             sofas.remove(i); // Se borra a sí mismo, no al primero
             return;
        }

        // CORRECCIÓN 2: Control de turno estricto (FIFO).
        // No basta con que el barbero esté libre (contador < 1).
        // Tienes que ser EL PRIMERO de la cola (sofas.peek() == i) para sentarte.
        while (contador >= 1 || sofas.peek() != i) {
            System.out.println("---Hay un ingles getting a low taper fade... " + Thread.currentThread().getName() + " espera.");
            wait();
        }

        // Si salgo del bucle es porque el barbero está libre Y soy el primero.
        sofas.remove(); // Ahora sí es seguro borrar al primero, porque SOY YO.
        
        System.out.println(">>>Ingles " + Thread.currentThread().getName() + " se sienta para un taper fade durisimo");
        contador++; 
    }
    
    public synchronized void atendido(Ingles i) {
        System.out.println("---Ingles " + Thread.currentThread().getName() + " recibe un TAPER FADE!!!!");
        contador--; // Libera la silla
        notifyAll(); // Avisa a los que duermen en los sofás
    }
    
    public void simularCortePelo() {
        try {
            Thread.sleep(random.nextInt(401));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}