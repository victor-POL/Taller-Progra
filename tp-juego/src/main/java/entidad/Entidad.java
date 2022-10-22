package entidad;

import animation.Control;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import mapa.Mapa;
import utiles.Constantes;
import utiles.Posicion;

public class Entidad implements Constantes {
	protected final double STEP_SIZE;
	protected Posicion pos;
	protected Mapa map;
	protected ImageView render;
	protected Control control;
	

	// Constructores
	
	public Entidad() {
		STEP_SIZE = 1;
	}

	public Entidad(double paso, Posicion pos, Mapa map) {
		STEP_SIZE = paso;
		this.pos = pos;
		this.map = map;
	}
	
	// Movimientos

	public boolean moverDerecha() {
		if (map.puedoPasar((int) (pos.getX() + 1), (int) pos.getY())) {
			pos.setX(pos.getX() + STEP_SIZE);
			return true;
		}

		return false;
	}

	public boolean moverIzquierda() {

		if (map.puedoPasar((int) Math.round(pos.getX() - 1), (int) pos.getY())) {
			pos.setX(pos.getX() - STEP_SIZE);
			return true;
		}
		return false;
	}

	public boolean moverAbajo() {
		if (map.puedoPasar((int) (pos.getX()), (int) (pos.getY() + 1))) {
			pos.setY(pos.getY() + STEP_SIZE);
			return true;
		}
		return false;
	}

	public boolean moverArriba() {
		if (map.puedoPasar((int) (pos.getX()), (int) Math.round(pos.getY() - 1))) {
			pos.setY(pos.getY() - STEP_SIZE);
			return true;
		}
		return false;
	}

	// Getters
	
	public Posicion getPos() {
		return pos;
	}
	
	public double getPaso() {
		return this.STEP_SIZE;
	}
	
	public Mapa getMap() {
		return this.map;
	}

	public Node getRender() {
		return render;
	}

	public void setPos(Posicion pos) {
		this.pos = pos;
	}
}
