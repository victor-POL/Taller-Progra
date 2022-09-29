package entidad;

import mapa.Mapa;

public class Jugador extends Entidad {
	private int vidas;
	private int puntaje;

	public Jugador(Mapa map) {
		super(0.5, map.getPosInicialJugador(), map);
	}
	
	public double getPaso() {
		return this.PASO;
	}
	

	

}
