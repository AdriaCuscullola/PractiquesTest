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
			/*case 3: 
				// ganar partida version 2 sin uso de banderas
				break;
			default:
				// ganar partida version 2 sin uso de banderas
				break;
			*/
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
