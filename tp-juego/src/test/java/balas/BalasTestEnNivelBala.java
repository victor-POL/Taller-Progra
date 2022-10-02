package balas;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entidad.Jugador;
import niveles.NivelDeBala;
import piso.Piso;
import piso.PisoHandler;
import utiles.Posicion;

public class BalasTestEnNivelBala {

	NivelDeBala levelBala;
	Jugador playerBala;

	@Before
	public void setUp() {
		levelBala = new NivelDeBala();
		playerBala = levelBala.getPlayer();
	}

	// Test mapa de bala
	@Test
	public void agarrar_corazon() {
		Posicion pos = new Posicion(3, 2);

		Assert.assertTrue(levelBala.getMapa().getByPosition(pos) != null);

		playerBala.moverDerecha();
		playerBala.moverDerecha();

		Assert.assertTrue(levelBala.getMapa().getByPosition(pos) == null);
	}

	@Test
	public void disparar_a_arbol() {
		playerBala.moverDerecha();
		playerBala.moverDerecha();

		playerBala.moverIzquierda();
		playerBala.moverIzquierda();

		playerBala.moverAbajo();

		Posicion pos = new Posicion(2, 4);

		Assert.assertTrue(playerBala.disparar());

		Assert.assertTrue(levelBala.getMapa().getEnemyByPosition(pos) == null);
	}

	@Test
	public void disparar_a_roca_2() {
		playerBala.moverDerecha();
		playerBala.moverDerecha();
		playerBala.moverDerecha();
		playerBala.moverDerecha();

		Posicion p = new Posicion(4, 2);

		Assert.assertEquals(p, playerBala.getPos());

		playerBala.moverArriba();
		playerBala.moverArriba();

		playerBala.moverDerecha();

		Posicion pos = new Posicion(7, 1);

		Assert.assertTrue(levelBala.getMapa().getEnemyByPosition(pos) != null);

		Assert.assertTrue(playerBala.disparar());

		// mata al enemigo (atraviesa la roca)
		Assert.assertTrue(levelBala.getMapa().getEnemyByPosition(pos) == null);
	}

	@Test
	public void disparar_a_cofre_cerrado() {
		playerBala.moverDerecha();
		playerBala.moverDerecha();

		Posicion pos = new Posicion(10, 2);

		Assert.assertTrue(levelBala.getMapa().getEnemyByPosition(pos) != null);

		Assert.assertTrue(playerBala.disparar());

		// mata al enemigo (atraviesa cofre)
		Assert.assertTrue(levelBala.getMapa().getEnemyByPosition(pos) == null);
	}

	@Test
	public void disparar_a_cofre_abierto() {
		playerBala.moverDerecha();
		playerBala.moverDerecha();
		playerBala.moverDerecha();
		playerBala.moverDerecha();

		playerBala.moverAbajo();
		playerBala.moverAbajo();
		playerBala.moverAbajo();
		playerBala.moverAbajo();
		playerBala.moverAbajo();
		playerBala.moverAbajo();

		playerBala.moverArriba();
		playerBala.moverArriba();
		playerBala.moverArriba();
		playerBala.moverArriba();
		playerBala.moverArriba();
		playerBala.moverArriba();

		playerBala.moverDerecha();

		Posicion pos = new Posicion(10, 2);

		Piso piso = new PisoHandler().getPisoByPosition(7);

		Assert.assertTrue(piso.getSprite() == "cofre abierto");

		Assert.assertTrue(levelBala.getMapa().getEnemyByPosition(pos) != null);

		Assert.assertTrue(playerBala.disparar());

		// mata al enemigo (atraviesa la roca)
		Assert.assertTrue(levelBala.getMapa().getEnemyByPosition(pos) == null);
	}

	@Test
	public void disparar_enemigo_enemigo() {
		playerBala.moverDerecha();
		playerBala.moverDerecha();

		playerBala.moverAbajo();
		playerBala.moverAbajo();

		playerBala.moverDerecha();

		Posicion pos1 = new Posicion(6, 3);
		Posicion pos2 = new Posicion(7, 3);

		// Verificamos que existan los enemigos
		Assert.assertTrue(levelBala.getMapa().getEnemyByPosition(pos1) != null);
		Assert.assertTrue(levelBala.getMapa().getEnemyByPosition(pos2) != null);

		Assert.assertTrue(playerBala.disparar());

		// Verificamos que solo mate al enemigo (6, 3)
		Assert.assertTrue(levelBala.getMapa().getEnemyByPosition(pos1) == null);
		Assert.assertTrue(levelBala.getMapa().getEnemyByPosition(pos2) != null);

	}

	@Test
	public void disparar_caja() {
		playerBala.moverDerecha();
		playerBala.moverDerecha();
		playerBala.moverDerecha();
		playerBala.moverDerecha();
		playerBala.moverDerecha();
		playerBala.moverDerecha();

		playerBala.moverAbajo();
		playerBala.moverAbajo();
		playerBala.moverAbajo();
		playerBala.moverAbajo();
		playerBala.moverAbajo();
		playerBala.moverAbajo();

		playerBala.moverDerecha();

		Posicion pos = new Posicion(10, 5);

		Assert.assertTrue(levelBala.getMapa().getEnemyByPosition(pos) != null);

		Assert.assertTrue(playerBala.disparar());

		// no mata al enemigo ( no atraviesa caja)
		Assert.assertTrue(levelBala.getMapa().getEnemyByPosition(pos) != null);

	}

	@Test
	public void disparar_agua() {
		playerBala.moverDerecha();
		playerBala.moverDerecha();

		playerBala.moverAbajo();

		Posicion pos = new Posicion(3, 10);

		Assert.assertTrue(levelBala.getMapa().getEnemyByPosition(pos) != null);

		Assert.assertTrue(playerBala.disparar());

		// mata al enemigo (atraviesa agua)
		Assert.assertTrue(levelBala.getMapa().getEnemyByPosition(pos) == null);
	}

	@Test
	public void disparar_corazon() {
		playerBala.moverDerecha();
		playerBala.moverDerecha();
		playerBala.moverDerecha();
		playerBala.moverDerecha();

		playerBala.moverAbajo();

		Posicion pos = new Posicion(4, 6);

		Assert.assertTrue(levelBala.getMapa().getEnemyByPosition(pos) != null);

		Assert.assertTrue(playerBala.disparar());

		// mata al enemigo (atraviesa corazon)
		Assert.assertTrue(levelBala.getMapa().getEnemyByPosition(pos) == null);
	}

	@Test
	public void disparar_arriba() {
		playerBala.moverDerecha();
		playerBala.moverDerecha();

		playerBala.moverArriba();

		Posicion pos = new Posicion(3, 1);

		Assert.assertTrue(levelBala.getMapa().getEnemyByPosition(pos) != null);

		Assert.assertTrue(playerBala.disparar());

		// mata al enemigo (atraviesa corazon)
		Assert.assertTrue(levelBala.getMapa().getEnemyByPosition(pos) == null);
	}

	@Test
	public void disparar_izquierda() {
		playerBala.moverDerecha();
		playerBala.moverDerecha();
		playerBala.moverDerecha();
		playerBala.moverDerecha();

		playerBala.moverArriba();
		playerBala.moverArriba();

		playerBala.moverIzquierda();

		Posicion pos = new Posicion(3, 1);

		Assert.assertTrue(levelBala.getMapa().getEnemyByPosition(pos) != null);

		Assert.assertTrue(playerBala.disparar());

		// mata al enemigo (atraviesa corazon)
		Assert.assertTrue(levelBala.getMapa().getEnemyByPosition(pos) == null);
	}

}
