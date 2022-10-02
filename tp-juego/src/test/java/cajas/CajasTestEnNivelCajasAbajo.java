package cajas;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entidad.Jugador;
import niveles.NivelDeCajasAbajo;
import utiles.Posicion;

public class CajasTestEnNivelCajasAbajo {
	NivelDeCajasAbajo level;
	Jugador player;

	@Before
	public void crearNivel() {
		level = new NivelDeCajasAbajo();
		player = level.getPlayer();
	}

	// Movimientos basicos en caja 4
	@Test
	public void posicion_mover_caja_4_arriba() {
		Posicion posCaja = new Posicion(6, 9);

		player.moverAbajo();
		player.moverAbajo();
		player.moverAbajo();

		Assert.assertTrue(level.getMapa().getCosas().get(posCaja) != null);
	}

	@Test
	public void mover_caja_4_abajo_y_chocar_pared() {
		player.moverAbajo();
		player.moverAbajo();
		player.moverAbajo();
		player.moverAbajo();
		player.moverAbajo();
		player.moverAbajo();
		player.moverAbajo();
		player.moverAbajo();

		Assert.assertFalse(player.moverAbajo());
	}

	// Caja 5
	@Test
	public void posicion_caja_5_luego_mover_abajo() {
		Posicion posCaja = new Posicion(7, 9);

		player.moverAbajo();
		player.moverAbajo();

		player.moverDerecha();
		player.moverDerecha();

		// Empujo caja 5 a 7,9
		player.moverAbajo();

		Assert.assertTrue(level.getMapa().getCosas().get(posCaja) != null);
	}

	@Test
	public void posicion_personaje_luego_mover_abajo_caja_5() {
		Posicion posPersonaje = new Posicion(7, 7.5);

		player.moverAbajo();
		player.moverAbajo();

		player.moverDerecha();
		player.moverDerecha();

		// Empujo caja 5 a 7,9
		player.moverAbajo();

		Assert.assertEquals(posPersonaje, player.getPos());
	}

	@Test
	public void no_puedo_mover_caja_5_por_arbol() {
		player.moverAbajo();
		player.moverAbajo();

		player.moverDerecha();
		player.moverDerecha();

		// Empujo caja 5 a 7,3,
		player.moverAbajo();

		// Me paro sobre la posicion de la caja anterior
		player.moverAbajo();

		// Trato de empujar la caja pero tengo el arbol y no puedo
		Assert.assertFalse(player.moverAbajo());
	}

	// Cajas 6 y 7
	@Test
	public void posicion_caja_6_luego_de_mover_abajo() {
		Posicion posCaja = new Posicion(8, 9);

		player.moverAbajo();
		player.moverAbajo();

		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();

		player.moverAbajo();

		Assert.assertTrue(level.getMapa().getCosas().get(posCaja) != null);
	}

	@Test
	public void posicion_caja_7_luego_de_mover_caja_6_abajo() {
		Posicion posCaja = new Posicion(8, 10);

		player.moverAbajo();
		player.moverAbajo();

		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();

		player.moverAbajo();

		Assert.assertTrue(level.getMapa().getCosas().get(posCaja) != null);
	}

	@Test
	public void posicion_caja_6_luego_de_querer_mover_caja_6_y_7_juntas_abajo() {
		Posicion posCaja = new Posicion(8, 9);

		player.moverAbajo();
		player.moverAbajo();

		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();

		player.moverAbajo();
		player.moverAbajo();
		player.moverAbajo();

		Assert.assertTrue(level.getMapa().getCosas().get(posCaja) != null);
	}

	@Test
	public void posicion_caja_7_luego_de_querer_mover_caja_6_y_7_juntas_abajo() {
		Posicion posCaja = new Posicion(8, 10);

		player.moverAbajo();
		player.moverAbajo();

		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();

		player.moverAbajo();
		player.moverAbajo();
		player.moverAbajo();

		Assert.assertTrue(level.getMapa().getCosas().get(posCaja) != null);
	}

	@Test
	public void no_puedo_avanzar_al_querer_mover_caja_6_y_7_juntas() {
		player.moverAbajo();
		player.moverAbajo();

		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();

		player.moverAbajo();
		player.moverAbajo();

		Assert.assertFalse(player.moverAbajo());
	}

	// Caja 3
	@Test
	public void posicion_caja_3_luego_mover_abajo() {
		Posicion posCaja = new Posicion(5, 9);

		player.moverAbajo();
		player.moverAbajo();

		player.moverIzquierda();
		player.moverIzquierda();

		// Empujo caja 3 a 5,9
		player.moverAbajo();

		Assert.assertTrue(level.getMapa().getCosas().get(posCaja) != null);
	}

	@Test
	public void posicion_personaje_luego_mover_abajo_caja_3() {
		Posicion posPersonaje = new Posicion(5, 7.5);

		player.moverAbajo();
		player.moverAbajo();

		player.moverIzquierda();
		player.moverIzquierda();

		// Empujo caja 3 a 5,9
		player.moverAbajo();

		Assert.assertEquals(posPersonaje, player.getPos());
	}

	@Test
	public void no_puedo_mover_caja_3_por_roca() {
		player.moverAbajo();
		player.moverAbajo();

		player.moverIzquierda();
		player.moverIzquierda();

		// Empujo caja 3 a 5,9
		player.moverAbajo();

		player.moverAbajo();

		// Trato de empujar la caja pero tengo la roca y no puedo
		Assert.assertFalse(player.moverAbajo());
	}

	// Caja 2
	@Test
	public void posicion_caja_2_luego_mover_abajo() {
		Posicion posCaja = new Posicion(4, 9);

		player.moverAbajo();
		player.moverAbajo();

		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();
		
		// Empujo caja 2 a 4,9
		player.moverAbajo();

		Assert.assertTrue(level.getMapa().getCosas().get(posCaja) != null);
	}

	@Test
	public void posicion_personaje_luego_mover_abajo_caja_2() {
		Posicion posPersonaje = new Posicion(4, 7.5);

		player.moverAbajo();
		player.moverAbajo();

		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();

		// Empujo caja 2 a 4,9
		player.moverAbajo();

		Assert.assertEquals(posPersonaje, player.getPos());
	}

	@Test
	public void no_puedo_mover_caja_2_por_cofre() {
		player.moverAbajo();
		player.moverAbajo();

		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();

		// Empujo caja 2 a 4,9
		player.moverAbajo();

		player.moverAbajo();

		// Trato de empujar la caja pero tengo el cofre y no puedo
		Assert.assertFalse(player.moverAbajo());
	}

	// Caja 1
	@Test
	public void posicion_caja_1_luego_mover_abajo() {
		Posicion posCaja = new Posicion(3, 9);

		player.moverAbajo();
		player.moverAbajo();

		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();

		// Empujo caja 1 a 3,9
		player.moverAbajo();

		Assert.assertTrue(level.getMapa().getCosas().get(posCaja) != null);
	}

	@Test
	public void posicion_personaje_luego_mover_abajo_caja_1() {
		Posicion posPersonaje = new Posicion(3, 7.5);

		player.moverAbajo();
		player.moverAbajo();

		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();

		// Empujo caja 1 a 3,9,
		player.moverAbajo();

		Assert.assertEquals(posPersonaje, player.getPos());
	}

	@Test
	public void no_puedo_mover_caja_1_por_corazon() {
		player.moverAbajo();
		player.moverAbajo();

		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();

		// Empujo caja 1 a 3,9
		player.moverAbajo();
		player.moverAbajo();

		// Trato de empujar la caja pero tengo el corazon y no puedo
		Assert.assertFalse(player.moverAbajo());
	}

}
