package entidad;

import mapa.Mapa;
import utiles.Constantes;
import utiles.Posicion;

public class Entidad implements Constantes {
	protected final double PASO; 
	protected Posicion pos;
	protected Mapa map;


	public Entidad() {
		PASO = 1;
	}
	
	public Entidad(double paso, Posicion pos, Mapa map) {
		PASO = paso;
		this.pos = pos;
		this.map = map;
	}

	public boolean moverDerecha() {
		if(map.puedoPasar((int)(pos.getX()+1),(int)pos.getY())) {
			pos.setX(pos.getX()+PASO);
			return true;
		}
		
		return false;	
	}
	
	public boolean moverIzquierda() {
		
		if(map.puedoPasar((int)Math.round(pos.getX()-1),(int)pos.getY())) {
			pos.setX(pos.getX()-PASO);
			return true;
		}
		return false;
	}
	public boolean moverAbajo() {
		if(map.puedoPasar((int)(pos.getX()),(int)(pos.getY()+1))) {
			pos.setY(pos.getY()+PASO);
			return true;
		}
		return false;
	}
	public boolean moverArriba() {
		if(map.puedoPasar((int)(pos.getX()),(int)Math.round(pos.getY()-1))) {
			pos.setY(pos.getY()-PASO);		
			return true;
		}
		return false;
	}
	
	public Posicion getPos() {
		return pos;
	}
}
