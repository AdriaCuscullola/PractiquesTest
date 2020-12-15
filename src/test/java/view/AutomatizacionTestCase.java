package view;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import static org.mockito.Mockito.*;

import controller.Game;
import controller.GeneradorRandom;
import controller.MockTestCase;

public class AutomatizacionTestCase {
	
	@Test
	public void partidaPrimeraVersion() {
		// test case ganar partida versión 1
		Game g = new Game();
		GeneradorRandom random = new MockTestCase();
		g.setRandom(random);
		g.setNoReset();
		g.start();
		AutomatizacionJubula autJubula = new AutomatizacionJubula();
		// autJubula.simulaSeleccionDificultad();
		autJubula.crearAcciones(1);
		int numAcciones = autJubula.numeroAcciones();
		for(int i = 0; i < numAcciones; i++) {
			g.mouseClicked(autJubula.simularClick(g));
			autJubula.nextAction();
			if(i < (numAcciones-1)) {
				assertFalse(g.isFinished(autJubula.getFila(i), autJubula.getColumna(i)));
			}
			else {
				assertTrue(g.isFinished(autJubula.getFila(i), autJubula.getColumna(i)));
			}
		}
	}
	
	@Test
	public void partidaSegundaVersion() {
		// test case perder partida versión 1
		Game g = new Game();
		GeneradorRandom random = new MockTestCase();
		g.setRandom(random);
		g.setNoReset();
		g.start();
		AutomatizacionJubula autJubula = new AutomatizacionJubula();
		// autJubula.simulaSeleccionDificultad();
		autJubula.crearAcciones(2);
		int numAcciones = autJubula.numeroAcciones();
		for(int i = 0; i < numAcciones; i++) {
			g.mouseClicked(autJubula.simularClick(g));
			autJubula.nextAction();
			if(i < (numAcciones-1)) {
				assertFalse(g.isFinished(autJubula.getFila(i), autJubula.getColumna(i)));
			}
			else {
				assertTrue(g.isFinished(autJubula.getFila(i), autJubula.getColumna(i)));
			}
		}
	}
	
	@Test
	public void partidaTerceraVersion() {
		// test case ganar partida versión 2
		Game g = new Game();
		GeneradorRandom random = new MockTestCase();
		g.setRandom(random);
		g.setNoReset();
		g.start();
		AutomatizacionJubula autJubula = new AutomatizacionJubula();
		// autJubula.simulaSeleccionDificultad();
		autJubula.crearAcciones(3);
		int numAcciones = autJubula.numeroAcciones();
		for(int i = 0; i < numAcciones; i++) {
			g.mouseClicked(autJubula.simularClick(g));
			autJubula.nextAction();
			if(i < (numAcciones-1)) {
				assertFalse(g.isFinished(autJubula.getFila(i), autJubula.getColumna(i)));
			}
			else {
					assertTrue(g.isFinished(autJubula.getFila(i), autJubula.getColumna(i)));
			}
		}
	}
	
	@Test
	public void partidaCuartaVersion() {
		// test case perder partida versión 2
		Game g = new Game();
		GeneradorRandom random = new MockTestCase();
		g.setRandom(random);
		g.setNoReset();
		g.start();
		AutomatizacionJubula autJubula = new AutomatizacionJubula();
		// autJubula.simulaSeleccionDificultad();
		autJubula.crearAcciones(4);
		int numAcciones = autJubula.numeroAcciones();
		for(int i = 0; i < numAcciones; i++) {
			g.mouseClicked(autJubula.simularClick(g));
			autJubula.nextAction();
			if(i < (numAcciones-1)) {
				assertFalse(g.isFinished(autJubula.getFila(i), autJubula.getColumna(i)));
			}
			else {
				assertTrue(g.isFinished(autJubula.getFila(i), autJubula.getColumna(i)));
			}
		}
	}
	
	@Test
	public void partidaQuintaVersion() {
		// test case perder partida versión 3
		Game g = new Game();
		GeneradorRandom random = new MockTestCase();
		g.setRandom(random);
		g.setNoReset();
		g.start();
		AutomatizacionJubula autJubula = new AutomatizacionJubula();
		// autJubula.simulaSeleccionDificultad();
		autJubula.crearAcciones(5);
		int numAcciones = autJubula.numeroAcciones();
		for(int i = 0; i < numAcciones; i++) {
			g.mouseClicked(autJubula.simularClick(g));
			autJubula.nextAction();
			if(i < (numAcciones-1)) {
				assertFalse(g.isFinished(autJubula.getFila(i), autJubula.getColumna(i)));
			}
			else {
				assertTrue(g.isFinished(autJubula.getFila(i), autJubula.getColumna(i)));
			}
		}
	}
	
	@Test
	public void partidaSextaVersion() {
		// test case perder partida versión 4
		Game g = new Game();
		GeneradorRandom random = new MockTestCase();
		g.setRandom(random);
		g.setNoReset();
		g.start();
		AutomatizacionJubula autJubula = new AutomatizacionJubula();
		// autJubula.simulaSeleccionDificultad();
		autJubula.crearAcciones(6);
		int numAcciones = autJubula.numeroAcciones();
		for(int i = 0; i < numAcciones; i++) {
			g.mouseClicked(autJubula.simularClick(g));
			autJubula.nextAction();
			if(i < (numAcciones-1)) {
				assertFalse(g.isFinished(autJubula.getFila(i), autJubula.getColumna(i)));
			}
			else {
				assertTrue(g.isFinished(autJubula.getFila(i), autJubula.getColumna(i)));
			}
		}
	}
}
