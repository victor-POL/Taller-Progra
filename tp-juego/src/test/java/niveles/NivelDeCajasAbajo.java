package niveles;

import java.util.HashMap;
import java.util.Map;

import entidad.Cosa;
import entidad.Enemigo;
import entidad.Jugador;
import mapa.Mapa;
import utiles.Posicion;

public class NivelDeCajasAbajo {
	private Mapa map;
	private Map<Posicion, Cosa> cosas = new HashMap<Posicion, Cosa>();
	private Map<Posicion, Enemigo> enemies = new HashMap<Posicion, Enemigo>();

	private int[][] disenio = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 6, 4, 0, 5, 0, 0, 0, 0, 1 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 1, 1, 1 }, };

	Jugador player;

	public NivelDeCajasAbajo(Jugador player) {
		new NivelDeCajasAbajo();
		this.player = player;
	}

	public NivelDeCajasAbajo() {
		// Inicializo el mapa con el disenio y luego agrego los objetos
		Posicion pos_inicial_jugador = new Posicion(6, 6);
		map = new Mapa(disenio, cosas, enemies, pos_inicial_jugador, 1);

		this.player = new Jugador(map);

		// Corazones recogible empujables
		Cosa corazon = new Cosa(new Posicion(3, 10), map, true, false);

		// Cajas
		Cosa caja_1 = new Cosa(1, new Posicion(3, 8), map, false, true);
		Cosa caja_2 = new Cosa(1, new Posicion(4, 8), map, false, true);
		Cosa caja_3 = new Cosa(1, new Posicion(5, 8), map, false, true);
		Cosa caja_4 = new Cosa(1, new Posicion(6, 8), map, false, true);
		Cosa caja_5 = new Cosa(1, new Posicion(7, 8), map, false, true);
		Cosa caja_6 = new Cosa(1, new Posicion(8, 8), map, false, true);
		Cosa caja_7 = new Cosa(1, new Posicion(8, 10), map, false, true);
		Cosa caja_8 = new Cosa(1, new Posicion(9, 8), map, false, true);

		cosas.put(corazon.getPos(), corazon);
		cosas.put(caja_1.getPos(), caja_1);
		cosas.put(caja_2.getPos(), caja_2);
		cosas.put(caja_3.getPos(), caja_3);
		cosas.put(caja_4.getPos(), caja_4);
		cosas.put(caja_5.getPos(), caja_5);
		cosas.put(caja_6.getPos(), caja_6);
		cosas.put(caja_7.getPos(), caja_7);
		cosas.put(caja_8.getPos(), caja_8);
	}

	public Jugador getPlayer() {
		return player;
	}

	public Mapa getMapa() {
		return this.map;
	}

}
