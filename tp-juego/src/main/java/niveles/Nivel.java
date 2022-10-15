package niveles;

import entidad.Jugador;
import jugadores.Instrucciones;
import mapa.Mapa;
import utiles.Archivo;

public class Nivel {
	private Mapa map;
	Jugador player;
	Instrucciones instrucciones = null;

	// Constructores

	public Nivel(String nombreNivel) {
		Archivo disenioNivel = new Archivo(nombreNivel);
		map = disenioNivel.cargarMapa();
		this.player = new Jugador(map);
	}

	// Metodos
	
	public void add(Instrucciones jugador) {
		instrucciones = jugador;
	}

	public void run() {
		instrucciones.ejecutarInstrucciones(player);
		if (this.player.getPos().equals(map.getPosPuerta()))
			System.out.println("Ganaste! Esa era una solucion valida!");

		else
			System.out.println("Perdiste :c, Esa no era una solucion valida..");
	}

	// Getters

	public Jugador getPlayer() {
		return player;
	}

	public Mapa getMapa() {
		return this.map;
	}
}
