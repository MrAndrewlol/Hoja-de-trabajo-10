/**
 * 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author JAPM
 *
 */
public class FloydWarshall {

	private HashMap<String, Integer> nodos;
    private Integer[][] matriz;
    private Integer[][] matrizFloyd;
    private String[][] path;
    private String[][] caminosFloyd;
    private HashMap<Integer, String> nodosColum;
	
	public FloydWarshall(HashMap<String, Integer> nodos, HashMap<Integer,String> nodosColum, int tamaño) {
		this.nodos = nodos;
		this.nodosColum = nodosColum;
		matriz = new Integer[tamaño][tamaño];
		path = new String[tamaño][tamaño];
		for (int i = 0; i < tamaño; i++) {
			for (int j = 0; j < tamaño; j++) {
				if (i != j)
					path[i][j] = nodosColum.get(j+1);
			}
		}
		for (int i = 0; i < tamaño; i++){
			for (int j = 0; j< tamaño; j++) {
				if ( i == j)
					matriz[i][j] = 0;
			}
		}
		
		
	}

	public void setMatriz(int f, int c, int tamaño){
		matriz[f][c] = tamaño;
	}

	public void setInterrupcion(int x, int y) {
		matriz[x][y] = null;
		path[x][y] = null;
	}

	public void setLluvia(int x, int y, int distance){
		matriz[x][y] = distance;
	}

	public void setNieve(int x, int y, int distance){
		matriz[x][y] = distance;
	}

	public void setTormenta(int x, int y, int distance){
		matriz[x][y] = distance;
	}

	public void setPath(int x, int y) {
		path[x][y] = nodosColum.get(y);
	}

	public void Warshall() {
		int size = nodos.size();
		matrizFloyd = new Integer[size][size];
		caminosFloyd = new String[size][size];
		for (int i = 0; i < size; i++){
			for (int j = 0; j < size; j++) {
				matrizFloyd[i][j] = matriz[i][j];
				caminosFloyd[i][j] = path[i][j];
			}
		}
		for (int k = 0; k < nodos.size(); k++){
			for (int j = 0; j < nodos.size(); j++){
				for (int i = 0; i < nodos.size(); i++) {
					if (matrizFloyd[i][j] != null && matrizFloyd[i][k] != null && matrizFloyd[k][j] != null){
						if (matrizFloyd[i][j] > (matrizFloyd[i][k] + matrizFloyd[k][j])){
							matrizFloyd[i][j] = matrizFloyd[i][k] + matrizFloyd[k][j];
                            caminosFloyd[i][j] = nodosColum.get(k + 1);
						}
					}
					if (matrizFloyd[i][j] == null && !(matrizFloyd[i][k] == null || matrizFloyd[k][j] == null)) {
                        matrizFloyd[i][j] = matrizFloyd[i][k] + matrizFloyd[k][j];
                        caminosFloyd[i][j] = nodosColum.get(k + 1);
					} 
				}
			}
		}
	}

	public String ruta(int x, int y) {
		String peso =null, mensaje = "\nNo existe el recorrido";
		if (caminosFloyd[x][y] != null) {
			peso = matrizFloyd[x][y].toString();
			mensaje = "\nEl recorrido va de " + nodosColum.get(x + 1);
			while (x != y) {
				if (caminosFloyd[x][y] != null) {
					mensaje += " a " + caminosFloyd[x][y];
					x = nodos.get(caminosFloyd[x][y]) - 1;
				}
			}
			mensaje += " con una trayectoria de " + peso;
		}
		return mensaje;
	}

	public String centro() {
		String mensaje = "\nEl centro del grafo es: ";
		ArrayList<Integer> caminos = new ArrayList<Integer>();
		int sum = 0;
		for (int i = 0; i < nodos.size(); i++) {
			sum = 0;
			for (int j = 0; j < nodos.size(); j++) {
				if (matrizFloyd[j][i] != null)
					sum += matrizFloyd[j][i];
				else	sum += Integer.MAX_VALUE/2;
			}
			caminos.add(sum);
		}
		ArrayList<Integer> orden = caminos;
		Collections.sort(orden);
		mensaje += nodosColum.get(caminos.indexOf(orden.get(0)) + 1);
		return mensaje;
	}
}