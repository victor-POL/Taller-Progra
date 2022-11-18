package movimientos;

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

public class MovimientosJugadorTests {
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
            { 1.7, 0, 0, 0, 0, 0, 6, 0, 0, 0, 40, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.6, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.4 }, };
    JugadorLolo player;
    Enemigo enemy;
    Cosa cosaEmpujable;
    Cosa cosaEmpujable2;
    Cosa cosaRecogible;
    Map<Posicion, Cosa> cosas;
    Map<Posicion, Enemigo> enemigos;
    JFXPanel panel = new JFXPanel();

    @Before
    public void setUp() {
        cosas = new HashMap<Posicion, Cosa>();
        enemigos = new HashMap<Posicion, Enemigo>();

        Posicion posInicialJugador = new Posicion(6, 5);
        Posicion posCosaEmpujable = new Posicion(10, 3);
        Posicion posCosaEmpujable2 = new Posicion(10, 2);
        Posicion posCosaRecogible = new Posicion(2, 10);
        Posicion posEnemy = new Posicion(2, 3);

        map = new Mapa(disenioMap, cosas, enemigos, posInicialJugador, 0);
        enemy = new Enemigo(1, posEnemy, map, utiles.Constantes.ABAJO);
        cosaEmpujable = new Cosa(1, posCosaEmpujable, map, false, true, "obstaculo empujable");
        cosaEmpujable2 = new Cosa(1, posCosaEmpujable2, map, false, true, "obstaculo empujable");
        cosaRecogible = new Cosa(0, posCosaRecogible, map, true, false, "COMIDA");
        cosas.put(posCosaEmpujable, cosaEmpujable);
        cosas.put(posCosaRecogible, cosaRecogible);
        player = new JugadorLolo(map);
        enemigos.put(posEnemy, enemy);

        map.setPlayer(player);
    }

    @Test
    public void playerSeMueveArriba() {
        Assert.assertTrue(player.moverArriba());
    }

    @Test
    public void playerSeMueveAbajo() {
        Assert.assertTrue(player.moverAbajo());
    }

    @Test
    public void playerSeMueveDerecha() {
        Assert.assertTrue(player.moverDerecha());
    }

    @Test
    public void playerSeMueveIzquierda() {
        Assert.assertTrue(player.moverIzquierda());
    }

    @Test
    public void playerChocaConParedArriba() {
        player.setPos(new Posicion(1, 1));
        Assert.assertFalse(player.moverArriba());
    }

    @Test
    public void playerChocaConParedAbajo() {
        player.setPos(new Posicion(1, 11));
        Assert.assertFalse(player.moverAbajo());
    }

    @Test
    public void playerChocaConParedDerecha() {
        player.setPos(new Posicion(11, 11));
        Assert.assertFalse(player.moverDerecha());
    }

    @Test
    public void playerChocaConParedIzquierda() {
        player.setPos(new Posicion(1, 11));
        Assert.assertFalse(player.moverIzquierda());
    }

    @Test
    public void playerChocaConRocaArriba() {
        player.setPos(new Posicion(6, 9));
        Assert.assertFalse(player.moverArriba());
    }

    @Test
    public void playerChocaConRocaAbajo() {
        player.setPos(new Posicion(6, 7));
        Assert.assertFalse(player.moverAbajo());
    }

    @Test
    public void playerChocaConRocaDerecha() {
        player.setPos(new Posicion(5, 8));
        Assert.assertFalse(player.moverDerecha());
    }

    @Test
    public void playerChocaConRocaIzquierda() {
        player.setPos(new Posicion(7, 8));
        Assert.assertFalse(player.moverIzquierda());
    }

    @Test
    public void playerChocaConArbolArriba() {
        player.setPos(new Posicion(6, 4));
        Assert.assertFalse(player.moverArriba());
    }

    @Test
    public void playerChocaConArbolAbajo() {
        player.setPos(new Posicion(6, 2));
        Assert.assertFalse(player.moverAbajo());
    }

    @Test
    public void playerChocaConArbolDerecha() {
        player.setPos(new Posicion(5, 3));
        Assert.assertFalse(player.moverDerecha());
    }

    @Test
    public void playerChocaConArbolIzquierda() {
        player.setPos(new Posicion(7, 3));
        Assert.assertFalse(player.moverIzquierda());
    }

    @Test
    public void playerChocaConAguaArriba() {
        player.setPos(new Posicion(10, 11));
        Assert.assertFalse(player.moverArriba());
    }

    @Test
    public void playerChocaConAguaAbajo() {
        player.setPos(new Posicion(10, 9));
        Assert.assertFalse(player.moverAbajo());
    }

    @Test
    public void playerChocaConAguaDerecha() {
        player.setPos(new Posicion(9, 10));
        Assert.assertFalse(player.moverDerecha());
    }

    @Test
    public void playerChocaConAguaIzquierda() {
        player.setPos(new Posicion(11, 10));
        Assert.assertFalse(player.moverIzquierda());
    }

    @Test
    public void playerChocaConEnemigoArriba() {
        player.setPos(new Posicion(2, 4));
        Assert.assertFalse(player.moverArriba());
    }

    @Test
    public void playerChocaConEnemigoAbajo() {
        player.setPos(new Posicion(2, 2));
        Assert.assertFalse(player.moverAbajo());
    }

    @Test
    public void playerChocaConEnemigoDerecha() {
        player.setPos(new Posicion(1, 3));
        Assert.assertFalse(player.moverDerecha());
    }

    @Test
    public void playerChocaConEnemigoIzquierda() {
        player.setPos(new Posicion(3, 3));
        Assert.assertFalse(player.moverIzquierda());
    }

    @Test
    public void playerAvanzaConCosaEmpujableArriba() {
        player.setPos(new Posicion(10, 4));
        Assert.assertTrue(player.moverArriba());
    }

    @Test
    public void playerAvanzaConCosaEmpujableAbajo() {
        player.setPos(new Posicion(10, 2));
        Assert.assertTrue(player.moverAbajo());
    }

    @Test
    public void playerAvanzaConCosaEmpujableDerecha() {
        player.setPos(new Posicion(9, 3));
        Assert.assertTrue(player.moverDerecha());
    }

    @Test
    public void playerAvanzaConCosaEmpujableIzquierda() {
        player.setPos(new Posicion(11, 3));
        Assert.assertTrue(player.moverIzquierda());
    }

    @Test
    public void playerAvanzaConCosaRecogibleArriba() {
        player.setPos(new Posicion(2, 11));
        Assert.assertTrue(player.moverArriba());
    }

    @Test
    public void playerAvanzaConCosaRecogibleAbajo() {
        player.setPos(new Posicion(2, 9));
        Assert.assertTrue(player.moverAbajo());
    }

    @Test
    public void playerAvanzaConCosaRecogibleDerecha() {
        player.setPos(new Posicion(1, 10));
        Assert.assertTrue(player.moverDerecha());
    }

    @Test
    public void playerAvanzaConCosaRecogibleIzquierda() {
        player.setPos(new Posicion(3, 10));
        Assert.assertTrue(player.moverIzquierda());
    }

    @Test
    public void playerChocaCon2CosasEmpujablesArriba() {
        cosas.put(cosaEmpujable2.getPos(), cosaEmpujable2);
        player.setPos(new Posicion(10, 4));
        Assert.assertFalse(player.moverArriba());
    }

    @Test
    public void playerChocaCon2CosasEmpujablesAbajo() {
        cosaEmpujable2.setPos(new Posicion(10, 4));
        cosas.put(cosaEmpujable2.getPos(), cosaEmpujable2);
        player.setPos(new Posicion(10, 2));
        Assert.assertFalse(player.moverAbajo());
    }

    @Test
    public void playerChocaCon2CosasEmpujablesDerecha() {
        cosaEmpujable2.setPos(new Posicion(11, 3));
        cosas.put(cosaEmpujable2.getPos(), cosaEmpujable2);
        player.setPos(new Posicion(9, 3));
        Assert.assertFalse(player.moverDerecha());
    }

    @Test
    public void playerChocaCon2CosasEmpujablesIzquierda() {
        cosaEmpujable2.setPos(new Posicion(9, 3));
        cosas.put(cosaEmpujable2.getPos(), cosaEmpujable2);
        player.setPos(new Posicion(11, 3));
        Assert.assertFalse(player.moverIzquierda());
    }

    @Test
    public void playerChocaConCofreCerradoArriba() {
        player.setPos(new Posicion(6, 11));
        Assert.assertFalse(player.moverArriba());
    }

    @Test
    public void playerChocaConCofreCerradoAbajo() {
        player.setPos(new Posicion(6, 9));
        Assert.assertFalse(player.moverAbajo());
    }

    @Test
    public void playerChocaConCofreCerradoDerecha() {
        player.setPos(new Posicion(5, 10));
        Assert.assertFalse(player.moverDerecha());
    }

    @Test
    public void playerChocaConCofreCerradoIzquierda() {
        player.setPos(new Posicion(7, 10));
        Assert.assertFalse(player.moverIzquierda());
    }

    @Test
    public void playerAvanzaConCofreAbiertoArriba() {
        map.habilitarItemParaPasarNivel();
        player.setPos(new Posicion(6, 11));
        Assert.assertTrue(player.moverArriba());
    }

    @Test
    public void playerAvanzaConCofreAbiertoAbajo() {
        map.habilitarItemParaPasarNivel();
        player.setPos(new Posicion(6, 9));
        Assert.assertTrue(player.moverAbajo());
    }

    @Test
    public void playerAvanzaConCofreAbiertoDerecha() {
        map.habilitarItemParaPasarNivel();
        player.setPos(new Posicion(5, 10));
        Assert.assertTrue(player.moverDerecha());
    }

    @Test
    public void playerAvanzaConCofreAbiertoIzquierda() {
        map.habilitarItemParaPasarNivel();
        player.setPos(new Posicion(7, 10));
        Assert.assertTrue(player.moverIzquierda());
    }

    @Test
    public void playerChocaConPuertaCerradaArriba() {
        Posicion posPlayer = new Posicion(6, 1);
        disenioMap[0][6] = 2.3;
        map = new Mapa(disenioMap, cosas, enemigos, posPlayer, 0);
        player = new JugadorLolo(map);
        player.setPos(posPlayer);
        map.setPlayer(player);
        Assert.assertFalse(player.moverArriba());
    }

    @Test
    public void playerChocaConPuertaCerradaAbajo() {
        Posicion posPlayer = new Posicion(6, 11);
        disenioMap[12][6] = 2.1;
        map = new Mapa(disenioMap, cosas, enemigos, posPlayer, 0);
        player = new JugadorLolo(map);
        player.setPos(posPlayer);
        map.setPlayer(player);
        Assert.assertFalse(player.moverAbajo());
    }

    @Test
    public void playerChocaConPuertaCerradaDerecha() {
        Posicion posPlayer = new Posicion(11, 6);
        disenioMap[6][12] = 2.0;
        map = new Mapa(disenioMap, cosas, enemigos, posPlayer, 0);
        player = new JugadorLolo(map);
        player.setPos(posPlayer);
        map.setPlayer(player);
        Assert.assertFalse(player.moverDerecha());
    }

    @Test
    public void playerChocaConPuertaCerradaIzquierda() {
        Posicion posPlayer = new Posicion(1, 6);
        disenioMap[6][0] = 2.2;
        map = new Mapa(disenioMap, cosas, enemigos, posPlayer, 0);
        player = new JugadorLolo(map);
        player.setPos(posPlayer);
        map.setPlayer(player);
        Assert.assertFalse(player.moverIzquierda());
    }

    @Test
    public void playerAvanzaConPuertaAbiertaArriba() {
        Posicion posPlayer = new Posicion(6, 1);
        disenioMap[0][6] = 2.3;
        map = new Mapa(disenioMap, cosas, enemigos, posPlayer, 0);
        player = new JugadorLolo(map);
        player.setPos(posPlayer);
        map.setPlayer(player);
        map.habilitarPasarNivel();
        Assert.assertTrue(player.moverArriba());
    }

    @Test
    public void playerAvanzaConPuertaAbiertaAbajo() {
        Posicion posPlayer = new Posicion(6, 11);
        disenioMap[12][6] = 2.1;
        map = new Mapa(disenioMap, cosas, enemigos, posPlayer, 0);
        player = new JugadorLolo(map);
        player.setPos(posPlayer);
        map.setPlayer(player);
        map.habilitarPasarNivel();
        Assert.assertTrue(player.moverAbajo());
    }

    @Test
    public void playerAvanzaConPuertaAbiertaDerecha() {
        Posicion posPlayer = new Posicion(11, 6);
        disenioMap[6][12] = 2.0;
        map = new Mapa(disenioMap, cosas, enemigos, posPlayer, 0);
        player = new JugadorLolo(map);
        player.setPos(posPlayer);
        map.setPlayer(player);
        map.habilitarPasarNivel();
        Assert.assertTrue(player.moverDerecha());
    }

    @Test
    public void playerAvanzaConPuertaAbiertaIzquierda() {
        Posicion posPlayer = new Posicion(1, 6);
        disenioMap[6][0] = 2.2;
        map = new Mapa(disenioMap, cosas, enemigos, posPlayer, 0);
        player = new JugadorLolo(map);
        player.setPos(posPlayer);
        map.setPlayer(player);
        map.habilitarPasarNivel();
        Assert.assertTrue(player.moverIzquierda());
    }
}