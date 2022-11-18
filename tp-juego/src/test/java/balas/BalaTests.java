package balas;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entidad.Cosa;
import entidad.Enemigo;
import entidad.JugadorLolo;
import javafx.embed.swing.JFXPanel;
import mapa.Mapa;
import utiles.Posicion;

public class BalaTests {
    Mapa map;
    double[][] disenioMap = { { 1.0, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.2 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 20, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.6, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.4 }, };
    JugadorLolo player;
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

        Posicion posInicialJugador = new Posicion(2, 3.5);
        Posicion posCosaNoRecogible = new Posicion(10, 3);
        Posicion posCosaRecogible = new Posicion(10, 10);
        Posicion posEnemy = new Posicion(2, 3);

        map = new Mapa(disenioMap, cosas, enemigos, posInicialJugador, 0);
        enemy = new Enemigo(1, posEnemy, map, utiles.Constantes.ABAJO);
        cosaNoRecogible = new Cosa(0, posCosaNoRecogible, map, false, false, "ARBOL");
        cosaRecogible = new Cosa(0, posCosaRecogible, map, true, false, "COMIDA");
        cosas.put(cosaNoRecogible.getPos(), cosaNoRecogible);
        cosas.put(cosaRecogible.getPos(), cosaRecogible);
        player = new JugadorLolo(map);
        player.setCantBalas(1);
        enemigos.put(enemy.getPos(), enemy);

