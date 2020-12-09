package controller;

public class MockTestCase implements GeneradorRandom {
	private int[] filas = {0, 0, 1, 2, 3, 4, 4, 6, 6, 7};
	private int[] columnas = {0, 4, 4, 7, 5, 2, 7, 1, 4, 6};
	/*
	private int[] filas = {0, 0, 0, 1, 1, 2, 2, 2, 6, 7};
	private int[] columnas = {0, 1, 2, 0, 2, 0, 1, 2, 4, 6};
	*/
	
	public int nextFila(int it) {
		return filas[it];
	}
	public int nextColumna(int it) {
		return columnas[it];
	}
}
