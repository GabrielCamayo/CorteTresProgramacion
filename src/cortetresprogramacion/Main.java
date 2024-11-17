package cortetresprogramacion;

public class Main {

    public static void main(String[] args) {
        // Crear el árbol de estaciones
        BinarySearchTree bst = new BinarySearchTree();
        bst.insertar(new Estacion(1, 10.0, 20.0));
        bst.insertar(new Estacion(2, 15.0, 25.0));
        bst.insertar(new Estacion(3, 5.0, 18.0));

        System.out.println("Recorrido inorden del arbol:");
        bst.recorridoInorden();
        RedTransporte redTransporte = new RedTransporte(4);
        // Agregar estaciones
        redTransporte.agregarEstacion(0, 10.0, 20.0);
        redTransporte.agregarEstacion(1, 15.0, 25.0);
        redTransporte.agregarEstacion(2, 5.0, 18.0);
        redTransporte.agregarEstacion(3, 12.0, 22.0);

        // Agregar rutas
        redTransporte.agregarRuta(0, 1, 10);
        redTransporte.agregarRuta(1, 2, 20);
        redTransporte.agregarRuta(2, 3, 30);
        redTransporte.agregarRuta(0, 3, 60);

        // Calcular la ruta más corta de la estación 0 a la 3
        redTransporte.encontrarRutaMasCorta(0, 3);

        // Calcular tiempo de viaje
        redTransporte.calcularTiempoDeViaje(0, 3, 50.0);

        // Encontrar estaciones críticas
        redTransporte.encontrarEstacionesCriticas();
    }
}