        map.setPlayer(player);
    }

    // TODO: MODIFICAR MÉTODO Muerto() POR muerto() CUANDO SE MODIFIQUE SU NOMBRE EN
    // ENEMIGO
    @Test
    public void balaSeMueveArriba() {
        player.setPos(new Posicion(6, 5.5));

        player.setOrientacion(utiles.Constantes.ARRIBA);

        Assert.assertNotNull(player.disparar());

        map.getBalas().get(0).mover();

        Assert.assertTrue(map.getBalas().get(0).mover());
    }

    @Test
    public void balaSeMueveAbajo() {
        player.setPos(new Posicion(6, 5.5));

        player.setOrientacion(utiles.Constantes.ABAJO);

        Assert.assertNotNull(player.disparar());

        map.getBalas().get(0).mover();

        Assert.assertTrue(map.getBalas().get(0).mover());
    }

    @Test
    public void balaSeMueveDerecha() {
        player.setPos(new Posicion(6.5, 5));

        Assert.assertNotNull(player.disparar());

        map.getBalas().get(0).mover();

        Assert.assertTrue(map.getBalas().get(0).mover());
    }

    @Test
    public void balaSeMueveIzquierda() {
        player.setPos(new Posicion(6.5, 5));

        player.setOrientacion(utiles.Constantes.IZQ);

        Assert.assertNotNull(player.disparar());

        map.getBalas().get(0).mover();

        Assert.assertTrue(map.getBalas().get(0).mover());
    }

    @Test
    public void mataEnemigoArriba() {
        player.setOrientacion(utiles.Constantes.ARRIBA);

        Assert.assertNotNull(player.disparar());

        map.getBalas().get(0).mover();

        Assert.assertTrue(map.getEnemyByPosition(enemy.getPos()).estaMuerto());
    }

    @Test
    public void mataEnemigoAbajo() {
        player.setPos(new Posicion(2, 2.5));

        player.setOrientacion(utiles.Constantes.ABAJO);

        Assert.assertNotNull(player.disparar());

        map.getBalas().get(0).mover();

        Assert.assertTrue(map.getEnemyByPosition(enemy.getPos()).estaMuerto());
    }

    @Test
    public void mataEnemigoDerecha() {
        player.setPos(new Posicion(1.5, 3));

        Assert.assertNotNull(player.disparar());

        map.getBalas().get(0).mover();

        Assert.assertTrue(map.getEnemyByPosition(enemy.getPos()).estaMuerto());
    }

    @Test
    public void mataEnemigoIzquierda() {
        player.setPos(new Posicion(2.5, 3));

        player.setOrientacion(utiles.Constantes.IZQ);

        Assert.assertNotNull(player.disparar());

        map.getBalas().get(0).mover();

        Assert.assertTrue(map.getEnemyByPosition(enemy.getPos()).estaMuerto());
    }

    @Test
    public void balaChocaConParedArriba() {
        player.setPos(new Posicion(1, 0.5));

        player.setOrientacion(utiles.Constantes.ARRIBA);

        Assert.assertNotNull(player.disparar());

        Assert.assertFalse(map.getBalas().get(0).mover());
    }

    @Test
    public void balaChocaConParedAbajo() {
        // TODO: CAMBIAR 12 POR 11.5 CUANDO SE MODIFIQUE EL MÉTODO MOVER DE LA CLASE
        // BALA
        player.setPos(new Posicion(1, 12));

        player.setOrientacion(utiles.Constantes.ABAJO);

        Assert.assertNotNull(player.disparar());

        Assert.assertFalse(map.getBalas().get(0).mover());
    }

    @Test
    public void balaChocaConParedDerecha() {
        // TODO: CAMBIAR 12 POR 11.5 CUANDO SE MODIFIQUE EL MÉTODO MOVER DE LA CLASE
        // BALA
        player.setPos(new Posicion(12, 1));

        Assert.assertNotNull(player.disparar());

        Assert.assertFalse(map.getBalas().get(0).mover());
    }

    @Test
    public void balaChocaConParedIzquierda() {
        player.setPos(new Posicion(0.5, 1));

        player.setOrientacion(utiles.Constantes.IZQ);

        Assert.assertNotNull(player.disparar());

        Assert.assertFalse(map.getBalas().get(0).mover());
    }

    @Test
    public void balaChocaConCosaNoRecogibleArriba() {
        player.setPos(new Posicion(10, 3.5));

        player.setOrientacion(utiles.Constantes.ARRIBA);

        Assert.assertNotNull(player.disparar());

        Assert.assertFalse(map.getBalas().get(0).mover());
    }

    @Test
    public void balaChocaConCosaNoRecogibleAbajo() {
        player.setPos(new Posicion(10, 2.5));

        player.setOrientacion(utiles.Constantes.ABAJO);

        Assert.assertNotNull(player.disparar());

        Assert.assertFalse(map.getBalas().get(0).mover());
    }

    @Test
    public void balaChocaConCosaNoRecogibleDerecha() {
        player.setPos(new Posicion(9.5, 3));

        Assert.assertNotNull(player.disparar());

        Assert.assertFalse(map.getBalas().get(0).mover());
    }

    @Test
    public void balaChocaConCosaNoRecogibleIzquierda() {
        player.setPos(new Posicion(10.5, 3));

        player.setOrientacion(utiles.Constantes.IZQ);

        Assert.assertNotNull(player.disparar());

        Assert.assertFalse(map.getBalas().get(0).mover());
    }

    @Test
    public void balaAtraviesaCosaRecogibleArriba() {
        player.setPos(new Posicion(10, 10.5));

        player.setOrientacion(utiles.Constantes.ARRIBA);

        Assert.assertNotNull(player.disparar());

        Assert.assertTrue(map.getBalas().get(0).mover());
    }

    @Test
    public void balaAtraviesaCosaRecogibleAbajo() {
        player.setPos(new Posicion(10, 9.5));

        player.setOrientacion(utiles.Constantes.ABAJO);

        Assert.assertNotNull(player.disparar());

        Assert.assertTrue(map.getBalas().get(0).mover());
    }

    @Test
    public void balaAtraviesaCosaRecogibleDerecha() {
        player.setPos(new Posicion(9.5, 10));

        Assert.assertNotNull(player.disparar());

        Assert.assertTrue(map.getBalas().get(0).mover());
    }

    @Test
    public void balaAtraviesaCosaRecogibleIzquierda() {
        player.setPos(new Posicion(10.5, 10));

        player.setOrientacion(utiles.Constantes.IZQ);

        Assert.assertNotNull(player.disparar());

        Assert.assertTrue(map.getBalas().get(0).mover());
    }

    @Test
    public void balaChocaConPisoDistintoDeCaminoArriba() {
        player.setPos(new Posicion(6, 8));

        player.setOrientacion(utiles.Constantes.ARRIBA);

        Assert.assertNotNull(player.disparar());

        Assert.assertFalse(map.getBalas().get(0).mover());
    }

    @Test
    public void balaChocaConPisoDistintoDeCaminoAbajo() {
        player.setPos(new Posicion(6, 7.5));

        player.setOrientacion(utiles.Constantes.ABAJO);

        Assert.assertNotNull(player.disparar());

        Assert.assertFalse(map.getBalas().get(0).mover());
    }

    @Test
    public void balaChocaConPisoDistintoDeCaminoDerecha() {
        player.setPos(new Posicion(5.5, 8));

        player.setOrientacion(utiles.Constantes.DER);

        Assert.assertNotNull(player.disparar());

        Assert.assertFalse(map.getBalas().get(0).mover());
    }

    @Test
    public void balaChocaConPisoDistintoDeCaminoIzquierda() {
        player.setPos(new Posicion(6.5, 8));

        player.setOrientacion(utiles.Constantes.IZQ);

        Assert.assertNotNull(player.disparar());

        Assert.assertFalse(map.getBalas().get(0).mover());
    }
}
