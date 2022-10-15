package entidad;

import utiles.Posicion;
import mapa.Mapa;

public class Cosa extends Entidad {
	private final boolean esRecogible;
	private final boolean esEmpujable;

	// Constructores

	public Cosa(Posicion pos, Mapa map, boolean esRecogible, boolean esEmpujable) {
		super(0, pos, map);
		this.esRecogible = esRecogible;
		this.esEmpujable = esEmpujable;
	}

	public Cosa(double paso, Posicion pos, Mapa map, boolean esRecogible, boolean esEmpujable) {
		super(paso, pos, map);
		this.esRecogible = esRecogible;
		this.esEmpujable = esEmpujable;
	}
	
	// Consultas

	public boolean esRecogible() {
		return esRecogible;
	}
	
	// Movimientos

	@Override
	public boolean moverDerecha() {
		if (map.puedoPasar((int) (pos.getX() + 1), (int) pos.getY())
				&& map.getByPosition(new Posicion(pos.getX() + 1, (int) pos.getY())) == null) {
			pos.setX(pos.getX() + PASO);
			return true;
		}
		return false;
	}

	@Override
	public boolean moverIzquierda() {
		if (map.puedoPasar((int) Math.round(pos.getX() - 1), (int) pos.getY())
				&& map.getByPosition(new Posicion((int) Math.round(pos.getX() - 1), (int) pos.getY())) == null) {
			pos.setX(pos.getX() - PASO);
			return true;
		}
		return false;
	}

	@Override
	public boolean moverArriba() {
		if (map.puedoPasar((int) (pos.getX()), (int) Math.round(pos.getY() - 1))
				&& map.getByPosition(new Posicion((int) (pos.getX()), (int) Math.round(pos.getY() - 1))) == null) {
			pos.setY(pos.getY() - PASO);
			return true;
		}
		return false;
	}

	@Override
	public boolean moverAbajo() {
		if (map.puedoPasar((int) (pos.getX()), (int) (pos.getY() + 1))
				&& map.getByPosition(new Posicion((int) (pos.getX()), (int) (pos.getY() + 1))) == null) {
			pos.setY(pos.getY() + PASO);
			return true;
		}
		return false;
	}

}
