package ejercicioExtraCallable;

import java.util.ArrayList;
import java.util.List;

public class Carpeta {
    private String nombre;
    private List<Archivo> archivos = new ArrayList<>();
    private List<Carpeta> subcarpetas = new ArrayList<>();

    public Carpeta(String nombre) { this.nombre = nombre; }
    public void addArchivo(double peso) { this.archivos.add(new Archivo(peso)); }
    public void addCarpeta(Carpeta c) { this.subcarpetas.add(c); }
    public List<Archivo> getArchivos() { return this.archivos; }
    public List<Carpeta> getSubcarpetas() { return this.subcarpetas; }
}
