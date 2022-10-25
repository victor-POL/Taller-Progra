package balas;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entidad.BalaEnemigo;
import entidad.Cosa;
import entidad.Enemigo;
import entidad.Jugador;
import javafx.embed.swing.JFXPanel;
import mapa.Mapa;
import utiles.Posicion;

public class BalaEnemigoTests {
    Mapa map;
    double[][] disenioMap = { { 1.0, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.2 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 30, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 20, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.6, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.4 }, };
    Jugador player;
    Enemigo enemy;
    Cosa cosaNoRecogible;
    Cosa cosaRecogible;
    Map<Posicion, Cosa> cosas;
    Map<Posicion, Enemigo> enemigos;
    JFXPanel panel = new JFXPanel();

    @Before
    public void setUp() {
        cosas = new HashMap<Posicion, Cosa>();
        enemigos = new HashMap<Posicion, Enemigo>();

        Posicion posEnemy = new Posicion(2, 4);
        Posicion posInicialJugador = new Posicion(2, 3);
        Posicion posCosaNoRecogible = new Posicion(10, 3);
        Posicion posCosaRecogible = new Posicion(10, 10);

        map = new Mapa(disenioMap, cosas, enemigos, posInicialJugador, 0);
        enemy = new Enemigo(1, posEnemy, map, utiles.Constantes.ARRIBA);
        cosaNoRecogible = new Cosa(0, posCosaNoRecogible, map, false, false, "ARBOL");
        cosaRecogible = new Cosa(0, posCosaRecogible, map, true, false, "COMIDA");
        cosas.put(cosaNoRecogible.getPos(), cosaNoRecogible);
        cosas.put(cosaRecogible.getPos(), cosaRecogible);
        player = new Jugador(map);
        enemigos.put(posEnemy, enemy);

        map.setPlayer(player);
    }

    @Test
    public void balaEnemigoSeMueveArriba() {
        enemy.setPos(new Posicion(6, 5.5));

        BalaEnemigo bala = enemy.disparar(utiles.Constantes.ARRIBA);

        Assert.assertNotNull(bala);

        Assert.assertTrue(bala.mover());
    }

    @Test
    public void balaEnemigoSeMueveAbajo() {
        enemy.setPos(new Posicion(6, 5.5));

        BalaEnemigo bala = enemy.disparar(utiles.Constantes.ABAJO);

        Assert.assertNotNull(bala);

        Assert.assertTrue(bala.mover());
    }

    @Test
    public void balaEnemigoSeMueveDerecha() {
        enemy.setPos(new Posicion(6.5, 5));

        BalaEnemigo bala = enemy.disparar(utiles.Constantes.DER);

        Assert.assertNotNull(bala);

        Assert.assertTrue(bala.mover());
    }

    @Test
    public void balaEnemigoSeMueveIzquierda() {
        enemy.setPos(new Posicion(6.5, 5));

        BalaEnemigo bala = enemy.disparar(utiles.Constantes.IZQ);

        Assert.assertNotNull(bala);

        Assert.assertTrue(bala.mover());
    }

    @Test
    public void balaEnemigoMataPlayerArriba() {
        BalaEnemigo bala = enemy.disparar(utiles.Constantes.ARRIBA);

        Assert.assertNotNull(bala);

        bala.mover();

        Assert.assertTrue(player.estaMuerto());
    }

    @Test
    public void balaEnemigoMataPlayerAbajo() {
        enemy.setPos(new Posicion(2, 2));

        BalaEnemigo bala = enemy.disparar(utiles.Constantes.ABAJO);

        Assert.assertNotNull(bala);

        bala.mover();

        Assert.assertTrue(player.estaMuerto());
    }

    @Test
    public void balaEnemigoMataPlayerDerecha() {
        enemy.setPos(new Posicion(1, 3));

        BalaEnemigo bala = enemy.disparar(utiles.Constantes.DER);

        Assert.assertNotNull(bala);

        bala.mover();

        Assert.assertTrue(player.estaMuerto());
    }

    @Test
    public void balaEnemigoMataPlayerIzquierda() {
        enemy.setPos(new Posicion(3, 3));

        BalaEnemigo bala = enemy.disparar(utiles.Constantes.IZQ);

        Assert.assertNotNull(bala);

        bala.mover();

        Assert.assertTrue(player.estaMuerto());
    }

    @Test
    public void balaEnemigoChocaConParedArriba() {
        enemy.setPos(new Posicion(1, 1));

        BalaEnemigo bala = enemy.disparar(utiles.Constantes.ARRIBA);

        Assert.assertNotNull(bala);

        Assert.assertFalse(bala.mover());
    }

    @Test
    public void balaEnemigoChocaConParedAbajo() {
        enemy.setPos(new Posicion(1, 11.5));

        BalaEnemigo bala = enemy.disparar(utiles.Constantes.ABAJO);

        Assert.assertNotNull(bala);

        Assert.assertFalse(bala.mover());
    }

    @Test
    public void balaEnemigoChocaConParedDerecha() {
        enemy.setPos(new Posicion(11.5, 1));

        BalaEnemigo bala = enemy.disparar(utiles.Constantes.DER);

        Assert.assertNotNull(bala);

        Assert.assertFalse(bala.mover());
    }

