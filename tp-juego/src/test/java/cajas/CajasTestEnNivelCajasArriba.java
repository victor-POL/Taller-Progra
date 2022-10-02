package cajas;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entidad.Jugador;
import niveles.NivelDeCajasArriba;
import utiles.Posicion;

public class CajasTestEnNivelCajasArriba {
	NivelDeCajasArriba level;
	Jugador player;

	@Before
	public void crearNivel() {
		level = new NivelDeCajasArriba();
		player = level.getPlayer();
	}

	// Movimientos basicos en caja 4
	@Test
	public void posicion_mover_caja_4_arriba() {
		Posicion posCaja = new Posicion(6, 3);

		player.moverArriba();
		player.moverArriba();
		player.moverArriba();

		Assert.assertTrue(level.getMapa().getCosas().get(posCaja) != null);
	}

	@Test
	public void mover_caja_4_arriba_y_chocar_pared() {
		player.moverArriba();
		player.moverArriba();
		player.moverArriba();
		player.moverArriba();
		player.moverArriba();
		player.moverArriba();
		player.moverArriba();
		player.moverArriba();

		Assert.assertFalse(player.moverArriba());
	}

	// Caja 5
	@Test
	public void posicion_caja_5_luego_mover_arriba() {
		Posicion posCaja = new Posicion(7, 3);

		player.moverArriba();
		player.moverArriba();

		player.moverDerecha();
		player.moverDerecha();

		// Empujo caja 5 a 7,3,
		player.moverArriba();

		Assert.assertTrue(level.getMapa().getCosas().get(posCaja) != null);
	}

	@Test
	public void posicion_personaje_luego_mover_arriba_caja_5() {
		Posicion posPersonaje = new Posicion(7, 4.5);

		player.moverArriba();
		player.moverArriba();

		player.moverDerecha();
		player.moverDerecha();

		// Empujo caja 5 a 7,3,
		player.moverArriba();

		Assert.assertEquals(posPersonaje, player.getPos());
	}

	@Test
	public void no_puedo_mover_caja_5_por_arbol() {
		player.moverArriba();
		player.moverArriba();

		player.moverDerecha();
		player.moverDerecha();

		// Empujo caja 5 a 7,3,
		player.moverArriba();

		// Me paro sobre la posicion de la caja anterior
		player.moverArriba();

		// Trato de empujar la caja pero tengo el arbol y no puedo
		Assert.assertFalse(player.moverArriba());
	}

	// Cajas 6 y 7
	@Test
	public void posicion_caja_6_luego_de_mover_arriba() {
		Posicion posCaja = new Posicion(8, 3);

		player.moverArriba();
		player.moverArriba();

		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();

		player.moverArriba();

		Assert.assertTrue(level.getMapa().getCosas().get(posCaja) != null);
	}

	@Test
	public void posicion_caja_7_luego_de_mover_caja_6_arriba() {
		Posicion posCaja = new Posicion(8, 2);

		player.moverArriba();
		player.moverArriba();

		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();

		player.moverArriba();

		Assert.assertTrue(level.getMapa().getCosas().get(posCaja) != null);
	}

	@Test
	public void posicion_caja_6_luego_de_querer_mover_caja_6_y_7_juntas_arriba() {
		Posicion posCaja = new Posicion(8, 3);

		player.moverArriba();
		player.moverArriba();

		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();

		player.moverArriba();
		player.moverArriba();
		player.moverArriba();

		Assert.assertTrue(level.getMapa().getCosas().get(posCaja) != null);
	}

	@Test
	public void posicion_caja_7_luego_de_querer_mover_caja_6_y_7_juntas_arriba() {
		Posicion posCaja = new Posicion(8, 2);

		player.moverArriba();
		player.moverArriba();

		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();

		player.moverArriba();
		player.moverArriba();
		player.moverArriba();

		Assert.assertTrue(level.getMapa().getCosas().get(posCaja) != null);
	}

	@Test
	public void no_puedo_avanzar_al_querer_mover_caja_6_y_7_juntas() {
		player.moverArriba();
		player.moverArriba();

		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();
		player.moverDerecha();

		player.moverArriba();
		player.moverArriba();

		Assert.assertFalse(player.moverArriba());
	}

	// Caja 3
	@Test
	public void posicion_caja_3_luego_mover_arriba() {
		Posicion posCaja = new Posicion(5, 3);

		player.moverArriba();
		player.moverArriba();

		player.moverIzquierda();
		player.moverIzquierda();

		// Empujo caja 3 a 5,3
		player.moverArriba();

		Assert.assertTrue(level.getMapa().getCosas().get(posCaja) != null);
	}

	@Test
	public void posicion_personaje_luego_mover_arriba_caja_3() {
		Posicion posPersonaje = new Posicion(5, 4.5);

		player.moverArriba();
		player.moverArriba();

		player.moverIzquierda();
		player.moverIzquierda();

		// Empujo caja 3 a 5,3
		player.moverArriba();

		Assert.assertEquals(posPersonaje, player.getPos());
	}

	@Test
	public void no_puedo_mover_caja_3_por_roca() {
		player.moverArriba();
		player.moverArriba();

		player.moverIzquierda();
		player.moverIzquierda();

		// Empujo caja 3 a 5,3
		player.moverArriba();

		player.moverArriba();

		// Trato de empujar la caja pero tengo la roca y no puedo
		Assert.assertFalse(player.moverArriba());
	}

	// Caja 2
	@Test
	public void posicion_caja_2_luego_mover_arriba() {
		Posicion posCaja = new Posicion(4, 3);

		player.moverArriba();
		player.moverArriba();

		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();

		// Empujo caja 2 a 4,3,
		player.moverArriba();

		Assert.assertTrue(level.getMapa().getCosas().get(posCaja) != null);
	}

	@Test
	public void posicion_personaje_luego_mover_arriba_caja_2() {
		Posicion posPersonaje = new Posicion(4, 4.5);

		player.moverArriba();
		player.moverArriba();

		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();

		// Empujo caja 2 a 4,3,
		player.moverArriba();

		Assert.assertEquals(posPersonaje, player.getPos());
	}

	@Test
	public void no_puedo_mover_caja_2_por_cofre() {
		player.moverArriba();
		player.moverArriba();

		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();

		// Empujo caja 2 a 4,3,
		player.moverArriba();

		player.moverArriba();

		// Trato de empujar la caja pero tengo el cofre y no puedo
		Assert.assertFalse(player.moverArriba());
	}

	// Caja 1
	@Test
	public void posicion_caja_1_luego_mover_arriba() {
		Posicion posCaja = new Posicion(3, 3);

		player.moverArriba();
		player.moverArriba();

		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();

		// Empujo caja 1 a 3,3,
		player.moverArriba();

		Assert.assertTrue(level.getMapa().getCosas().get(posCaja) != null);
	}

	@Test
	public void posicion_personaje_luego_mover_arriba_caja_1() {
		Posicion posPersonaje = new Posicion(3, 4.5);

		player.moverArriba();
		player.moverArriba();

		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();

		// Empujo caja 1 a 3,3,
		player.moverArriba();

		Assert.assertEquals(posPersonaje, player.getPos());
	}

	@Test
	public void no_puedo_mover_caja_1_por_corazon() {
		player.moverArriba();
		player.moverArriba();

		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();
		player.moverIzquierda();

		// Empujo caja 1 a 3,3,
		player.moverArriba();
		player.moverArriba();

		// Trato de empujar la caja pero tengo el corazon y no puedo
		Assert.assertFalse(player.moverArriba());
	}

}
