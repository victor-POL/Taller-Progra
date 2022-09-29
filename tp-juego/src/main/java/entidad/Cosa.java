package entidad;

import utiles.Posicion;
import mapa.Mapa;

public class Cosa extends Entidad {
	public final boolean esRecogible;
	public final boolean esEmpujable;
	public Cosa(double paso, Posicion pos, Mapa map) {
		super(paso, pos, map);
		esRecogible = false;
		esEmpujable = false;
	}
	
	public Cosa(double paso, Posicion pos, Mapa map, boolean esRecogible, boolean esEmpujable) {
		super(paso, pos, map);
		this.esRecogible = esRecogible;
		this.esEmpujable = esEmpujable;
	}
	

	

}
