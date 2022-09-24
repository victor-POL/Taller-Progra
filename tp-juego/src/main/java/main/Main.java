package main;

import java.util.Arrays;

import entidad.Jugador;
import niveles.Nivel1;

public class Main {
	public static void main(String[] args) {		
		
		Nivel1 level = new Nivel1();
		Jugador player = new Jugador(level.getMapa());
		
		
		
		System.out.println(Arrays.toString(player.getPos()));
		player.moverDerecha();
		System.out.println(Arrays.toString(player.getPos()));
		player.moverDerecha();
		System.out.println(Arrays.toString(player.getPos()));
		player.moverDerecha();
		System.out.println(Arrays.toString(player.getPos()));
		player.moverDerecha();
		System.out.println(Arrays.toString(player.getPos()));
		player.moverAbajo();
		System.out.println(Arrays.toString(player.getPos()));
		for (int i = 0; i < 16; i++) {
			player.moverDerecha();
			System.out.println(Arrays.toString(player.getPos()));
		}
		player.moverAbajo();
		System.out.println(Arrays.toString(player.getPos()));
	}
}
