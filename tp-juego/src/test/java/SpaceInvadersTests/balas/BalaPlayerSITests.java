package SpaceInvadersTests.balas;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import animation.Control;
import entidad.Cosa;
import entidad.Enemigo;
import entidad.JugadorSpace;
import javafx.embed.swing.JFXPanel;
import mapa.MapaSpace;
import utiles.Posicion;

public class BalaPlayerSITests {
    MapaSpace map;
    JugadorSpace player;
    Enemigo enemy;
    Posicion posPlayer;
    Posicion posEnemy;
    Map<Posicion, Cosa> cosas;
    Map<Posicion, Enemigo> enemigos;
    JFXPanel panel = new JFXPanel();

    Control control;

    @Before
    public void setUp() {
        posPlayer = new Posicion(6, 6);
        posEnemy = new Posicion(6, 5);
        cosas = new HashMap<>();
        enemigos = new HashMap<>();
        map = new MapaSpace(cosas, enemigos, posPlayer);
        control = new Control();

        player = new JugadorSpace(control, map);
        map.setPlayer(player);
        enemy = new Enemigo(0, posEnemy, map, utiles.Constantes.ABAJO);

        enemigos.put(posEnemy, enemy);

    }

    @Test
    public void playerPoseeBalasDesdeElInicio() {
        Assert.assertNotEquals(null, player.disparar());
    }

    @Test
    public void balaSeMueveArribaSinObstaculos() {
        player.disparar();
        player.getBala().mover();

        Assert.assertEquals(5.5, player.getBala().getPos().getY(), 0.1);
    }

    @Test
    public void balaPlayerEliminaAEnemigo() {

        Assert.assertEquals(false, enemigos.get(posEnemy).estaMuerto());

        player.disparar();
        player.getBala().mover();
        player.getBala().mover();

        Assert.assertEquals(true, enemigos.get(posEnemy).estaMuerto());
    }

    @Test
    public void balaPlayerDesapareceLuegoDeEliminarEnemigo() {

        // Player dispara la bala
        player.disparar();

        // Mueve la bala hacia el enemigo
        Assert.assertTrue(player.getBala().mover());

        // Elimina y luego, puedo seguir moviendo la bala? => False
        Assert.assertFalse(player.getBala().mover());
    }

    @Test
    public void balaPlayerNoPasaObstaculos() {
        // Cosa en la misma linea de tiro del player
        posPlayer.setPos(new Posicion(posPlayer.getX(), 8));
        Posicion posCosa = new Posicion(posPlayer.getX(), posPlayer.getY() - 1);
        Cosa cosa1 = new Cosa(5, posCosa, map, false, false, "asteroide");
        cosas.put(cosa1.getPos(), cosa1);

        player.disparar();

        Assert.assertTrue(player.getBala().mover()); // Puede moverse la bala en espacio sin obstaculo
        Assert.assertFalse(player.getBala().mover()); // Choca con un obstaculo => false
    }

    @Test
    public void balaPlayerNoMataEnemigoDetrasDeObstaculo() {
        posPlayer.setPos(new Posicion(posPlayer.getX(), 8));
        posEnemy.setPos(posPlayer.getX(), posPlayer.getY() - 2);
        // Cosa y enemigo en la misma linea de tiro del player
        Posicion posCosa = new Posicion(posPlayer.getX(), posPlayer.getY() - 1);
        Cosa cosa1 = new Cosa(5, posCosa, map, false, false, "asteroide");
        cosas.put(cosa1.getPos(), cosa1);

        player.disparar();

        // Enemigo sigue vivo sin que lo ancance la bala
        Assert.assertFalse(enemy.estaMuerto());
        player.getBala().mover();
        Assert.assertFalse(player.getBala().mover());
        // Sigue vivo, aun cuando no puede moverse la bala
        Assert.assertFalse(enemy.estaMuerto());
    }
}
