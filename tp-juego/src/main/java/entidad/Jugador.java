package entidad;

import java.util.ArrayList;
import java.util.List;

import mapa.Mapa;
import utiles.Constantes;
import utiles.Posicion;

public class Jugador extends Entidad {
//	private int vidas;
//	private int puntaje;
	private int cantBalas = 0;
	private int orientacionActual = utiles.Constantes.DER;

	private List<Cosa> inventario = new ArrayList<Cosa>();

	public Jugador() {
		super();
	}

	public Jugador(Mapa map) {
		super(0.5, map.getPosInicialJugador(), map);
	}

	public double getPaso() {
		return this.PASO;
	}

	private boolean chequeo_items_y_progreso(Cosa c) {
		if ((c = map.getByPosition(pos.getPos())) != null) {

			inventario.add(c);
			map.removeCosa(pos.getPos());
			
			cantBalas++;
			
			if (inventario.size() == map.getItemsObjetivo()) {
				map.habilitarCofre();
			} else if (inventario.size() > map.getItemsObjetivo() && !map.getPuertaHabilitada()) {
				map.habilitarPuerta();
			}
		}
		return true;
	}

	@Override
	public boolean moverDerecha() {
		Cosa c = null;
		boolean res = false;

		if (super.moverDerecha()) {
			res = chequeo_items_y_progreso(c);
			
		} else {

			c = map.getByPosition(this.pos.posDerecha());

			// 1 - Hay algo en esa casilla?
			if (c != null) {

				this.map.removeCosa(c.getPos());
				res = c.moverDerecha();
				this.map.actualizarCosa(c);
				

				super.moverDerecha();
			} else
				res = false;

		}
		
		
		orientacionActual = Constantes.DER;
		return res;
	}

	@Override
	public boolean moverIzquierda() {

		Cosa c = null;
		boolean res = false;

		if (super.moverIzquierda()) {
			res = chequeo_items_y_progreso(c);

		} else {

			c = map.getByPosition(this.pos.posIzquierda());

			// 1 - Hay algo en esa casilla?
			if (c != null) {

				this.map.removeCosa(c.getPos());
				res = c.moverIzquierda();
				this.map.actualizarCosa(c);
				

				super.moverIzquierda();
			} else
				res = false;

		}

		orientacionActual = Constantes.IZQ;
		return res;
	}

	@Override
	public boolean moverArriba() {
		Cosa c = null;
		boolean res = false;

		if (super.moverArriba()) {
			res = chequeo_items_y_progreso(c);

		} else {

			c = map.getByPosition(this.pos.posArriba());

			// 1 - Hay algo en esa casilla?
			if (c != null) {

				this.map.removeCosa(c.getPos());
				res = c.moverArriba();
				this.map.actualizarCosa(c);
				

				super.moverArriba();
			} else
				res = false;

		}

		orientacionActual = Constantes.ARRIBA;
		return res;
	}

	@Override
	public boolean moverAbajo() {
		Cosa c = null;
		boolean res = false;

		if (super.moverAbajo()) {
			res = chequeo_items_y_progreso(c);
		} else {

			c = map.getByPosition(this.pos.posAbajo());

			// 1 - Hay algo en esa casilla?
			if (c != null) {
				this.map.removeCosa(c.getPos());
				res = c.moverAbajo();
				this.map.actualizarCosa(c);
				

				super.moverAbajo();
			} else
				res = false;
		}

		orientacionActual = Constantes.ABAJO;
		return res;
	}

	
	public boolean disparar() {
		if (cantBalas > 0) {
			Bala b = new Bala(new Posicion(this.pos.getX(), this.pos.getY()), orientacionActual, map);
			cantBalas--;
			while(b.mover()) {

				Cosa c = map.getByPosition(b.getPos());
				Enemigo e = map.getEnemyByPosition(b.getPos());
				
				if (c != null && !c.isRecogible()) {
					break;
				}
				if (e != null) {
					map.sacarEnemigo(e);
					break;
				}
			}
			
			return true;
		}
		
		return false;
	}


//	public void mostrarInventario() {
//		if (inventario.isEmpty())
//			System.out.println("El inventario esta vacio");
//		for (Cosa c : inventario) {
//			System.out.println("Habia algo en : " + c.getPos());
//		}
//	}

	public Mapa getMap() {
		return this.map;
	}

	public int getItemsInventario( ) {
		return inventario.size();
	}

}


