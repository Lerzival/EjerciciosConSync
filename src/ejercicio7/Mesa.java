package ejercicio7;

import java.util.ArrayList;
import java.util.List;

public class Mesa {

    private List<Integer> mesa = new ArrayList<>();
    public Mesa() {
    }

    /**
     * El estanquero llama a este método para poner dos ingredientes.
     * Debe bloquearse si la mesa no está vacía.
     * @param i1 Primer ingrediente (ej: Constantes.TABACO)
     * @param i2 Segundo ingrediente (ej: Constantes.PAPEL)
     */
    public synchronized void ponerIngredientes(int i1, int i2) throws InterruptedException {
        while(!mesa.isEmpty()) {
        	wait();
        }
        mesa.add(i1);
        mesa.add(i2);
        notifyAll();
    }

    /**
     * Un fumador llama a este método intentando coger lo que le falta.
     * @param miIngrediente El ingrediente que el fumador YA TIENE (infinito).
     * Ej: Si pasa TABACO, es porque quiere PAPEL y CERILLAS.
     * Debe bloquearse si lo que hay en la mesa no es exactamente lo que necesita.
     */
    public synchronized void cogerIngredientes(int miIngrediente) throws InterruptedException {
        while(mesa.isEmpty() || mesa.contains(miIngrediente)){
        	wait();
        }
        mesa.removeAll(mesa);
        System.out.println("Fumador con " + miIngrediente + " ha cogido ingredientes.");
        notifyAll();
    }
}