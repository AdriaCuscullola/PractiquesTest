package view;

import static org.mockito.ArgumentMatchers.any;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import controller.Game;

import java.awt.AWTException;
import java.awt.Robot;

public class AutomatizacionJubula {
	
	private int[] files;
	private int[] columnas;
	private int[] acciones; // 1- obri casella i 3- bandera
	private int nAccion;
	private Robot robotDif;
	
	public void crearAcciones(int versioJoc) {
		try {
			robotDif = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		switch(versioJoc) {
			case 1:
				// ganar partida version 1 mediante uso de banderas
				files = new int[] {0, 0, 0, 4,  4, 4, 3, 2, 1, 0, 5, 5, 5, 2, 1, 3, 2, 1, 3, 0, 0, 2, 3, 4, 4, 4, 5, 5, 6, 6, 6, 7, 7, 7, 7, 7, 6, 6, 6, 6, 7, 7 };
				columnas = new int[] {1, 2, 0, 2, 3, 4, 4, 4, 4, 4, 3, 2, 4, 5, 5, 5, 6, 6, 6, 5, 6, 7, 7, 7, 6, 5, 5, 6, 6, 5, 4, 6, 5, 4, 3, 2, 2, 3, 1, 0, 0, 1 };
				acciones = new int[] {1, 1, 3, 3, 1, 1, 1, 1, 3, 3, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 3, 1, 3, 1, 1, 1, 1, 1, 1, 3, 3, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1};
				break;
			case 2:
				// perder partida version 1 mediante uso de banderas
				files = new int[] {0, 5, 5, 4, 4, 6};
				columnas = new int[] {2, 2, 3, 3, 2, 1};
				acciones = new int[] {1, 1, 1, 1, 3, 1};
				break;
			case 3: 
				// ganar partida version 2 con uso de banderas
				files = new int[] 	 {6,7,7,6,7,6,6,7,4,6,7,7,7,6,6,3,5,5,4,4,3,2,0,1,3,2,1,0,5,1,0,4,2,5,5,3,4,2,0,4,0,0};
				columnas = new int[] {0,0,6,1,1,3,2,5,7,4,4,3,2,6,5,5,6,5,6,5,7,7,6,4,6,6,6,5,3,5,4,2,5,4,2,4,3,4,0,4,2,1};
				acciones = new int[] {1, 1, 3, 3, 1, 1, 1, 1, 3, 3, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1, 3, 1, 3, 1, 1, 1, 1, 1, 1, 3, 3, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1};
				break;
			case 4:
				// perder partida version 2 con uso de banderas
				files = new int[] {7,4,2,2,2,3,3};
				columnas = new int[] {9,7,7,6,5,7,5};
				acciones = new int[] {1,3,3,1,1,3,1};
				break;
			case 5:
				// perder partida version 3 con uso de banderas
				files = new int[] {1, 6, 7, 4, 6, 6};
				columnas = new int[] {2, 1, 3, 3, 1, 1};
				acciones = new int[] {3, 3, 3, 3, 3, 1};
				break;
			default:
				// perder partida version 4 con uso de banderas
				files = new int[] {1, 2, 3, 6, 7, 0, 0, 0};
				columnas = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
				acciones = new int[] {1, 3, 1, 3, 1, 3, 3, 1};
				break;
		}
		nAccion = 0;
	}
	
	public MouseEvent simularClick(Game partidaActual) {	
		JButtonExtend casillaAbrir = new JButtonExtend(files[nAccion], columnas[nAccion], partidaActual);
		return new MouseEvent(casillaAbrir, 0, 0, 0, 100, 100, 1, false, acciones[nAccion]);
	}
	
	public void nextAction() {
		nAccion = nAccion + 1;
	}
	
	public int numeroAcciones() {
		return acciones.length;
	}
	
	public void simulaSeleccionDificultad() {
		robotDif.keyPress(KeyEvent.VK_SPACE);
	}
}
