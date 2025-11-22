package ejercicioExtraCallable2;
import java.util.ArrayList;
import java.util.List;

public class Parte {
    private String nombre;
    private int unidades; // Unidades en inventario en este nodo
    private double defectoPorcentaje; // 0.0 a 1.0 (ej: 0.15 = 15% defecto)
    private List<Parte> componentes = new ArrayList<>();

    public Parte(String nombre, int unidades, double defecto) {
        this.nombre = nombre;
        this.unidades = unidades;
        this.defectoPorcentaje = defecto;
    }

    public void addComponente(Parte p) {
        this.componentes.add(p);
    }

    public int getUnidades() { return this.unidades; }
    public double getDefectoPorcentaje() { return this.defectoPorcentaje; }
    public List<Parte> getComponentes() { return this.componentes; }
}
