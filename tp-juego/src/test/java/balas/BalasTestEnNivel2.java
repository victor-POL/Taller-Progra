package balas;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entidad.Jugador;
import niveles.Nivel;
import utiles.Posicion;

public class BalasTestEnNivel2 {

	Nivel level;
	Jugador player;

	@Before
	public void setUp() {
		level = new Nivel("nivel_2");
		player = level.getPlayer();

	}

	// Test mapa original
	@Test
	public void disparar_sin_balas() {
		Assert.assertFalse(player.disparar());
	}

	@Test
	public void disparar_con_balas() {
		// Obtenemos un corazon
		obtener_corazon();

		Assert.assertTrue(player.disparar());
	}

	@Test
	public void matar_enemigo() {
		obtener_corazon();
		corazon_a_enemigo();

		Assert.assertTrue(player.disparar());

		Posicion pos = new Posicion(7, 6);

		Assert.assertTrue(level.getMapa().getEnemyByPosition(pos) == null);

	}

	@Test
	public void disparar_a_roca() {
		obtener_corazon();

		player.moverAbajo();
		player.moverAbajo();

		Posicion p = new Posicion(11, 6);

		Assert.assertEquals(p, player.getPos());

		player.moverIzquierda();

		Posicion pos = new Posicion(7, 6);

		player.disparar();

		Assert.assertTrue(level.getMapa().getEnemyByPosition(pos) == null);
	}

	@Test
	public void disparar_dos_veces_con_una_bala() {
		obtener_corazon();

		Assert.assertTrue(player.disparar());
		Assert.assertFalse(player.disparar());
	}

	// Funciones Privadas
	private void obtener_corazon() {
		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();

		player.moverAbajo();
		player.moverAbajo();

		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();

		player.moverAbajo();
		player.moverAbajo();

		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();

		player.moverArriba();
		player.moverArriba();
		player.moverArriba();
		player.moverArriba();

	}

	private void corazon_a_enemigo() {
		player.moverAbajo();
		player.moverAbajo();
		player.moverAbajo();
		player.moverAbajo();

		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();

		player.moverArriba();
	}

}
