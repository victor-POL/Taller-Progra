package main;

import jugadores.JugadorNivel1;
import jugadores.JugadorNivel2;
import niveles.Nivel;

public class Main {
	public static void main(String[] args) {
//		Nivel level = new Nivel("nivel_1");
//		JugadorNivel1 jugador = new JugadorNivel1();
		
		Nivel level = new Nivel("nivel_2");
		JugadorNivel2 jugador = new JugadorNivel2();
		
		
		level.add(jugador);
		
		level.run();
	}
}
