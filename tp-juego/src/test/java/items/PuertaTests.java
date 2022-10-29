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

public class PuertaTests {
    Mapa map;
    double[][] disenioMap = {
            { 1.0, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.2 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.6, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.4 }, };
    Jugador player;

    Cosa cosaRecogible, cosaItemLlave;
    Map<Posicion, Cosa> cosas;
    JFXPanel panel = new JFXPanel();

    Enemigo enemy;
    Map<Posicion, Enemigo> enemigos;

    @Before
    public void setUp() {
        cosas = new HashMap<Posicion, Cosa>();
        enemigos = new HashMap<Posicion, Enemigo>();

        Posicion posInicialJugador = new Posicion(6, 5);
        Posicion posEnemy = new Posicion(2, 3);

        map = new Mapa(disenioMap, cosas, enemigos, posInicialJugador, 0);
        enemy = new Enemigo(1, posEnemy, map, utiles.Constantes.ABAJO);

        player = new Jugador(map);
        enemigos.put(enemy.getPos(), enemy);

        map.setPlayer(player);
    }

    @Test
    public void puertaEsAbiertaAbajo() {
        Posicion posPlayer = new Posicion(6, 5.5);
        disenioMap[0][6] = 2.3;
        map = new Mapa(disenioMap, cosas, enemigos, posPlayer, 0);
        player = new Jugador(map);
        player.setPos(posPlayer);
        map.setPlayer(player);

        map.habilitarItemParaPasarNivel(); // Habilita el cofre

        player.moverAbajo(); // Agarra la llave
        Assert.assertTrue(map.getPuertaHabilitada());
    }

    @Test
    public void puertaEsAbiertaArriba() {
        Posicion posPlayer = new Posicion(6, 6.5);
        disenioMap[0][6] = 2.3;
        map = new Mapa(disenioMap, cosas, enemigos, posPlayer, 0);
        player = new Jugador(map);
        player.setPos(posPlayer);
        map.setPlayer(player);

        map.habilitarItemParaPasarNivel(); // Habilita el cofre

        player.moverArriba(); // Agarra la llave
        Assert.assertTrue(map.getPuertaHabilitada());
    }

    @Test
    public void puertaEsAbiertaIzq() {
        Posicion posPlayer = new Posicion(6.5, 6);
        disenioMap[0][6] = 2.3;
        map = new Mapa(disenioMap, cosas, enemigos, posPlayer, 0);
        player = new Jugador(map);
        player.setPos(posPlayer);
        map.setPlayer(player);

        map.habilitarItemParaPasarNivel(); // Habilita el cofre

        player.moverIzquierda(); // Agarra la llave
        Assert.assertTrue(map.getPuertaHabilitada());
    }

    @Test
    public void puertaEsAbiertaDer() {
        Posicion posPlayer = new Posicion(5.5, 6);
        disenioMap[0][6] = 2.3;
        map = new Mapa(disenioMap, cosas, enemigos, posPlayer, 0);
        player = new Jugador(map);
        player.setPos(posPlayer);
        map.setPlayer(player);

        map.habilitarItemParaPasarNivel(); // Habilita el cofre

        player.moverDerecha(); // Agarra la llave
        Assert.assertTrue(map.getPuertaHabilitada());
    }

    @Test
    public void playerAgarraItemPasarDeNivelArriba() {
        Posicion posPlayer = new Posicion(6, 0.5);
        disenioMap[0][6] = 2.3;

        map = new Mapa(disenioMap, cosas, enemigos, posPlayer, 0);
        player = new Jugador(map);
        player.setPos(posPlayer);
        map.setPlayer(player);

        map.habilitarPasarNivel();

        player.moverArriba();

        Assert.assertTrue(player.completoNivel);
    }

    @Test
    public void playerAgarraItemPasarDeNivelAbajo() {
        Posicion posPlayer = new Posicion(6, 11.5);
        disenioMap[12][6] = 2.1;

        map = new Mapa(disenioMap, cosas, enemigos, posPlayer, 0);
        player = new Jugador(map);
        player.setPos(posPlayer);
        map.setPlayer(player);

        map.habilitarPasarNivel();

        player.moverAbajo();

        Assert.assertTrue(player.completoNivel);
    }

    @Test
    public void playerAgarraItemPasarDeNivelDer() {
        Posicion posPlayer = new Posicion(11.5, 6);
        disenioMap[6][12] = 2.0;

        map = new Mapa(disenioMap, cosas, enemigos, posPlayer, 0);
        player = new Jugador(map);
        player.setPos(posPlayer);
        map.setPlayer(player);

        map.habilitarPasarNivel();

        player.moverDerecha();

        Assert.assertTrue(player.completoNivel);
    }

    @Test
    public void playerAgarraItemPasarDeNivelIzq() {
        Posicion posPlayer = new Posicion(0.5, 6);
        disenioMap[6][0] = 2.2;

        map = new Mapa(disenioMap, cosas, enemigos, posPlayer, 0);
        player = new Jugador(map);
        player.setPos(posPlayer);
        map.setPlayer(player);

        map.habilitarPasarNivel();

        player.moverIzquierda();

        Assert.assertTrue(player.completoNivel);
    }
}