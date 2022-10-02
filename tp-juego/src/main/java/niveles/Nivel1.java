package niveles;

import java.util.HashMap;
import java.util.Map;

import entidad.Cosa;
import entidad.Enemigo;
import entidad.Jugador;
import jugadores.JugadorNivel1;
import mapa.Mapa;
import utiles.Posicion;

public class Nivel1 {
	private Mapa map;
	private Map<Posicion, Cosa> cosas = new HashMap<Posicion, Cosa>();
	private Map<Posicion, Enemigo> enemies = new HashMap<Posicion, Enemigo>();

	private int[][] disenio = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 4, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 1, 0, 0, 0, 0, 0, 4, 4, 4, 4, 4, 0, 3 },
			{ 1, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 1 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6, 1 },
			{ 1, 5, 0, 5, 0, 4, 0, 0, 0, 0, 0, 0, 1 }, { 1, 5, 0, 5, 0, 4, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 5, 0, 5, 0, 4, 0, 0, 0, 0, 0, 0, 1 }, { 1, 5, 0, 5, 0, 4, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, };

	Jugador player;

	JugadorNivel1 jn1 = null;

	public Nivel1(Jugador player) {
		new Nivel1();
		this.player = player;
	}

	public Nivel1() {
		// Inicializo el mapa con el disenio y luego agrego los objetos
		Posicion pos_inicial_jugador = new Posicion(2, 2);
		map = new Mapa(disenio, cosas, enemies, pos_inicial_jugador, 2);

		this.player = new Jugador(map);

		// Corazones
		Cosa corazon_1 = new Cosa(new Posicion(2, 11), map, true, false);
		Cosa corazon_2 = new Cosa(new Posicion(6, 11), map, true, false);

		cosas.put(corazon_1.getPos(), corazon_1);
		cosas.put(corazon_2.getPos(), corazon_2);

		// Cajas
		Cosa caja_1 = new Cosa(1, new Posicion(2, 7), map, false, true);

		cosas.put(caja_1.getPos(), caja_1);

		// Enemigos
		Enemigo enemigo_1 = new Enemigo(0, new Posicion(5, 2), map);

		enemies.put(enemigo_1.getPos(), enemigo_1);

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

	public void mostrarCosas() {
		map.mostrarCosas();
	}

	public void add(JugadorNivel1 jn1) {
		this.jn1 = jn1;
	}

	public void run() {
		jn1 = new JugadorNivel1(player);
		if (this.player.getPos().equals(map.getPosPuerta()))
			System.out.println("Ganaste! Esa era una solucion valida!");

		else
			System.out.println("Perdiste :c, Esa no era una solucion valida..");
	}

}
