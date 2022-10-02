package niveles;

import java.util.HashMap;
import java.util.Map;

import entidad.Cosa;
import entidad.Enemigo;
import entidad.Jugador;
import jugadores.JugadorNivel1;
import mapa.Mapa;
import utiles.Posicion;

public class NivelDeCajasArriba {
	private Mapa map;
	private Map<Posicion, Cosa> cosas = new HashMap<Posicion, Cosa>();
	private Map<Posicion, Enemigo> enemies = new HashMap<Posicion, Enemigo>();

	private int [][]disenio = {
            {1,1,1,1,1,1,1,1,1,3,1,1,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,6,4,0,5,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1},
    };
	
	Jugador player;

	JugadorNivel1 jn1;

	public NivelDeCajasArriba(Jugador player) {
		new NivelDeCajasArriba();
		this.player = player;
	}

	public NivelDeCajasArriba() {
		// Inicializo el mapa con el disenio y luego agrego los objetos
		Posicion pos_inicial_jugador = new Posicion(6, 6);
		map = new Mapa(disenio, cosas, enemies, pos_inicial_jugador, 1);

		this.player = new Jugador(map);

		// Corazones recogible empujables
		Cosa corazon = new Cosa(new Posicion(3, 2), map, true, false);

		// Cajas
		Cosa caja_1 = new Cosa(1, new Posicion(3, 4), map, false, true);
		Cosa caja_2 = new Cosa(1, new Posicion(4, 4), map, false, true);
		Cosa caja_3 = new Cosa(1, new Posicion(5, 4), map, false, true);
		Cosa caja_4 = new Cosa(1, new Posicion(6, 4), map, false, true);
		Cosa caja_5 = new Cosa(1, new Posicion(7, 4), map, false, true);
		Cosa caja_6 = new Cosa(1, new Posicion(8, 4), map, false, true);
		Cosa caja_7 = new Cosa(1, new Posicion(8, 2), map, false, true);
		Cosa caja_8 = new Cosa(1, new Posicion(9, 4), map, false, true);

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
		System.out.println("Posicion inicial del jugador : " + player.getPos());
		jn1 = new JugadorNivel1(player);
		System.out.println("Posicion final del jugador : " + player.getPos());
	}

}