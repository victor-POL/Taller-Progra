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

public class MovimientosObstaculoEmpujableTests {

    Mapa map;
    double[][] disenioMap = { { 1.0, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.2 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 30, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 20, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 40, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
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

        Posicion posInicialJugador = new Posicion(6, 6);
        Posicion posCosaEmpujable = new Posicion(6, 5);
        Posicion posCosaEmpujable2 = new Posicion(3, 6);
        Posicion posCosaRecogible = new Posicion(3, 9);
        Posicion posEnemy = new Posicion(3, 3);

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
    public void playerMueveCosaEmpujableArriba() {
        Posicion posFinalCosa = new Posicion(6, 4);
        player.moverArriba();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void playerMueveCosaEmpujableAbajo() {
        Posicion posFinalCosa = new Posicion(6, 6);
        player.setPos(new Posicion(6, 4));
        player.moverAbajo();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void playerMueveCosaEmpujableDerecha() {
        Posicion posFinalCosa = new Posicion(7, 5);
        player.setPos(new Posicion(5, 5));
        player.moverDerecha();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void playerMueveCosaEmpujableIzquierda() {
        Posicion posFinalCosa = new Posicion(5, 5);
        player.setPos(new Posicion(7, 5));
        player.moverIzquierda();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConParedArriba() {
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(1, 1));
        Posicion posFinalCosa = new Posicion(1, 1);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(1, 2));
        player.moverArriba();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConParedAbajo() {
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(1, 11));
        Posicion posFinalCosa = new Posicion(1, 11);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(1, 10));
        player.moverAbajo();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConParedDerecha() {
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(11, 11));
        Posicion posFinalCosa = new Posicion(11, 11);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(10, 11));
        player.moverDerecha();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConParedIzquierda() {
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(1, 11));
        Posicion posFinalCosa = new Posicion(1, 11);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(2, 11));
        player.moverIzquierda();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConRocaArriba() {
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(6, 8));
        Posicion posFinalCosa = new Posicion(6, 8);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(6, 9));
        player.moverArriba();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConRocaAbajo() {
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(6, 6));
        Posicion posFinalCosa = new Posicion(6, 6);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(6, 5));
        player.moverAbajo();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConRocaDerecha() {
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(5, 7));
        Posicion posFinalCosa = new Posicion(5, 7);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(4, 7));
        player.moverDerecha();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConRocaIzquierda() {
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(7, 7));
        Posicion posFinalCosa = new Posicion(7, 7);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(8, 7));
        player.moverIzquierda();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConArbolArriba() {
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(6, 4));
        Posicion posFinalCosa = new Posicion(6, 4);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(6, 5));
        player.moverArriba();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConArbolAbajo() {
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(6, 2));
        Posicion posFinalCosa = new Posicion(6, 2);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(6, 1));
        player.moverAbajo();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConArbolDerecha() {
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(5, 3));
        Posicion posFinalCosa = new Posicion(5, 3);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(4, 3));
        player.moverDerecha();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConArbolIzquierda() {
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(7, 3));
        Posicion posFinalCosa = new Posicion(7, 3);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(8, 3));
        player.moverIzquierda();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConAguaArriba() {
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(9, 10));
        Posicion posFinalCosa = new Posicion(9, 10);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(9, 11));
        player.moverArriba();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConAguaAbajo() {
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(9, 8));
        Posicion posFinalCosa = new Posicion(9, 8);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(9, 7));
        player.moverAbajo();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConAguaDerecha() {
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(8, 9));
        Posicion posFinalCosa = new Posicion(8, 9);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(7, 9));
        player.moverDerecha();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConAguaIzquierda() {
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(10, 9));
        Posicion posFinalCosa = new Posicion(10, 9);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(11, 9));
        player.moverIzquierda();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConEnemigoArriba() {
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(3, 4));
        Posicion posFinalCosa = new Posicion(3, 4);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(3, 5));
        player.moverArriba();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConEnemigoAbajo() {
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(3, 2));
        Posicion posFinalCosa = new Posicion(3, 2);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(3, 1));
        player.moverAbajo();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConEnemigoDerecha() {
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(2, 3));
        Posicion posFinalCosa = new Posicion(2, 3);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(1, 3));
        player.moverDerecha();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConEnemigoIzquierda() {
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(4, 3));
        Posicion posFinalCosa = new Posicion(4, 3);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(5, 3));
        player.moverIzquierda();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConCosaRecogibleArriba() {
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(3, 10));
        Posicion posFinalCosa = new Posicion(3, 10);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(3, 11));
        player.moverArriba();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConCosaRecogibleAbajo() {
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(3, 8));
        Posicion posFinalCosa = new Posicion(3, 8);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(3, 7));
        player.moverAbajo();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConCosaRecogibleDerecha() {
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(2, 9));
        Posicion posFinalCosa = new Posicion(2, 9);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(1, 9));
        player.moverDerecha();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConCosaRecogibleIzquierda() {
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(4, 9));
        Posicion posFinalCosa = new Posicion(4, 9);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(5, 9));
        player.moverIzquierda();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConCosaEmpujable2Arriba() {
        map.actualizarCosa(cosaEmpujable2);
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(3, 7));
        Posicion posFinalCosa = new Posicion(3, 7);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(3, 8));
        player.moverArriba();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConCosaEmpujable2Abajo() {
        map.actualizarCosa(cosaEmpujable2);
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(3, 5));
        Posicion posFinalCosa = new Posicion(3, 5);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(3, 4));
        player.moverAbajo();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConCosaEmpujable2Derecha() {
        map.actualizarCosa(cosaEmpujable2);
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(2, 6));
        Posicion posFinalCosa = new Posicion(2, 6);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(1, 6));
        player.moverDerecha();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConCosaEmpujable2Izquierda() {
        map.actualizarCosa(cosaEmpujable2);
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(4, 6));
        Posicion posFinalCosa = new Posicion(4, 6);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(5, 6));
        player.moverIzquierda();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConCofreCerradoArriba() {
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(9, 6));
        Posicion posFinalCosa = new Posicion(9, 6);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(9, 7));
        player.moverArriba();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConCofreCerradoAbajo() {
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(9, 4));
        Posicion posFinalCosa = new Posicion(9, 4);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(9, 3));
        player.moverAbajo();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConCofreCerradoDerecha() {
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(8, 5));
        Posicion posFinalCosa = new Posicion(8, 5);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(7, 5));
        player.moverDerecha();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConCofreCerradoIzquierda() {
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(10, 5));
        Posicion posFinalCosa = new Posicion(10, 5);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(11, 5));
        player.moverIzquierda();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConCofreAbiertoArriba() {
        map.habilitarItemParaPasarNivel();
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(9, 6));
        Posicion posFinalCosa = new Posicion(9, 6);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(9, 7));
        player.moverArriba();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConCofreAbiertoAbajo() {
        map.habilitarItemParaPasarNivel();
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(9, 4));
        Posicion posFinalCosa = new Posicion(9, 4);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(9, 3));
        player.moverAbajo();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConCofreAbiertoDerecha() {
        map.habilitarItemParaPasarNivel();
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(8, 5));
        Posicion posFinalCosa = new Posicion(8, 5);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(7, 5));
        player.moverDerecha();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConCofreAbiertoIzquierda() {
        map.habilitarItemParaPasarNivel();
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(10, 5));
        Posicion posFinalCosa = new Posicion(10, 5);
        map.actualizarCosa(cosaEmpujable);
        player.setPos(new Posicion(11, 5));
        player.moverIzquierda();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConPuertaCerradaArriba() {
        Posicion posPlayer = new Posicion(6, 2);
        disenioMap[0][6] = 2.3;
        map = new Mapa(disenioMap, cosas, enemigos, posPlayer, 0);
        player = new JugadorLolo(map);
        player.setPos(posPlayer);
        map.setPlayer(player);
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(6, 1));
        Posicion posFinalCosa = new Posicion(6, 1);
        map.actualizarCosa(cosaEmpujable);
        player.moverArriba();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConPuertaCerradaAbajo() {
        Posicion posPlayer = new Posicion(6, 10);
        disenioMap[12][6] = 2.1;
        map = new Mapa(disenioMap, cosas, enemigos, posPlayer, 0);
        player = new JugadorLolo(map);
        player.setPos(posPlayer);
        map.setPlayer(player);
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(6, 11));
        Posicion posFinalCosa = new Posicion(6, 11);
        map.actualizarCosa(cosaEmpujable);
        player.moverAbajo();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConPuertaCerradaDerecha() {
        Posicion posPlayer = new Posicion(10, 6);
        disenioMap[6][12] = 2.0;
        map = new Mapa(disenioMap, cosas, enemigos, posPlayer, 0);
        player = new JugadorLolo(map);
        player.setPos(posPlayer);
        map.setPlayer(player);
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(11, 6));
        Posicion posFinalCosa = new Posicion(11, 6);
        map.actualizarCosa(cosaEmpujable);
        player.moverDerecha();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConPuertaCerradaIzquierda() {
        Posicion posPlayer = new Posicion(2, 6);
        disenioMap[6][0] = 2.2;
        map = new Mapa(disenioMap, cosas, enemigos, posPlayer, 0);
        player = new JugadorLolo(map);
        player.setPos(posPlayer);
        map.setPlayer(player);
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(1, 6));
        Posicion posFinalCosa = new Posicion(1, 6);
        map.actualizarCosa(cosaEmpujable);
        player.moverIzquierda();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConPuertaAbiertaArriba() {
        Posicion posPlayer = new Posicion(6, 2);
        disenioMap[0][6] = 2.3;
        map = new Mapa(disenioMap, cosas, enemigos, posPlayer, 0);
        player = new JugadorLolo(map);
        player.setPos(posPlayer);
        map.setPlayer(player);
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(6, 1));
        Posicion posFinalCosa = new Posicion(6, 1);
        map.actualizarCosa(cosaEmpujable);
        map.habilitarPasarNivel();
        player.moverArriba();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConPuertaAbiertaAbajo() {
        Posicion posPlayer = new Posicion(6, 10);
        disenioMap[12][6] = 2.1;
        map = new Mapa(disenioMap, cosas, enemigos, posPlayer, 0);
        player = new JugadorLolo(map);
        player.setPos(posPlayer);
        map.setPlayer(player);
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(6, 11));
        Posicion posFinalCosa = new Posicion(6, 11);
        map.actualizarCosa(cosaEmpujable);
        map.habilitarPasarNivel();
        player.moverAbajo();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConPuertaAbiertaDerecha() {
        Posicion posPlayer = new Posicion(10, 6);
        disenioMap[6][12] = 2.0;
        map = new Mapa(disenioMap, cosas, enemigos, posPlayer, 0);
        player = new JugadorLolo(map);
        player.setPos(posPlayer);
        map.setPlayer(player);
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(11, 6));
        Posicion posFinalCosa = new Posicion(11, 6);
        map.actualizarCosa(cosaEmpujable);
        map.habilitarPasarNivel();
        player.moverDerecha();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }

    @Test
    public void cosaEmpujableChocaConPuertaAbiertaIzquierda() {
        Posicion posPlayer = new Posicion(2, 6);
        disenioMap[6][0] = 2.2;
        map = new Mapa(disenioMap, cosas, enemigos, posPlayer, 0);
        player = new JugadorLolo(map);
        player.setPos(posPlayer);
        map.setPlayer(player);
        map.removeCosa(cosaEmpujable.getPos());
        cosaEmpujable.setPos(new Posicion(1, 6));
        Posicion posFinalCosa = new Posicion(1, 6);
        map.actualizarCosa(cosaEmpujable);
        map.habilitarPasarNivel();
        player.moverIzquierda();
        Assert.assertEquals(posFinalCosa, cosaEmpujable.getPos());
    }
}