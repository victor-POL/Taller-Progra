package entidad;

import java.util.ArrayList;
import java.util.List;

import mapa.Mapa;
import entidad.Cosa;

public class Jugador extends Entidad {
	private int vidas;
	private int puntaje;
	
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
	
	@Override
	public boolean moverDerecha() {
		if(super.moverDerecha()) {
			Cosa c;
			if(( c = map.getByPosition(pos.getPos())) != null) {
				inventario.add(c);
				map.removeCosa(pos.getPos());
			}
			return true;	
		}
		return false;
			
		
	}
	@Override
	public boolean moverIzquierda() {
		if(super.moverIzquierda()) {
			Cosa c;
			if(( c = map.getByPosition(pos.getPos())) != null) {
				inventario.add(c);
				map.removeCosa(pos.getPos());
			}
			return true;	
		}
		return false;	
	}
	
	@Override
	public boolean moverArriba() {
		if(super.moverArriba()) {
			Cosa c;
			if(( c = map.getByPosition(pos.getPos())) != null) {
				inventario.add(c);
				map.removeCosa(pos.getPos());
			}
			return true;	
		}
		return false;	
	}
	
	@Override
	public boolean moverAbajo() {
		if(super.moverAbajo()) {
			Cosa c;
			if(( c = map.getByPosition(pos.getPos())) != null) {
				inventario.add(c);
				map.removeCosa(pos.getPos());
			}
			return true;	
		}
		return false;
	}
	
	public void mostrarInventario() {
		if(inventario.isEmpty())
			System.out.println("El inventario esta vacio");
		for (Cosa c : inventario) {
			System.out.println("Habia algo en : " + c.getPos());
		}
	}
	

	public Mapa getMap() {
		return this.map;
	}

}
