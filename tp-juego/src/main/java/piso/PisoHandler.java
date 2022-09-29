package piso;

public class PisoHandler {
	
	private Piso[] pisos;
	
	public PisoHandler() {
		pisos = new Piso[3];
		pisos[0] = new Piso("pasto", false);//pasto
		pisos[1] = new Piso("pared", true);//pared
		pisos[2] = new Piso("agua", true);//agua
		
	}
	
	public Piso getPisoByPosition(int n) {
		if(pisos.length < n)
			return null;
		return pisos[n];
	}
}
