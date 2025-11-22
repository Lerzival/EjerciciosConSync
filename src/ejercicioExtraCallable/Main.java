package ejercicioExtraCallable;

import java.util.concurrent.*;

public class Main{

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        
        // Configuración de la Concurrencia
        // Usamos un FixedThreadPool para gestionar el número de trabajadores (hilos)
        ExecutorService executor = Executors.newFixedThreadPool(4); 
        
        // 1. Construir la estructura (El Árbol)
        // Estructura: Raíz (10) -> Docs (5+5), Fotos (3+3)
        // Total esperado: 10 + 10 + 6 = 26.0
        
        Carpeta docs = new Carpeta("Docs");
        docs.addArchivo(5.0); // 5.0
        docs.addArchivo(5.0); // 5.0
        
        Carpeta fotos = new Carpeta("Fotos");
        fotos.addArchivo(3.0); // 3.0
        fotos.addArchivo(3.0); // 3.0

        Carpeta raiz = new Carpeta("Raiz");
        raiz.addArchivo(10.0); // 10.0
        raiz.addCarpeta(docs);
        raiz.addCarpeta(fotos);
        
        System.out.println("Iniciando cálculo concurrente. Total esperado: 26.0 MB");
        
        TareaPeso total = new TareaPeso(raiz, executor);
        
        Future<Double> pesoTotalFuturo = executor.submit(total);
        
        double pesoTotal = pesoTotalFuturo.get();
        
        
        if (Math.abs(pesoTotal - 26.0) < 0.001) {
            System.out.println(">>> ÉXITO: Recursividad y paralelismo combinados.");
        } else {
            System.out.println(">>> FALLO.");
        }
    }
}