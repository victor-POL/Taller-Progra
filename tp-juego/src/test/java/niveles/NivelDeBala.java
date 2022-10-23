package niveles;

import java.util.HashMap;
import java.util.Map;

import entidad.Cosa;
import entidad.Enemigo;
import entidad.Jugador;
import mapa.Mapa;
import utiles.Posicion;

public class NivelDeBala {
	private Mapa map;
	private Map<Posicion, Cosa> cosas = new HashMap<Posicion, Cosa>();
	private Map<Posicion, Enemigo> enemies = new HashMap<Posicion, Enemigo>();

	private int[][] disenio = { { 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 1 }, { 1, 0, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }

	};

	Jugador player;

	public NivelDeBala(Jugador player) {
		new NivelDeBala();
		this.player = player;
	}

	public NivelDeBala() {
		// Inicializo el mapa con el disenio y luego agrego los objetos
		Posicion pos_inicial_jugador = new Posicion(2, 2);
		map = new Mapa(disenio, cosas, enemies, pos_inicial_jugador, 2);

		this.player = new Jugador(map);

		// Corazones
		Cosa corazon_1 = new Cosa(0, new Posicion(3, 2), map, true, false, "corazon");
		Cosa corazon_2 = new Cosa(0, new Posicion(4, 5), map, true, false, "corazon");

		// Caja
		Cosa caja = new Cosa(1, new Posicion(9, 5), map, false, true, "caja");

		cosas.put(corazon_1.getPos(), corazon_1);
		cosas.put(corazon_2.getPos(), corazon_2);
		cosas.put(caja.getPos(), caja);

		// Enemigos
		Enemigo enemigo_1 = new Enemigo(player.getPaso(), new Posicion(3, 1), map);
		Enemigo enemigo_2 = new Enemigo(player.getPaso(), new Posicion(7, 1), map);
		Enemigo enemigo_3 = new Enemigo(player.getPaso(), new Posicion(10, 2), map);
		Enemigo enemigo_4 = new Enemigo(player.getPaso(), new Posicion(6, 3), map);
		Enemigo enemigo_5 = new Enemigo(player.getPaso(), new Posicion(7, 3), map);
		Enemigo enemigo_6 = new Enemigo(player.getPaso(), new Posicion(2, 4), map);
		Enemigo enemigo_7 = new Enemigo(player.getPaso(), new Posicion(10, 5), map);
		Enemigo enemigo_8 = new Enemigo(player.getPaso(), new Posicion(4, 6), map);
		Enemigo enemigo_9 = new Enemigo(player.getPaso(), new Posicion(7, 6), map);
		Enemigo enemigo_10 = new Enemigo(player.getPaso(), new Posicion(3, 10), map);

		enemies.put(enemigo_1.getPos(), enemigo_1);
		enemies.put(enemigo_2.getPos(), enemigo_2);
		enemies.put(enemigo_3.getPos(), enemigo_3);
		enemies.put(enemigo_4.getPos(), enemigo_4);
		enemies.put(enemigo_5.getPos(), enemigo_5);
		enemies.put(enemigo_6.getPos(), enemigo_6);
		enemies.put(enemigo_7.getPos(), enemigo_7);
		enemies.put(enemigo_8.getPos(), enemigo_8);
		enemies.put(enemigo_9.getPos(), enemigo_9);
		enemies.put(enemigo_10.getPos(), enemigo_10);
	}

	public Jugador getPlayer() {
		return player;
	}

	public Mapa getMapa() {
		return this.map;
	}

}
