package piso;

public class PisoHandler {
	
	private Piso[] pisos;
	public static final int PASTO = 0;
	public static final int PARED = 1;
	public static final int AGUA = 2;
	public static final int PUERTA= 3;
	
	public PisoHandler() {
		pisos = new Piso[4];
		pisos[0] = new Piso("pasto", false);//pasto
		pisos[1] = new Piso("pared", true);//pared
		pisos[2] = new Piso("agua", true);//agua
		pisos[3] = new Piso("puerta", true);//puerta
		
	}
	
	public Piso getPisoByPosition(int n) {
		if(pisos.length < n)
			return null;
		return pisos[n];
	}
}
