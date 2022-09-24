package mapa;

import java.util.ArrayList;
import java.util.List;

import entidad.ObjetosMovibles;
import piso.Piso;
import piso.PisoHandler;

public class Mapa {
	Piso[][] matPiso = new Piso[16][16];
	
	List<ObjetosMovibles> obj = new ArrayList<ObjetosMovibles>();
	
	public double xInicialJugador = 0;
	public double yInicialJugador = 0;
	
	
	public Mapa(int [][] disenio, List<ObjetosMovibles> lista) {
		for (int i = 0; i < disenio.length; i++) {
			for(int j = 0; j < disenio[0].length; j++) {
				matPiso[i][j] = new PisoHandler().getPisoByPosition(disenio[i][j]);
			}
		}
		obj = lista;
	}
	
	// x = 10; y = 15;
	//moverDerecha();
	//matPiso[11][15].collision == false;
	
	public void mostrarPisoCollisions() {
		for (Piso[] p : matPiso) {
			for(Piso pi : p) {
				System.out.print(pi.collision);
			}
			System.out.println();
		}
	}
	
	public boolean puedoPasar(int x, int y) { // x=Columnas, y=filas
		if(x < matPiso.length && x >= 0 && y >= 0 && y < matPiso[0].length)
			return matPiso[y][x].collision;
		return true;
	}
	
}
