package entidad;

import mapa.Mapa;
import utiles.Posicion;

public class Entidad {
	protected final double PASO; 
	protected Posicion pos;
	protected Mapa map;


	public Entidad(double paso, Posicion pos, Mapa map) {
		PASO = paso;
		this.pos = pos;
		this.map = map;
	}

	public void moverDerecha() {
		if(map.puedoPasar((int)(pos.getX()+1),(int)pos.getY()))
			pos.setX(pos.getX()+PASO);
	}
	public void moverIzquierda() {
		if(map.puedoPasar((int)(pos.getX()-1),(int)pos.getY()))
			pos.setX(pos.getX()-PASO);
	}
	public void moverAbajo() {
		if(map.puedoPasar((int)(pos.getX()),(int)(pos.getY()+1)))
			pos.setY(pos.getY()+PASO);
	}
	public void moverArriba() {
		if(map.puedoPasar((int)(pos.getX()),(int)(pos.getY()-1)))
			pos.setY(pos.getY()-PASO);		
	}
	
	public Posicion getPos() {
		return pos;
	}
}
