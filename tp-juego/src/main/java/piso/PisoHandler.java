package piso;

public class PisoHandler {

	private Piso[] pisos;
	public static final int CAMINO = 0;
	public static final int PARED = 1;
	public static final int AGUA = 2;
	public static final int PUERTA_CERRADA = 3;
	public static final int ROCA = 4;
	public static final int ARBOL = 5;
	public static final int COFRE_CERRADO = 6;
	public static final int COFRE_ABIERTO = 7;
	public static final int PUERTA_ABIERTA = 8;

	// Constructores
	
	public PisoHandler() {
		pisos = new Piso[9];
		pisos[0] = new Piso("file:src/main/resources/piso/imagenes/camino.png", false);// camino
		pisos[1] = new Piso("file:src/main/resources/piso/imagenes/pared.png", true);// pared
		pisos[2] = new Piso("file:src/main/resources/piso/imagenes/agua.png", true);// agua
		pisos[3] = new Piso("file:src/main/resources/piso/imagenes/puertaCerrada.png", true);// puerta cerrada
		pisos[4] = new Piso("file:src/main/resources/piso/imagenes/roca.png", true);// roca
		pisos[5] = new Piso("file:src/main/resources/piso/imagenes/arbol.png", true);// arbol
		pisos[6] = new Piso("file:src/main/resources/piso/imagenes/cofre.png", true);// cofre cerrado
		pisos[7] = new Piso("file:src/main/resources/piso/imagenes/cofreAbierto.png", false);// cofre abierto
		pisos[8] = new Piso("file:src/main/resources/piso/imagenes/puertaAbierta.png", false);// cofre abierto
	}
	
	// Metodos

	public Piso getPisoByPosition(int n) {
		if (pisos.length < n)
			return null;
		return pisos[n];
	}
}
