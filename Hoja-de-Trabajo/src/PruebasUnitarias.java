//Pruebas Unitarias
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;
public class PruebasUnitarias {

   FloydWarshall grafo;
   HashMap<String, Integer> nodos = new HashMap<String, Integer>();
    HashMap<Integer, String> nodosColum = new HashMap<Integer, String>();
    ArrayList<String> filas = new ArrayList<String>();
    int tamaño = 0;
    String[] lista;
    String ciudad1, ciudad2;
    String data = "Lima Quito 10 12 15 20";

    


    //Prueba para añadir un elemento
    @Test 
    public void agregararcos(){
        filas.add(data);

    for (String linea : filas) {
      lista = linea.split(" ");
      if (nodos.get(lista[0]) == null){
        tamaño++;
        nodos.put(lista[0], tamaño);
        nodosColum.put(tamaño, lista[0]);
      }
      if (nodos.get(lista[1]) == null) {
        tamaño++;
        nodos.put(lista[1], tamaño);
        nodosColum.put(tamaño, lista[1]);
      }
    }
    grafo = new FloydWarshall(nodos, nodosColum, tamaño);
    for (String linea : filas) {
      lista = linea.split(" ");
      grafo.setMatriz(nodos.get(lista[0]) -1, nodos.get(lista[1]) - 1, Integer.valueOf(lista[2]));
    }
    grafo.Warshall();

    grafo.setMatriz(0, 1, 3);
    int x = grafo.getMatriz()[0][1];

    assertEquals(3,x);

    
    }

    //Prueba para buscar un elemento
    @Test
    public void eliminararcos() {
        filas.add(data);

        for (String linea : filas) {
          lista = linea.split(" ");
          if (nodos.get(lista[0]) == null){
            tamaño++;
            nodos.put(lista[0], tamaño);
            nodosColum.put(tamaño, lista[0]);
          }
          if (nodos.get(lista[1]) == null) {
            tamaño++;
            nodos.put(lista[1], tamaño);
            nodosColum.put(tamaño, lista[1]);
          }
        }
        grafo = new FloydWarshall(nodos, nodosColum, tamaño);
        for (String linea : filas) {
          lista = linea.split(" ");
          grafo.setMatriz(nodos.get(lista[0]) -1, nodos.get(lista[1]) - 1, Integer.valueOf(lista[2]));
        }
        grafo.Warshall();
    
        grafo.setInterrupcion(0, 1);

       assertNull(grafo.getMatriz()[0][1]);
        
        
    }

    //Prueba para obtener un elemento del árbol
    @Test 
    public void agregarnodos() {

        filas.add(data);

        for (String linea : filas) {
          lista = linea.split(" ");
          if (nodos.get(lista[0]) == null){
            tamaño++;
            nodos.put(lista[0], tamaño);
            nodosColum.put(tamaño, lista[0]);
          }
          if (nodos.get(lista[1]) == null) {
            tamaño++;
            nodos.put(lista[1], tamaño);
            nodosColum.put(tamaño, lista[1]);
          }
        }
        grafo = new FloydWarshall(nodos, nodosColum, tamaño);
        for (String linea : filas) {
          lista = linea.split(" ");
          grafo.setMatriz(nodos.get(lista[0]) -1, nodos.get(lista[1]) - 1, Integer.valueOf(lista[2]));
        }
        grafo.Warshall();

        grafo.setMatriz(0, 0, 7);
        int x = grafo.getMatriz()[0][0];

        assertEquals(7,x);



        

    }

  

    //Prueba traduccion
    @Test
    public void algoritmofloid() {

        filas.add(data);

        for (String linea : filas) {
          lista = linea.split(" ");
          if (nodos.get(lista[0]) == null){
            tamaño++;
            nodos.put(lista[0], tamaño);
            nodosColum.put(tamaño, lista[0]);
          }
          if (nodos.get(lista[1]) == null) {
            tamaño++;
            nodos.put(lista[1], tamaño);
            nodosColum.put(tamaño, lista[1]);
          }
        }
        grafo = new FloydWarshall(nodos, nodosColum, tamaño);
        for (String linea : filas) {
          lista = linea.split(" ");
          grafo.setMatriz(nodos.get(lista[0]) -1, nodos.get(lista[1]) - 1, Integer.valueOf(lista[2]));
        }
        grafo.Warshall();

        String y = grafo.ruta(0, 1);
        String x = grafo.ruta(nodos.get("Lima") -1, nodos.get("Quito") -1);
        assertEquals(x,y);
        
    }
    
}