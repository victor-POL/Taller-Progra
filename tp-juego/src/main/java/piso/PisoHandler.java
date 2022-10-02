package piso;

public class PisoHandler {
	
	private Piso[] pisos;
	public static final int CAMINO = 0;
	public static final int PARED = 1;
	public static final int AGUA = 2;
	public static final int PUERTA_CERRADA= 3;
	public static final int ROCA= 4;
	public static final int ARBOL= 5;
	public static final int COFRE_CERRADO = 6;
	public static final int COFRE_ABIERTO = 7;
	public static final int PUERTA_ABIERTA = 8;
	
	
	public PisoHandler() {
		pisos = new Piso[9];
		pisos[0] = new Piso("camino", false);//camino
		pisos[1] = new Piso("pared", true);//pared
		pisos[2] = new Piso("agua", true);//agua
		pisos[3] = new Piso("puerta", true);//puerta cerrada
		pisos[4] = new Piso("roca", true);//roca
		pisos[5] = new Piso("arbol", true);//arbol
		pisos[6] = new Piso("cofre cerrado", true);//cofre cerrado
		pisos[7] = new Piso("cofre abierto", false);//cofre abierto
		pisos[8] = new Piso("puerta abierta", false);//cofre abierto
	}
	
	public Piso getPisoByPosition(int n) {
		if(pisos.length < n)
			return null;
		return pisos[n];
	}
}
