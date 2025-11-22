package ejercicioExtraCallable2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;

public class TareaInventario implements Callable<ResultadoTraversa> {
    private final Parte parte;
    private final ExecutorService executor;

    public TareaInventario(Parte parte, ExecutorService executor) {
        this.parte = parte;
        this.executor = executor;
    }

    @Override
    public ResultadoTraversa call() throws Exception {
        // [TU CÓDIGO VA AQUÍ]
        // Tareas:
        // 1. Caso base (null)
        // 2. Inicializa Count con tus propias unidades.
        // 3. Inicializa MaxDefect con tu propio porcentaje.
        // 4. Crea List<Future<ResultadoTraversa>>.
        // 5. Bucle recursivo/paralelo (submit nuevas tareas).
        // 6. Bucle de recolección (future.get()) y agregación dual (suma + Math.max).
        
        return new ResultadoTraversa(0, 0.0); // Placeholder
    }
}
