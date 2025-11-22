package ejercicioExtraCallable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

public class TareaPeso implements Callable<Double> {
    private final Carpeta carpeta;
    private final ExecutorService executor;

    public TareaPeso(Carpeta carpeta, ExecutorService executor) {
        this.carpeta = carpeta;
        this.executor = executor;
    }

    @Override
    public Double call() throws Exception {
        double pesoTotal = 0;
        List<Future<Double>> futuros = new ArrayList<>();
        if (this.carpeta.getArchivos() != null) {
        	for(Archivo a : this.carpeta.getArchivos()) {
        	pesoTotal += a.getPesoMB();
        	}
        }
        if (this.carpeta.getSubcarpetas() != null) {
            for(Carpeta c : this.carpeta.getSubcarpetas()) {
            	TareaPeso subcarpetas = new TareaPeso(c,executor);
            	futuros.add(this.executor.submit(subcarpetas));	
            }
            for(Future<Double> futuro : futuros) {
            	pesoTotal += futuro.get();
            }
         }
    	
        return pesoTotal;
    }
}
