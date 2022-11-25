package spaceInvaders.balas;

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
    public void balaSeMueveArriba() {
        player.disparar();
        player.getBala().mover();

        Assert.assertEquals(5.5, player.getBala().getPos().getY(), 0.1);
    }

    @Test
    public void playerEliminaAEnemigo() {

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
}
