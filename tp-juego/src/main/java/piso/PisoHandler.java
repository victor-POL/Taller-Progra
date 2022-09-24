package piso;

public class PisoHandler {
	
	Piso[] pisos;
	
	public PisoHandler() {
		pisos = new Piso[3];
		//pisos[0] ----> pasto
		pisos[0] = new Piso();
		pisos[1] = new Piso();
		pisos[2] = new Piso();
		pisos[1].collision = true; //agua
		pisos[2].collision = true; //pared
	}
	
	public Piso getPisoByPosition(int n) {
		if(pisos.length < n)
			return null;
		return pisos[n];
	}
}
