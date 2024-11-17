package cortetresprogramacion;

public class NodoArbol {
    Estacion estacion;
    NodoArbol izquierda, derecha;

    public NodoArbol(Estacion estacion) {
        this.estacion = estacion;
        izquierda = derecha = null;
    }
}
