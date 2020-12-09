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
		Game g = new Game();
		GeneradorRandom random = new MockTestCase();
		g.setRandom(random);
		g.start();
		AutomatizacionJubula autJubula = new AutomatizacionJubula();
		// autJubula.simulaSeleccionDificultad();
		autJubula.crearAcciones(1);
		for(int i = 0; i < autJubula.numeroAcciones(); i++) {
			g.mouseClicked(autJubula.simularClick(g));
			autJubula.nextAction();
		}
	}
	
	@Test
	public void partidaSegundaVersion() {
		Game g = new Game();
		GeneradorRandom random = new MockTestCase();
		g.setRandom(random);
		g.start();
		AutomatizacionJubula autJubula = new AutomatizacionJubula();
		// autJubula.simulaSeleccionDificultad();
		autJubula.crearAcciones(2);
		for(int i = 0; i < autJubula.numeroAcciones(); i++) {
			g.mouseClicked(autJubula.simularClick(g));
			autJubula.nextAction();
		}
	}
	
	@Test
	public void partidaTerceraVersion() {
		Game g = new Game();
		GeneradorRandom random = new MockTestCase();
		g.setRandom(random);
		g.start();
		AutomatizacionJubula autJubula = new AutomatizacionJubula();
		// autJubula.simulaSeleccionDificultad();
		autJubula.crearAcciones(3);
		for(int i = 0; i < autJubula.numeroAcciones(); i++) {
			g.mouseClicked(autJubula.simularClick(g));
			autJubula.nextAction();
		}
	}
	
	@Test
	public void partidaQuartaVersion() {
		Game g = new Game();
		GeneradorRandom random = new MockTestCase();
		g.setRandom(random);
		g.start();
		AutomatizacionJubula autJubula = new AutomatizacionJubula();
		// autJubula.simulaSeleccionDificultad();
		autJubula.crearAcciones(4);
		for(int i = 0; i < autJubula.numeroAcciones(); i++) {
			g.mouseClicked(autJubula.simularClick(g));
			autJubula.nextAction();
		}
	}
	
	@Test
	public void partidaQuintaVersion() {
		Game g = new Game();
		GeneradorRandom random = new MockTestCase();
		g.setRandom(random);
		g.start();
		AutomatizacionJubula autJubula = new AutomatizacionJubula();
		// autJubula.simulaSeleccionDificultad();
		autJubula.crearAcciones(5);
		for(int i = 0; i < autJubula.numeroAcciones(); i++) {
			g.mouseClicked(autJubula.simularClick(g));
			autJubula.nextAction();
		}
	}
}
