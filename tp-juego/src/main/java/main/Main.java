package main;

import niveles.Nivel1;

public class Main {
	public static void main(String[] args) {		
		//NivelFacil level = new NivelFacil();
		
		//Jugador player = new Jugador(level.getMapa());
		
		Nivel1 level = new Nivel1();
		level.run();
	}
}
