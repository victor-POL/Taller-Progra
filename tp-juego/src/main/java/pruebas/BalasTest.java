package pruebas;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entidad.Cosa;
import entidad.Jugador;
import niveles.Nivel1;
import niveles.NivelDeBala;
import piso.Piso;
import piso.PisoHandler;
import utiles.Posicion;

public class BalasTest {
	
	Nivel1 level;
	NivelDeBala levelBala;
	Jugador player, playerBala;
	
	@Before
	public void setUp() {
		level = new Nivel1();
		player = level.getPlayer();
		
		levelBala = new NivelDeBala();
		playerBala = levelBala.getPlayer();
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
		
		Posicion pos = new Posicion(7,6);
		
		Assert.assertTrue(
				level.getMapa().getEnemyByPosition(pos) == null
				);
		
	}
	
	@Test
	public void disparar_a_roca() {
		obtener_corazon();
		
		player.moverAbajo();
		player.moverAbajo();
		
		Posicion p = new Posicion(11, 6);
		
		Assert.assertEquals(p, player.getPos());
		
		player.moverIzquierda();
		
		Posicion pos = new Posicion(7,6);
		
		player.disparar();
		
		Assert.assertTrue(
				level.getMapa().getEnemyByPosition(pos) == null
				);
	}
	
	@Test
	public void disparar_dos_veces_con_una_bala() {
		obtener_corazon();
		
		Assert.assertTrue(player.disparar());
		Assert.assertFalse(player.disparar());
	}
		
	// Test mapa de bala
	@Test
	public void agarrar_corazon() {
		Posicion pos = new Posicion(3,2);
		
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
		
		Posicion pos = new Posicion(2,4);
		
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
		
		Posicion pos = new Posicion(7,1);
		
		Assert.assertTrue(
				levelBala.getMapa().getEnemyByPosition(pos) != null
				);
		
		Assert.assertTrue(playerBala.disparar());
		
		// mata al enemigo (atraviesa la roca)
		Assert.assertTrue(
				levelBala.getMapa().getEnemyByPosition(pos) == null
				);
	}
	
	@Test
	public void disparar_a_cofre_cerrado() {
		playerBala.moverDerecha();
		playerBala.moverDerecha();
		
		Posicion pos = new Posicion(10,2);
		
		Assert.assertTrue(
				levelBala.getMapa().getEnemyByPosition(pos) != null
				);
		
		Assert.assertTrue(playerBala.disparar());
		
		// mata al enemigo (atraviesa cofre)
		Assert.assertTrue(
				levelBala.getMapa().getEnemyByPosition(pos) == null
				);
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
		
		Posicion pos = new Posicion(10,2);
		
		Piso piso = new PisoHandler().getPisoByPosition(7);
		
		Assert.assertTrue(piso.getSprite() == "cofre abierto");
		
		Assert.assertTrue(
				levelBala.getMapa().getEnemyByPosition(pos) != null
				);
		
		Assert.assertTrue(playerBala.disparar());
		
		// mata al enemigo (atraviesa la roca)
		Assert.assertTrue(
				levelBala.getMapa().getEnemyByPosition(pos) == null
				);
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
		Assert.assertTrue(
				levelBala.getMapa().getEnemyByPosition(pos1) != null
				);
		Assert.assertTrue(
				levelBala.getMapa().getEnemyByPosition(pos2) != null
				);
		
		Assert.assertTrue(playerBala.disparar());
		
		// Verificamos que solo mate al enemigo (6, 3)
		Assert.assertTrue(
				levelBala.getMapa().getEnemyByPosition(pos1) == null
				);
		Assert.assertTrue(
				levelBala.getMapa().getEnemyByPosition(pos2) != null
				);
		
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
		
		Assert.assertTrue(
				levelBala.getMapa().getEnemyByPosition(pos) != null
				);
		
		Assert.assertTrue(playerBala.disparar());
		
		// no mata al enemigo ( no atraviesa caja)
		Assert.assertTrue(
				levelBala.getMapa().getEnemyByPosition(pos) != null
				);
		
		
	}
	
	@Test
	public void disparar_agua() {
		playerBala.moverDerecha();
		playerBala.moverDerecha();
		
		playerBala.moverAbajo();
		
		Posicion pos = new Posicion(3, 10);
		
		Assert.assertTrue(
				levelBala.getMapa().getEnemyByPosition(pos) != null
				);
		
		Assert.assertTrue(playerBala.disparar());
		
		// mata al enemigo (atraviesa agua)
		Assert.assertTrue(
				levelBala.getMapa().getEnemyByPosition(pos) == null
				);
	}
	
	@Test
	public void disparar_corazon() {
		playerBala.moverDerecha();
		playerBala.moverDerecha();
		playerBala.moverDerecha();
		playerBala.moverDerecha();
		
		playerBala.moverAbajo();
		
		Posicion pos = new Posicion(4, 6);
		
		Assert.assertTrue(
				levelBala.getMapa().getEnemyByPosition(pos) != null
				);
		
		Assert.assertTrue(playerBala.disparar());
		
		// mata al enemigo (atraviesa corazon)
		Assert.assertTrue(
				levelBala.getMapa().getEnemyByPosition(pos) == null
				);
	}
	
	@Test
	public void disparar_arriba() {
		playerBala.moverDerecha();
		playerBala.moverDerecha();
		
		playerBala.moverArriba();
		
		Posicion pos = new Posicion(3, 1);
		
		Assert.assertTrue(
				levelBala.getMapa().getEnemyByPosition(pos) != null
				);
		
		Assert.assertTrue(playerBala.disparar());
		
		// mata al enemigo (atraviesa corazon)
		Assert.assertTrue(
				levelBala.getMapa().getEnemyByPosition(pos) == null
				);
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
		
		Assert.assertTrue(
				levelBala.getMapa().getEnemyByPosition(pos) != null
				);
		
		Assert.assertTrue(playerBala.disparar());
		
		// mata al enemigo (atraviesa corazon)
		Assert.assertTrue(
				levelBala.getMapa().getEnemyByPosition(pos) == null
				);
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
