package items;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import entidad.Cosa;
import entidad.Enemigo;
import entidad.Jugador;
import javafx.embed.swing.JFXPanel;
import mapa.Mapa;
import utiles.Posicion;

public class ItemRecogibleTests {

    Mapa map;
    double[][] disenioMap = { { 1.0, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.2 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 6 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.6, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.4 }, };
    Jugador player;

    Cosa cosaRecogible;
    Map<Posicion, Cosa> cosas;
    JFXPanel panel = new JFXPanel();

    Enemigo enemy;
    Map<Posicion, Enemigo> enemigos;

    @Before
    public void setUp() {
        cosas = new HashMap<Posicion, Cosa>();
        enemigos = new HashMap<Posicion, Enemigo>();

        Posicion posInicialJugador = new Posicion(6, 5.5);
        Posicion posCosaRecogible = new Posicion(6, 6); // X, Y
        Posicion posEnemy = new Posicion(2, 3);

        map = new Mapa(disenioMap, cosas, enemigos, posInicialJugador, 1);
        enemy = new Enemigo(1, posEnemy, map, utiles.Constantes.ABAJO);
        cosaRecogible = new Cosa(0, posCosaRecogible, map, true, false, "ITEM");
        cosas.put(cosaRecogible.getPos(), cosaRecogible);
        player = new Jugador(map);
        enemigos.put(enemy.getPos(), enemy);

        map.setPlayer(player);
    }

    @Test
    public void playerRecogeItemRecogibleAbajo() {
        player.setPos(new Posicion(6, 5.5));
        player.setOrientacion(utiles.Constantes.ABAJO);

        player.moverAbajo();

        Assert.assertEquals(1, player.getInventario().size());
    }

    @Test
    public void playerRecogeItemRecogibleArriba() {
        player.setPos(new Posicion(6, 6.5));
        player.setOrientacion(utiles.Constantes.ARRIBA);
        player.moverArriba();

        Assert.assertEquals(1, player.getInventario().size());
    }

    @Test
    public void playerRecogeItemRecogibleIzq() {
        player.setPos(new Posicion(6.5, 6));
        player.setOrientacion(utiles.Constantes.IZQ);
        player.moverIzquierda();

        Assert.assertEquals(1, player.getInventario().size());
    }

    @Test
    public void playerRecogeItemRecogibleDer() {
        player.setPos(new Posicion(5.5, 6));
        player.setOrientacion(utiles.Constantes.DER);
        player.moverDerecha();

        Assert.assertEquals(1, player.getInventario().size());
    }
}