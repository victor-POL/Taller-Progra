package main;


import entidad.Jugador;
import niveles.Nivel1;

public class Main {
	public static void main(String[] args) {		
		
		Nivel1 level = new Nivel1();
		Jugador player = level.getPlayer();
		
		for(int i = 0; i < 6; i++) {
			player.moverAbajo();
		}
		for(int i = 0; i < 4; i++) {
			player.moverDerecha();
		}
		
		
		System.out.println(player.getPos());
		

	}
}
