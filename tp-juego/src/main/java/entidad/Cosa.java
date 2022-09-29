package entidad;

import utiles.Posicion;
import mapa.Mapa;

public class Cosa extends Entidad {
	public boolean esRecogible;
	public boolean esEmpujable;
	public Cosa(double paso, Posicion pos, Mapa map) {
		super(paso, pos, map);
	}

	

}
