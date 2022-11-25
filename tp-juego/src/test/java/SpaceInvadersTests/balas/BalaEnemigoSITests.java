package SpaceInvadersTests.balas;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import animation.Control;
import entidad.BalaEnemigo;
import entidad.Cosa;
import entidad.Enemigo;
import entidad.JugadorSpace;
import javafx.embed.swing.JFXPanel;
import mapa.MapaSpace;
import utiles.Posicion;

public class BalaEnemigoSITests {
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
        posPlayer = new Posicion(6, 5.5);
        posEnemy = new Posicion(6, 4.5);
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
    public void enemigoDisparaBala() {
        BalaEnemigo bala = enemy.disparar(utiles.Constantes.ABAJO);

        bala.mover();

        Assert.assertEquals(5.5, bala.getPos().getY(), 0.1);
    }

    @Test
    public void enemigoEliminaPlayer() {
        BalaEnemigo bala = enemy.disparar(utiles.Constantes.ABAJO);

        Assert.assertFalse(player.estaMuerto()); // Primero esta vivo

        bala.mover();
        bala.mover();

        Assert.assertTrue(player.estaMuerto()); // Ahora esta muerto
    }
}
