package ejercicio5;

public class Tenedor {
	private int id;
	private boolean enUso = false;
    public Tenedor(int id) { 
    	this.id = id; 
    	}
    public int getId() { 
    	return id; 
    }
    public synchronized void coger() throws InterruptedException{
    	while (this.enUso) {
    		wait();
    	}
    	enUso = true;
    }
    public synchronized void soltar() throws InterruptedException{
    	enUso = false;
    	notifyAll();
    }
}
