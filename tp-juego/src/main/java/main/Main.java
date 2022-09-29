package main;


import entidad.Jugador;
import entidad.JugadorNivel1;
import niveles.Nivel1;

public class Main {
	public static void main(String[] args) {		
		
		Nivel1 level = new Nivel1();
		Jugador player = level.getPlayer();
		JugadorNivel1 jn1 = null;
		level.add(jn1);
		
		level.run();
		
		

	}
}
