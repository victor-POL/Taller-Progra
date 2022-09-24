package entidad;

import mapa.Mapa;

public class Jugador extends Entidad {
	
	Mapa map;
	public Jugador(Mapa map) {
		super(0.5);
		this.map = map;
		this.x = map.xInicialJugador;
		this.y = map.yInicialJugador;
	}
	public void moverDerecha() {
		if(map.puedoPasar((int)(x+1),(int)y) == false)
			x+= PASO;
	}
	public void moverIzquierda() {
		if(map.puedoPasar((int)(x-1),(int)y) == false)
			x-= PASO;
	}
	public void moverAbajo() {
		if(map.puedoPasar((int)(x),(int)(y+1)) == false)
			y+= PASO;
	}
	
	public double[] getPos() {
		double[] d = new double[2];
		d[0] = this.x;
		d[1] = this.y;
		return d;
	}
}
