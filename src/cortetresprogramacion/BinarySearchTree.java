package cortetresprogramacion;

public class BinarySearchTree {
    NodoArbol raiz;

    public void insertar(Estacion estacion) {
        raiz = insertarRecursivo(raiz, estacion);
    }

    private NodoArbol insertarRecursivo(NodoArbol raiz, Estacion estacion) {
        if (raiz == null) {
            raiz = new NodoArbol(estacion);
            return raiz;
        }
        if (estacion.id < raiz.estacion.id) {
            raiz.izquierda = insertarRecursivo(raiz.izquierda, estacion);
        } else if (estacion.id > raiz.estacion.id) {
            raiz.derecha = insertarRecursivo(raiz.derecha, estacion);
        }
        return raiz;
    }

    public void recorridoInorden() {
        recorridoInorden(raiz);
    }

    private void recorridoInorden(NodoArbol nodo) {
        if (nodo != null) {
            recorridoInorden(nodo.izquierda);
            System.out.println("Estacion ID: " + nodo.estacion.id);
            recorridoInorden(nodo.derecha);
        }
    }
}
