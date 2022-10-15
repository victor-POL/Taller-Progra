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
	private Map<Posicion, Enemigo> enemigos = new HashMap<Posicion, Enemigo>();

	private Posicion posInicialJugador;
	private Posicion puertaPos;
	private Posicion cofrePos;
	private Cosa puerta;
	private Cosa cofre = null;

	private boolean puertaHabilitada = false;
	private int itemsObjetivo;
	
	// Constructores

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
		this.enemigos = enemies;
		this.posInicialJugador = posInicialJugador;
		this.itemsObjetivo = itemsObjetivo;

		this.puerta = new Cosa(this.puertaPos, this, true, false);
	}

	// Consultas

	public boolean puedoPasar(int x, int y) { // x=Columnas, y=filas
		if (x < matPiso.length && x >= 0 && y >= 0 && y < matPiso[0].length) {
			// System.out.println(new Posicion(x,y).compareTo(new Posicion(2,2)));
			return matPiso[y][x].esColisionable() == false
					&& (cosas.get(new Posicion(x, y)) == null || cosas.get(new Posicion(x, y)).esRecogible())
					&& enemigos.get(new Posicion(x, y)) == null;
		}
		return false;
	}

	// Getters by position

	public Cosa getByPosition(Posicion p) {
		return cosas.get(p);
	}

	public Enemigo getEnemyByPosition(Posicion p) {
		return enemigos.get(p);
	}

	// Modificar mapa

	public void removeCosa(Posicion p) {
		cosas.remove(p);
	}

	public void actualizarCosa(Cosa c) {
		this.cosas.put(c.getPos(), c);
	}

	public boolean sacarEnemigo(Enemigo e) {
		if (enemigos.containsValue(e)) {
			enemigos.remove(e.getPos());
			return true;
		}
		return false;
	}

	// Habilitaciones

	public void habilitarCofre() {
		cofre = new Cosa(this.cofrePos, this, true, false);
		cosas.put(cofrePos, cofre);

		matPiso[(int) cofrePos.getY()][(int) cofrePos.getX()] = new PisoHandler()
				.getPisoByPosition(PisoHandler.COFRE_ABIERTO);

	}

	public void habilitarPuerta() {
		matPiso[(int) puertaPos.getY()][(int) puertaPos.getX()] = new PisoHandler()
				.getPisoByPosition(PisoHandler.PUERTA_ABIERTA);
		puerta = new Cosa(puertaPos, this, true, false);
		puertaHabilitada = true;
	}

	// Getters

	public int getLimite() {
		return this.limite;
	}

	public int getItemsObjetivo() {
		return this.itemsObjetivo;
	}

	public Posicion getPosPuerta() {
		return this.puertaPos;
	}

	public Posicion getPosInicialJugador() {
		return this.posInicialJugador;
	}

	public boolean getPuertaHabilitada() {
		return this.puertaHabilitada;
	}

	public Map<Posicion, Cosa> getCosas() {
		return this.cosas;
	}

	// Utiles

	public void displayMap() { // leave space between each number
		for (int i = 0; i < matPiso.length; i++) {
			for (int j = 0; j < matPiso[0].length; j++) {
				System.out.printf("%8s", matPiso[i][j].getSprite());
			}
			System.out.println("\n");
		}

	}
}