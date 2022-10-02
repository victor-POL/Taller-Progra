package mapa;

import java.util.HashMap;
import java.util.Map;

import entidad.Cosa;
import entidad.Enemigo;
import piso.Piso;
import piso.PisoHandler;
import utiles.Posicion;

public class Mapa {
	private Piso[][] matPiso = new Piso[13][13];
	private int limite = matPiso.length;

	private Map<Posicion, Cosa> cosas = new HashMap<Posicion, Cosa>();
	private Map<Posicion, Enemigo> enemies = new HashMap<Posicion, Enemigo>();

	private Posicion posInicialJugador;
	private Posicion puertaPos;
	private Posicion cofrePos;
	private Cosa puerta = null;
	private Cosa cofre = null;
	
	private boolean puertaHabilitada = false;
	private int itemsObjetivo;

	public Mapa(int[][] disenio, Map<Posicion, Cosa> cosas, Map<Posicion, Enemigo> enemies, Posicion posInicialJugador,
			int itemsObjetivo) {

		PisoHandler pisoHandler = new PisoHandler();

		for (int i = 0; i < disenio.length; i++) {
			for (int j = 0; j < disenio[0].length; j++) {
				matPiso[i][j] = pisoHandler.getPisoByPosition(disenio[i][j]);
				if (disenio[i][j] == PisoHandler.PUERTA_CERRADA)
					puertaPos = new Posicion(j, i);
				if (disenio[i][j] == PisoHandler.COFRE_CERRADO)
					cofrePos = new Posicion(j, i);
			}
		}
		this.cosas = cosas;
		this.enemies = enemies;
		this.posInicialJugador = posInicialJugador;
		this.itemsObjetivo = itemsObjetivo;

		this.puerta = new Cosa(this.puertaPos, this, true, false);
	}

	// x = 10; y = 15;
	// moverDerecha();
	// matPiso[11][15].collision == false;

	public boolean puedoPasar(int x, int y) { // x=Columnas, y=filas
		if (x <= matPiso.length && x >= 0 && y >= 0 && y <= matPiso[0].length) {
			// System.out.println(new Posicion(x,y).compareTo(new Posicion(2,2)));
			return matPiso[y][x].isCollisionable() == false
					&& (cosas.get(new Posicion(x, y)) == null || cosas.get(new Posicion(x, y)).isRecogible())
					&& enemies.get(new Posicion(x, y)) == null;
		}
		return false;
	}

	public Posicion getPosInicialJugador() {
		return posInicialJugador;
	}

	public void displayMap() { // leave space between each number
		for (int i = 0; i < matPiso.length; i++) {
			for (int j = 0; j < matPiso[0].length; j++) {
				System.out.printf("%8s", matPiso[i][j].getSprite());
			}
			System.out.println("\n");
		}

	}

	public Posicion getPosPuerta() {
		return puertaPos;
	}

	public Cosa getByPosition(Posicion p) {
		return cosas.get(p);
	}

	public int getItemsObjetivo() {
		return this.itemsObjetivo;
	}

	public void removeCosa(Posicion p) {
		cosas.remove(p);
	}

	public void actualizarCosa(Cosa c) {
		this.cosas.put(c.getPos(), c);
	}

//	public void actualizarCosa(Cosa cosaVieja, Cosa nuevo) {
//		this.cosas.put(cosaVieja.getPos(), nuevo);
//	}

	public void mostrarCosas() {
		for (Posicion p : cosas.keySet()) {
			System.out.println("Hay una cosa en : " + p);
		}
	}
	
	public void mostrarEnemigos() {
		for (Posicion p : enemies.keySet()) {
			System.out.println("Hay un enemigo en : " + p);
		}
		if(enemies.isEmpty()) {
			System.out.println("No hay enemigos!");
		}
	}

	public void habilitarCofre() {
		cofre = new Cosa(this.cofrePos, this, true, false);
		cosas.put(cofrePos, cofre);

		matPiso[(int) cofrePos.getY()][(int) cofrePos.getX()] = new PisoHandler().getPisoByPosition(PisoHandler.COFRE_ABIERTO);

	}
	public boolean getPuertaHabilitada() {
		return this.puertaHabilitada;
	}
	

	public void habilitarPuerta() {
		matPiso[(int) puertaPos.getY()][(int) puertaPos.getX()] = new PisoHandler().getPisoByPosition(PisoHandler.PUERTA_ABIERTA);
		puerta = new Cosa(puertaPos, this, true, false);
		puertaHabilitada = true;
	}
	
	public Map<Posicion, Cosa> getCosas( ) {
		return this.cosas;
	}
	
	public Enemigo getEnemyByPosition(Posicion p) {
		return enemies.get(p);
	}
	
	public int getLimite() {
	    return this.limite;
	}
	public Cosa getPuerta() {
		return this.puerta;
	}
	
	public boolean sacarEnemigo(Enemigo e) {
		if (enemies.containsValue(e)) {
			enemies.remove(e.getPos());
			return true;
		}
		return false;
	}
}