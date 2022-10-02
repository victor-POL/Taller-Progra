package niveles;

import java.util.HashMap;
import java.util.Map;

import entidad.Cosa;
import entidad.Enemigo;
import entidad.Jugador;
import entidad.JugadorNivelFacil;
import mapa.Mapa;
import utiles.Posicion;

public class NivelDeCajasIzquierda {
	private Mapa map;
	private Map<Posicion, Cosa> cosas = new HashMap<Posicion, Cosa>();
	private Map<Posicion, Enemigo> enemies = new HashMap<Posicion, Enemigo>();

	private int [][]disenio = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,1},
            {3,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,5,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,4,0,0,0,0,0,0,0,0,0,1},
            {1,0,6,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,0,0,0,0,0,0,0,0,0,0,0,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1},
    };
	
	Jugador player;

	JugadorNivelFacil jn1;

	public NivelDeCajasIzquierda(Jugador player) {
		new NivelFacil();
		this.player = player;
	}

	public NivelDeCajasIzquierda() {
		// Inicializo el mapa con el disenio y luego agrego los objetos
		Posicion pos_inicial_jugador = new Posicion(6, 6);
		map = new Mapa(disenio, cosas, enemies, pos_inicial_jugador, 1);

		this.player = new Jugador(map);

		// Corazones recogible empujables
		Cosa corazon = new Cosa(new Posicion(2, 9), map, true, false);

		// Cajas
		Cosa caja_1 = new Cosa(1, new Posicion(4, 9), map, false, true);
		Cosa caja_2 = new Cosa(1, new Posicion(4, 8), map, false, true);
		Cosa caja_3 = new Cosa(1, new Posicion(4, 7), map, false, true);
		Cosa caja_4 = new Cosa(1, new Posicion(4, 6), map, false, true);
		Cosa caja_5 = new Cosa(1, new Posicion(4, 5), map, false, true);
		Cosa caja_6 = new Cosa(1, new Posicion(4, 4), map, false, true);
		Cosa caja_7 = new Cosa(1, new Posicion(2, 4), map, false, true);
		Cosa caja_8 = new Cosa(1, new Posicion(4, 3), map, false, true);

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

	public void add(JugadorNivelFacil jn1) {
		this.jn1 = jn1;
	}

	public void run() {
		System.out.println("Posicion inicial del jugador : " + player.getPos());
		jn1 = new JugadorNivelFacil(player);
		System.out.println("Posicion final del jugador : " + player.getPos());
	}

}
