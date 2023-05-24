import java.util.HashMap;

public class App {
        FloydWarshall grafo;

        public void interrupcion(String fila, String columna, HashMap<String, Integer> nodos) {
            grafo.setInterrupcion(nodos.get(fila) - 1, nodos.get(columna) - 1);
        }

        public void recalcular() {
            grafo.Warshall();
        }

        public void modificarRuta(String fila, String columna, int size, HashMap<String, Integer> nodos) {
            grafo.setMatriz(nodos.get(fila) - 1, nodos.get(fila) - 1, size);
            grafo.setPath(nodos.get(fila) - 1, nodos.get(columna) - 1);
        }
    
}
