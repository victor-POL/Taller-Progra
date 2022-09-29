package mapa;

import java.util.HashMap;
import java.util.Map;

import entidad.Cosa;
import entidad.Enemigo;
import piso.Piso;
import piso.PisoHandler;
import utiles.Posicion;

public class Mapa {
	private Piso[][] matPiso = new Piso[16][16];
	
	private Map<Posicion, Cosa> cosas = new HashMap<Posicion, Cosa>();
	private Map<Posicion, Enemigo> enemies = new HashMap<Posicion, Enemigo>();
	
	
	private Posicion posInicialJugador;
	
	
	public Mapa(int [][] disenio, Map<Posicion,Cosa> cosas, Map<Posicion, Enemigo> enemies, Posicion posInicialJugador) {
		for (int i = 0; i < disenio.length; i++) {
			for(int j = 0; j < disenio[0].length; j++) {
				matPiso[i][j] = new PisoHandler().getPisoByPosition(disenio[i][j]);
			}
		}
		this.cosas = cosas;
		this.enemies = enemies;
		this.posInicialJugador = posInicialJugador;
	}
	
	// x = 10; y = 15;
	//moverDerecha();
	//matPiso[11][15].collision == false;
	
	
	public boolean puedoPasar(int x, int y) { // x=Columnas, y=filas
		if(x < matPiso.length && x >= 0 && y >= 0 && y < matPiso[0].length) {
			//System.out.println(new Posicion(x,y).compareTo(new Posicion(2,2)));
			return matPiso[y][x].isCollisionable() == false && cosas.get(new Posicion(x,y)) == null && enemies.get(new Posicion(x,y)) == null;
		}
		return false;
	}
	
	public Posicion getPosInicialJugador() {
		return posInicialJugador;
	}

	public void displayMap(){ //leave space between each number
		for (int i = 0; i < matPiso.length; i++) {
			for(int j = 0; j < matPiso[0].length; j++) {
				System.out.printf("%8s",matPiso[i][j].getSprite());
			}
			System.out.println("\n");
		}
		
	}
	

}
