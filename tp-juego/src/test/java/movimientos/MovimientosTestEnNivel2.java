package movimientos;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entidad.Jugador;
import niveles.Nivel2;
import utiles.Posicion;

public class MovimientosTestEnNivel2 {
	
	Nivel2 level;
	Jugador player;
	
	@Before
	public void crearNivel( ) {
		level = new Nivel2();
		player = level.getPlayer();
	}
	
	
	@Test
	public void spawn_inicial() {
		Posicion pos = new Posicion(1,5);
		Assert.assertEquals(pos, player.getPos());
	}
	
	@Test
	public void chocarse_pared() {
		player.moverIzquierda();
		Assert.assertFalse(player.moverIzquierda());
	}
	
	@Test
	public void chequear_posicion_al_moverse_arriba( ) {
		
		Posicion pos = new Posicion(1, 4.5);
		player.moverArriba();
		Assert.assertEquals(pos, player.getPos());
	}
	
	@Test
	public void chequear_posicion_al_moverse_abajo( ) {
		
		Posicion pos = new Posicion(1, 5.5);
		player.moverAbajo();
		Assert.assertEquals(pos, player.getPos());
	}
	
	@Test
	public void chequear_posicion_al_moverse_derecha( ) {
		
		Posicion pos = new Posicion(1.5, 5);
		player.moverDerecha();
		Assert.assertEquals(pos, player.getPos());
	}
	
	@Test
	public void chequear_posicion_al_moverse_derecha_e_izquierda( ) {
		
		Posicion pos = new Posicion(1, 5);
		player.moverDerecha();
		player.moverIzquierda();
		Assert.assertEquals(pos, player.getPos());
	}
	
	@Test
	public void chequear_posicion_al_moverse_arriba_y_abajo( ) {
		
		Posicion pos = new Posicion(1, 5);
		player.moverArriba();
		player.moverAbajo();
		Assert.assertEquals(pos, player.getPos());
	}
	
	@Test
	public void moverse_y_chocarse_roca() {
		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();
		Assert.assertFalse(player.moverDerecha());
	}
	
	@Test
	public void moverse_y_chocarse_arbol() {
		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();
		Assert.assertFalse(player.moverArriba());
	}
	

}