    @Test
    public void balaEnemigoChocaConParedIzquierda() {
        enemy.setPos(new Posicion(1, 1));

        BalaEnemigo bala = enemy.disparar(utiles.Constantes.IZQ);

        Assert.assertNotNull(bala);

        Assert.assertFalse(bala.mover());
    }

    @Test
    public void balaEnemigoChocaConCosaNoRecogibleArriba() {
        enemy.setPos(new Posicion(10, 4));

        BalaEnemigo bala = enemy.disparar(utiles.Constantes.ARRIBA);

        Assert.assertNotNull(bala);

        Assert.assertFalse(bala.mover());
    }

    @Test
    public void balaEnemigoChocaConCosaNoRecogibleAbajo() {
        enemy.setPos(new Posicion(10, 2));

        BalaEnemigo bala = enemy.disparar(utiles.Constantes.ABAJO);

        Assert.assertNotNull(bala);

        Assert.assertFalse(bala.mover());
    }

    @Test
    public void balaEnemigoChocaConCosaNoRecogibleDerecha() {
        enemy.setPos(new Posicion(9, 3));

        BalaEnemigo bala = enemy.disparar(utiles.Constantes.DER);

        Assert.assertNotNull(bala);

        Assert.assertFalse(bala.mover());
    }

    @Test
    public void balaEnemigoChocaConCosaNoRecogibleIzquierda() {
        enemy.setPos(new Posicion(11, 3));

        BalaEnemigo bala = enemy.disparar(utiles.Constantes.IZQ);

        Assert.assertNotNull(bala);

        Assert.assertFalse(bala.mover());
    }

    @Test
    public void balaEnemigoAtraviesaCosaRecogibleArriba() {
        enemy.setPos(new Posicion(10, 11));

        BalaEnemigo bala = enemy.disparar(utiles.Constantes.ARRIBA);

        Assert.assertNotNull(bala);

        Assert.assertTrue(bala.mover());
    }

    @Test
    public void balaEnemigoAtraviesaCosaRecogibleAbajo() {
        enemy.setPos(new Posicion(10, 9));

        BalaEnemigo bala = enemy.disparar(utiles.Constantes.ABAJO);

        Assert.assertNotNull(bala);

        Assert.assertTrue(bala.mover());
    }

    @Test
    public void balaEnemigoAtraviesaCosaRecogibleDerecha() {
        enemy.setPos(new Posicion(9, 10));

        BalaEnemigo bala = enemy.disparar(utiles.Constantes.DER);

        Assert.assertNotNull(bala);

        Assert.assertTrue(bala.mover());
    }

    @Test
    public void balaEnemigoAtraviesaCosaRecogibleIzquierda() {
        enemy.setPos(new Posicion(11, 10));

        BalaEnemigo bala = enemy.disparar(utiles.Constantes.IZQ);

        Assert.assertNotNull(bala);

        Assert.assertTrue(bala.mover());
    }

    @Test
    public void balaEnemigoChocaConRocaArriba() {
        enemy.setPos(new Posicion(6, 8.5));

        BalaEnemigo bala = enemy.disparar(utiles.Constantes.ARRIBA);

        Assert.assertNotNull(bala);

        Assert.assertFalse(bala.mover());
    }

    @Test
    public void balaEnemigoChocaConRocaAbajo() {
        enemy.setPos(new Posicion(6, 7));

        BalaEnemigo bala = enemy.disparar(utiles.Constantes.ABAJO);

        Assert.assertNotNull(bala);

        Assert.assertFalse(bala.mover());
    }

    @Test
    public void balaEnemigoChocaConRocaDerecha() {
        enemy.setPos(new Posicion(5, 8));

        BalaEnemigo bala = enemy.disparar(utiles.Constantes.DER);

        Assert.assertNotNull(bala);

        Assert.assertFalse(bala.mover());
    }

    @Test
    public void balaEnemigoChocaConRocaIzquierda() {
        enemy.setPos(new Posicion(6.5, 8));

        BalaEnemigo bala = enemy.disparar(utiles.Constantes.IZQ);

        Assert.assertNotNull(bala);

        Assert.assertFalse(bala.mover());
    }

    @Test
    public void balaEnemigoAtraviesaPisoDistintoDeRocaArriba() {
        enemy.setPos(new Posicion(6, 3.5));

        BalaEnemigo bala = enemy.disparar(utiles.Constantes.ARRIBA);

        Assert.assertNotNull(bala);

        Assert.assertTrue(bala.mover());
    }

    @Test
    public void balaEnemigoAtraviesaPisoDistintoDeRocaAbajo() {
        enemy.setPos(new Posicion(6, 2.5));

        BalaEnemigo bala = enemy.disparar(utiles.Constantes.ABAJO);

        Assert.assertNotNull(bala);

        Assert.assertTrue(bala.mover());
    }

    @Test
    public void balaEnemigoAtraviesaPisoDistintoDeRocaDerecha() {
        enemy.setPos(new Posicion(5.5, 3));

        BalaEnemigo bala = enemy.disparar(utiles.Constantes.DER);

        Assert.assertNotNull(bala);

        Assert.assertTrue(bala.mover());
    }

    @Test
    public void balaEnemigoAtraviesaPisoDistintoDeRocaIzquierda() {
        enemy.setPos(new Posicion(6.5, 3));

        BalaEnemigo bala = enemy.disparar(utiles.Constantes.IZQ);

        Assert.assertNotNull(bala);

        Assert.assertTrue(bala.mover());
    }
}
