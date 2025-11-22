package ejercicio4;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {

    private Queue<Integer> datos = new LinkedList<>();
    int maxLength;
    private int contador = 0;
    
    public Buffer(int maxLength) {
    	this.maxLength = maxLength;
    }
    
    synchronized public void meter(int id) throws InterruptedException{
        while(datos.size() == this.maxLength) {
            System.out.println("Array de datos lleno, Productor " +id+ " a la espera");
            wait();
        }
        int dato = (int)(Math.random()*10000);
        System.out.println(">>>Productor " +id+ " añadiendo dato "+dato);
        datos.add(dato);
        contador++;
        if (contador == maxLength) { 
        	System.out.println("---Buffer VACÍO---");
        }
        notifyAll();
    }
    
    synchronized public void consumir(int id) throws InterruptedException{
        while(datos.isEmpty()) {
            System.out.println("Array de datos vacío, Consumidor " +id+ " a la espera");
            wait();
        }
        System.out.println(">>>Consumidor " +id+ " consumiendo dato");
        datos.remove();
        contador--;
        if (contador == 0) { 
        	System.out.println("---Buffer VACÍO---");
        }
        notifyAll();
    }
}