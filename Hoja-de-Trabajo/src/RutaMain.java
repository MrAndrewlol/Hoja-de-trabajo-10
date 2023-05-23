import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner; // Import the Scanner class to read text files

public class RutaMain {
  public static void main(String[] args) {
	  System.out.println("Ingrese la ruta del archivo ej C:\\ejemplos\\example1.txt");
	  Scanner in = new Scanner(System.in);
	  String fpath = in.nextLine(); 

    FloydWarshall grafo;

    HashMap<String, Integer> nodos = new HashMap<String, Integer>();
    HashMap<Integer, String> nodosColum = new HashMap<Integer, String>();
    ArrayList<String> filas = new ArrayList<String>();
    String fila = "", columna = "";
    int tamaño = 0;
    String[] lista;

    try {
      File myObj = new File(fpath);
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        filas.add(myReader.nextLine());
      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

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
    grafo.FloydWarshall();
  }
}

