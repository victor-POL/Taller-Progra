package niveles;

import java.util.HashMap;
import java.util.Map;


import entidad.Cosa;
import entidad.Enemigo;
import entidad.Jugador;
import mapa.Mapa;
import utiles.Posicion;

public class Nivel1 {
	private Mapa map;
	private Map<Posicion, Cosa> cosas = new HashMap<Posicion, Cosa>();
	private Map<Posicion, Enemigo> enemies = new HashMap<Posicion, Enemigo>();

	private int [][]disenio = {
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
			{1,0,0,0,0,1,1,1,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,2,2,2,2,2,2,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,2,1},
			{1,0,0,0,0,1,1,1,1,0,0,0,0,0,2,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,2,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,2,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,2,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,2,1},
			{1,0,0,0,0,0,0,2,2,2,2,2,2,2,2,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
				   
	};

	Jugador player;

	
	public Nivel1(Jugador player) {
		new Nivel1();
		this.player = player;
	}
	public Nivel1() {
		//crear Lista de objetos
		map = new Mapa(disenio,cosas,enemies,new Posicion(1,1));
		this.player = new Jugador(map);
		cosas.put(new Posicion(2, 2), new Cosa(player.getPaso(), new Posicion(2,2), map));
		cosas.put(new Posicion(2, 3), new Cosa(player.getPaso(), new Posicion(2,3), map));
		enemies.put(new Posicion(3,4), new Enemigo(player.getPaso(), new Posicion(3,4), map));
	}
	public void setPlayer(Jugador player) {
		this.player = player;
	}
	public Jugador getPlayer() {
		return player;
	}
	public Mapa getMapa() {
		return this.map;
	}

	public void displayLevel() {
		map.displayMap();
	}
	
}
