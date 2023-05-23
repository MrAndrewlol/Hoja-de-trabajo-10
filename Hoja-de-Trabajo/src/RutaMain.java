import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class RutaMain {
  public static void main(String[] args) {
	  System.out.println("Ingrese la ruta del archivo ej C:\\ejemplos\\example1.txt");
	  Scanner in = new Scanner(System.in);
	  String fpath = in.nextLine(); 
    int infinitio = Integer.MAX_VALUE;
    //BuenosAires SaoPaulo 10 15 20 50
    //int[][] myNumbers = { {1, 2, 3, 4}, {5, 6, 7} };
    String[] listastring = new String[6];
	  
    try {
      File myObj = new File(fpath);
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        System.out.println(data);
        listastring = data.split(" ");
        System.out.println(listastring);
        int[][] tiempos = { {0, infinitio, infinitio, infinitio}, {5, infinitio, infinitio} };
        int[][] lluvia = { {1, 2, 3, 4}, {5, 6, 7} };
        int[][] nieve = { {1, 2, 3, 4}, {5, 6, 7} };
        int[][] tormenta = { {1, 2, 3, 4}, {5, 6, 7} };

      }
      myReader.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }
}

