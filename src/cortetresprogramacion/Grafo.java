package cortetresprogramacion;
import java.util.*;
public class Grafo {
    private final int numEstaciones;
    private final List<List<NodoGrafo>> adyacencia;

    public Grafo(int numEstaciones) {
        this.numEstaciones = numEstaciones;
        adyacencia = new ArrayList<>(numEstaciones);
        for (int i = 0; i < numEstaciones; i++) {
            adyacencia.add(new ArrayList<>());
        }
    }

    public void agregarRuta(int origen, int destino, int peso) {
        adyacencia.get(origen).add(new NodoGrafo(destino, peso));
    }

    public int[] dijkstra(int inicio) {
        int[] distancias = new int[numEstaciones];
        Arrays.fill(distancias, Integer.MAX_VALUE);
        distancias[inicio] = 0;

        PriorityQueue<NodoGrafo> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.peso));
        pq.add(new NodoGrafo(inicio, 0));

        while (!pq.isEmpty()) {
            NodoGrafo nodoActual = pq.poll();
            int u = nodoActual.id;

            for (NodoGrafo vecino : adyacencia.get(u)) {
                int nuevoDist = distancias[u] + vecino.peso;
                if (nuevoDist < distancias[vecino.id]) {
                    distancias[vecino.id] = nuevoDist;
                    pq.add(new NodoGrafo(vecino.id, nuevoDist));
                }
            }
        }
        return distancias;
    }

    // Método para eliminar una estación
    public void eliminarEstacion(int estacion) {
        adyacencia.set(estacion, new ArrayList<>()); // Elimina todas las rutas salientes.
        for (List<NodoGrafo> lista : adyacencia) {
            lista.removeIf(nodo -> nodo.id == estacion); // Elimina todas las rutas entrantes.
        }
    }

    // Obtener las adyacencias de un nodo
    public List<NodoGrafo> obtenerAdyacencias(int nodo) {
        return adyacencia.get(nodo);
    }
}
