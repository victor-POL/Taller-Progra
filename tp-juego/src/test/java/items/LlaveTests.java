package items;

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

public class LlaveTests {
    Mapa map;
    double[][] disenioMap = {
            { 1.0, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.1, 1.2 },
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
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
            { 1.6, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.5, 1.4 }, };
    JugadorLolo player;
    Posicion posCofre = new Posicion(6, 5);

    Cosa cosaRecogible;
    Map<Posicion, Enemigo> enemigos;
    Map<Posicion, Cosa> cosas;
    JFXPanel panel = new JFXPanel();

    @Before
    public void setUp() {
        cosas = new HashMap<Posicion, Cosa>();
        enemigos = new HashMap<Posicion, Enemigo>();

        Posicion posInicialJugador = new Posicion(4, 5.5);
        Posicion posCosaRecogible = new Posicion(4, 5);

        map = new Mapa(disenioMap, cosas, enemigos, posInicialJugador, 1);

        cosaRecogible = new Cosa(0, posCosaRecogible, map, true, false, "municion");
        cosas.put(cosaRecogible.getPos(), cosaRecogible);

        player = new JugadorLolo(map);

        map.setPlayer(player);
    }

    @Test
    public void PlayerAgarraUltimoItemArriba_SeHabilitaLlave() {
        player.moverArriba();

        Assert.assertTrue(map.getCosas().get(posCofre).clasificacion == "llave");
    }

    @Test
    public void PlayerAgarraUltimoItemAbajo_SeHabilitaLlave() {
        player.setPos(new Posicion(4, 4.5));
        player.moverAbajo();

        Assert.assertTrue(map.getCosas().get(posCofre).clasificacion == "llave");
    }

    @Test
    public void PlayerAgarraUltimoItemDerecha_SeHabilitaLlave() {
        player.setPos(new Posicion(3.5, 5));
        player.moverDerecha();

        Assert.assertTrue(map.getCosas().get(posCofre).clasificacion == "llave");
    }

    @Test
    public void PlayerAgarraUltimoItemIzquierda_SeHabilitaLlave() {
        player.setPos(new Posicion(4.5, 5));
        player.moverIzquierda();

        Assert.assertTrue(map.getCosas().get(posCofre).clasificacion == "llave");
    }

    @Test
    public void PlayerAgarraLlaveArriba() {
        player.setPos(new Posicion(6, 5.5));
        map.habilitarItemParaPasarNivel();

        player.moverArriba();

        Assert.assertEquals(1, player.getInventario().size());
    }

    @Test
    public void PlayerAgarraLlaveAbajo() {
        player.setPos(new Posicion(6, 4.5));
        map.habilitarItemParaPasarNivel();

        player.moverAbajo();

        Assert.assertEquals(1, player.getInventario().size());
    }

    @Test
    public void PlayerAgarraLlaveDerecha() {
        player.setPos(new Posicion(5.5, 5));
        map.habilitarItemParaPasarNivel();

        player.moverDerecha();

        Assert.assertEquals(1, player.getInventario().size());
    }

    @Test
    public void PlayerAgarraLlaveIzquierda() {
        player.setPos(new Posicion(6.5, 5));
        map.habilitarItemParaPasarNivel();

        player.moverIzquierda();

        Assert.assertEquals(1, player.getInventario().size());
    }
}
