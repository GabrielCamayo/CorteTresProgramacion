package cortetresprogramacion;
import java.util.ArrayList;
import java.util.List;
public class RedTransporte {
    private ArrayList<Estacion> estaciones;
    private Grafo grafoRutas;

    public RedTransporte(int numEstaciones) {
        estaciones = new ArrayList<>();
        grafoRutas = new Grafo(numEstaciones);
    }

    public void agregarEstacion(int id, double latitud, double longitud) {
        estaciones.add(new Estacion(id, latitud, longitud));
    }

    public void agregarRuta(int origen, int destino, int peso) {
        grafoRutas.agregarRuta(origen, destino, peso);
    }

    public void encontrarRutaMasCorta(int inicio, int destino) {
        int[] distancias = grafoRutas.dijkstra(inicio);
        System.out.println("Distancia mas corta de " + inicio + " a " + destino + ": " + distancias[destino]);
    }

    public void mostrarEstaciones() {
        for (Estacion estacion : estaciones) {
            System.out.println("Estacion ID: " + estacion.id);
        }
    }

    // Nuevo Método 1: Calcular Tiempo de Viaje
    public double calcularTiempoDeViaje(int inicio, int destino, double velocidadPromedio) {
        int[] distancias = grafoRutas.dijkstra(inicio);
        double distancia = distancias[destino];
        if (distancia == Integer.MAX_VALUE) {
            System.out.println("No existe una ruta entre las estaciones seleccionadas.");
            return -1; // Indica que no hay conexión.
        }
        double tiempo = distancia / velocidadPromedio;
        System.out.println("El tiempo estimado de viaje de " + inicio + " a " + destino + " es: " + tiempo + " Horas.");
        return tiempo;
    }

    // Nuevo Método 2: Encontrar Estaciones Críticas
    public List<Integer> encontrarEstacionesCriticas() {
        List<Integer> estacionesCriticas = new ArrayList<>();
        for (int i = 0; i < estaciones.size(); i++) {
            Grafo grafoTemporal = clonarGrafo(grafoRutas); // Clona el grafo original.
            grafoTemporal.eliminarEstacion(i); // Elimina la estación del grafo temporal.
            if (!esConexo(grafoTemporal)) { // Verifica si el grafo sigue siendo conexo.
                estacionesCriticas.add(i);
            }
        }
        System.out.println("Estaciones criticas: " + estacionesCriticas);
        return estacionesCriticas;
    }

    // Método auxiliar para clonar el grafo
    private Grafo clonarGrafo(Grafo original) {
        Grafo copia = new Grafo(estaciones.size());
        for (int i = 0; i < estaciones.size(); i++) {
            for (NodoGrafo nodo : original.obtenerAdyacencias(i)) {
                copia.agregarRuta(i, nodo.id, nodo.peso);
            }
        }
        return copia;
    }

    // Método auxiliar para verificar la conectividad
    private boolean esConexo(Grafo grafo) {
        // Implementa un recorrido DFS o BFS desde cualquier nodo y verifica si todos son accesibles.
        boolean[] visitados = new boolean[estaciones.size()];
        dfs(grafo, 0, visitados);
        for (boolean visitado : visitados) {
            if (!visitado) return false;
        }
        return true;
    }

    private void dfs(Grafo grafo, int nodo, boolean[] visitados) {
        visitados[nodo] = true;
        for (NodoGrafo vecino : grafo.obtenerAdyacencias(nodo)) {
            if (!visitados[vecino.id]) {
                dfs(grafo, vecino.id, visitados);
            }
        }
    }
}
