package ejercicioExtraCallable2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        
        // 1. Configurar el pool de hilos
        ExecutorService executor = Executors.newFixedThreadPool(4); 
        
        // 2. Construir la jerarquía de prueba
        Parte tuerca = new Parte("Tuerca", 1000, 0.05); // 5% defectuoso
        Parte piston = new Parte("Pistón", 50, 0.02);   // 2% defectuoso
        
        Parte motor = new Parte("Motor V8", 1, 0.01);  // 1% defectuoso
        motor.addComponente(piston);
        motor.addComponente(tuerca);

        // Nivel 0 (Producto final)
        Parte coche = new Parte("Coche Completo", 1, 0.00); // 0% defectuoso
        coche.addComponente(motor);
        
        // RESULTADO ESPERADO MANUAL: TODO
        // Total Unidades: 1 (Coche) + 1 (Motor) + 50 (Pistón) + 1000 (Tuerca) = 1052
        // Max Defecto: El más alto de toda la rama es la Tuerca (0.05) o 5%.

        // 3. Crear y someter la tarea raíz
        TareaInventario tareaRaiz = new TareaInventario(coche, executor);
        Future<ResultadoTraversa> resultadoFuture = executor.submit(tareaRaiz);
        
        // 4. Obtener y mostrar el resultado
        ResultadoTraversa resultado = resultadoFuture.get();
        
        System.out.println("--- Análisis de Inventario Paralelo ---");
        System.out.println("Total de Piezas (Esperado: 1052): " + resultado.totalCount);
        System.out.println("Peor Tasa de Defecto (Esperada: 0.05): " + resultado.maxDefectRate);
        
        // 5. Cerrar el executor
        executor.shutdown();
        
        if (resultado.totalCount == 1052 && resultado.maxDefectRate == 0.05) {
            System.out.println("\n>>> ¡ÉXITO! La lógica dual concurrente es correcta.");
        }
    }
}
