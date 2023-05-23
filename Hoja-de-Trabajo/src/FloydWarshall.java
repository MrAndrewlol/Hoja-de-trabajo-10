/**
 * 
 */

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

	/**
	 * @return the distancias
	 */
	public int[][] getDistancias() {
		return distancias;
	}

	/**
	 * @param distancias the distancias to set
	 */
	public void setDistancias(int[][] distancias) {
		this.distancias = distancias;
	}

	/**
	 * @return the recorridos
	 */
	public String[][] getRecorridos() {
		return recorridos;
	}

	/**
	 * @param recorridos the recorridos to set
	 */
	public void setRecorridos(String[][] recorridos) {
		this.recorridos = recorridos;
	}

	/**
	 * @return the sIZE
	 */
	public int getSIZE() {
		return SIZE;
	}

	/**
	 * @param sIZE the sIZE to set
	 */
	public void setSIZE(int sIZE) {
		SIZE = sIZE;
	}
	
	public void CalcularRutas() {
		for (int i = 0; i < SIZE; i++) { //Que fila y que columna trabajo
			for (int j = 0; j < SIZE; j++) {
				for (int k = 0; k < SIZE; k++) {
					
					if ((i != j) && (i != k)) {
						int suma = distancias[j][i] + distancias[i][k]; 
						if (suma < distancias[j][k]) {
							distancias[j][k] = suma;
							recorridos[j][k] = vertices[i];
						}
					}
					
				}
			}
		}
	}
}