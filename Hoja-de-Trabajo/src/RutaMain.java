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
    boolean ejecucion = true;

    FloydWarshall grafo;

    HashMap<String, Integer> nodos = new HashMap<String, Integer>();
    HashMap<Integer, String> nodosColum = new HashMap<Integer, String>();
    ArrayList<String> filas = new ArrayList<String>();
    int tamaño = 0;
    String[] lista;
    String ciudad1, ciudad2;

    try {
      File myObj = new File(fpath);
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        filas.add(data);
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
      grafo.Warshall();
  
      while (ejecucion) {
        System.out.println("\n1: Ruta de ciudades\n2: Centro del grafo\n3: Modificar rutas\n4: Salir del programa\n");
        try {
          System.out.println("Opción: ");
          int opcion = in.nextInt();
          in.nextLine();
          switch (opcion) {
            case 1:
              System.out.println("\nIngrese la ciudad de inicio: ");
              String fila = in.nextLine();
              System.out.println("\nIngrese la ciudad de destino: ");
              String columna = in.nextLine();
              System.out.println(grafo.ruta(nodos.get(fila) -1, nodos.get(columna) -1));
              break;
            
            case 2:
              System.out.println(grafo.centro());
              break;
  
            case 3:
              System.out.println("\n1: Interrupción entre ciudades\n2: Nueva conexión\n3: Cambio de clima\n");
              System.out.println("Opción: ");
              try {
                opcion = in.nextInt();
                in.nextLine();
                switch (opcion) {
                  case 1:
                  System.out.println("\nIngrese la ciudad de inicio: ");
                  fila = in.nextLine();
                  System.out.println("\nIngrese la ciudad de destino: ");
                  columna = in.nextLine();
                  grafo.setInterrupcion(nodos.get(fila) - 1, nodos.get(columna) - 1);
                  grafo.Warshall();
                    break;
                  
                    case 2:
                      System.out.println("\nIngrese ciudad de inicio: ");
                      fila = in.nextLine();
                      System.out.println("\nIngrese la ciudad de destino: ");
                      columna = in.nextLine();
                      System.out.println("\nIngrese la nueva distancia entre las ciudades: ");
                      int newDistance = in.nextInt();
                      in.nextLine();
                      grafo.setMatriz(nodos.get(fila) - 1, nodos.get(fila), newDistance);
                      grafo.setPath(nodos.get(fila) - 1, nodos.get(columna));
                      grafo.Warshall();
                      break;
                  
                    case 3:
                    System.out.println("\nIngrese ciudad de inicio: ");
                    ciudad1 = in.nextLine();
                    System.out.println("\nIngrese la ciudad de destino: ");
                    ciudad2 = in.nextLine();
                    System.out.println("\nEscriba uno de los siguientes climas: Lluvia/Nieve/Tormenta");
                    String clima = in.nextLine().toLowerCase();

                    switch (clima) {
                      case "lluvia":
                        for (String linea : filas) {
                            lista = linea.split(" ");
                            if (ciudad1.equalsIgnoreCase(lista[0]) && ciudad2.equalsIgnoreCase(lista[1])){
                              tamaño = Integer.valueOf(lista[3]); 
                              grafo.setLluvia(nodos.get(ciudad1) - 1, nodos.get(ciudad2) - 1, tamaño);
                              grafo.Warshall();
                            }
                        }
                        break;

                      case "nieve":
                        for (String linea : filas) {
                          lista = linea.split(" ");
                          if (ciudad1 == lista[0] && ciudad2 == lista[1]){
                            tamaño = Integer.valueOf(lista[4]); 
                          }
                        }
                        grafo.setLluvia(nodos.get(ciudad1) - 1, nodos.get(ciudad2), tamaño);
                        grafo.Warshall();
                        break;

                      case "tormenta":
                        for (String linea : filas) {
                          lista = linea.split(" ");
                          if (ciudad1 == lista[0] && ciudad2 == lista[1]){
                            tamaño = Integer.valueOf(lista[5]); 
                          }
                        }
                        grafo.setLluvia(nodos.get(ciudad1) - 1, nodos.get(ciudad2), tamaño);
                        grafo.Warshall();
                        break;
                    
                      default:
                        System.out.println("\nEste clima no esta registrado");
                        break;
                    }

                      break;
  
                  default:
                    break;
                }
              } catch (Exception e) {
                // TODO: handle exception
              }
  
              break;
            
            case 4:
            ejecucion = false;
            System.out.println("Saliendo del programa...");
              break;
  
          }
        } catch (Exception e) {
          // TODO: handle exception
        }
        
      }
    
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }

  }
}

