package balas;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import animation.Control;
import entidad.Bala;
import entidad.Cosa;
import entidad.Enemigo;
import entidad.Jugador;
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
            { 1.7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.3 },
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
    Control control;
    JFXPanel panel = new JFXPanel();

    @Before
    public void setUp() {
        cosas = new HashMap<Posicion, Cosa>();
        enemigos = new HashMap<Posicion, Enemigo>();

        Posicion posJugador = new Posicion(6, 5);
        Posicion posCosaNoRecogible = new Posicion(6, 4.5);
        Posicion posCosaRecogible = new Posicion(6, 4.5);
        Posicion posEnemy = new Posicion(6, 4.5);

        map = new Mapa(disenioMap, cosas, enemigos, posJugador, 0);
        enemy = new Enemigo(1, posEnemy, map);
        cosaNoRecogible = new Cosa(posCosaNoRecogible, map, false, false, "ARBOL");
        cosaRecogible = new Cosa(posCosaRecogible, map, true, false, "COMIDA");
        player = new Jugador(map);
        player.setCantBalas(1);

        map.setPlayer(player);
    }

    // TODO: MODIFICAR MÉTODO Muerto() POR muerto() CUANDO SE MODIFIQUE SU NOMBRE EN
    // ENEMIGO
    @Test
    public void mataEnemigoArriba() {
        enemigos.put(enemy.getPos(), enemy);

        player.setOrientacion(utiles.Constantes.ARRIBA);

        Assert.assertNotNull(player.disparar());

        map.getBalas().get(0).mover();

        Assert.assertTrue(map.getEnemyByPosition(enemy.getPos()).Muerto());
    }

    @Test
    public void mataEnemigoAbajo() {
        enemy.setPos(new Posicion(6, 5.5));
        enemigos.put(enemy.getPos(), enemy);

        player.setOrientacion(utiles.Constantes.ABAJO);

        Assert.assertNotNull(player.disparar());

        map.getBalas().get(0).mover();

        Assert.assertTrue(map.getEnemyByPosition(enemy.getPos()).Muerto());
    }

    @Test
    public void mataEnemigoDerecha() {
        enemy.setPos(new Posicion(6.5, 5));
        enemigos.put(enemy.getPos(), enemy);

        Assert.assertNotNull(player.disparar());

        map.getBalas().get(0).mover();

        Assert.assertTrue(map.getEnemyByPosition(enemy.getPos()).Muerto());
    }

    @Test
    public void mataEnemigoIzquierda() {
        enemy.setPos(new Posicion(5.5, 5));
        enemigos.put(enemy.getPos(), enemy);

        player.setOrientacion(utiles.Constantes.IZQ);

        Assert.assertNotNull(player.disparar());

        map.getBalas().get(0).mover();

        Assert.assertTrue(map.getEnemyByPosition(enemy.getPos()).Muerto());
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
        cosas.put(cosaNoRecogible.getPos(), cosaNoRecogible);

        player.setOrientacion(utiles.Constantes.ARRIBA);

        Assert.assertNotNull(player.disparar());

        Assert.assertFalse(map.getBalas().get(0).mover());
    }

    @Test
    public void balaChocaConCosaNoRecogibleAbajo() {
        cosaNoRecogible.setPos(new Posicion(6, 5.5));
        cosas.put(cosaNoRecogible.getPos(), cosaNoRecogible);

        player.setOrientacion(utiles.Constantes.ABAJO);

        Assert.assertNotNull(player.disparar());

        Assert.assertFalse(map.getBalas().get(0).mover());
    }

    @Test
    public void balaChocaConCosaNoRecogibleDerecha() {
        cosaNoRecogible.setPos(new Posicion(6.5, 5));
        cosas.put(cosaNoRecogible.getPos(), cosaNoRecogible);

        Assert.assertNotNull(player.disparar());

        Assert.assertFalse(map.getBalas().get(0).mover());
    }

    @Test
    public void balaChocaConCosaNoRecogibleIzquierda() {
        cosaNoRecogible.setPos(new Posicion(5.5, 5));
        cosas.put(cosaNoRecogible.getPos(), cosaNoRecogible);

        player.setOrientacion(utiles.Constantes.IZQ);

        Assert.assertNotNull(player.disparar());

        Assert.assertFalse(map.getBalas().get(0).mover());
    }

    @Test
    public void balaChocaConCosaRecogibleArriba() {
        cosas.put(cosaRecogible.getPos(), cosaRecogible);

        player.setOrientacion(utiles.Constantes.ARRIBA);

        Assert.assertNotNull(player.disparar());

        Assert.assertTrue(map.getBalas().get(0).mover());
    }

    @Test
    public void balaChocaConCosaRecogibleAbajo() {
        cosaRecogible.setPos(new Posicion(6, 5.5));
        cosas.put(cosaRecogible.getPos(), cosaRecogible);

        player.setOrientacion(utiles.Constantes.ABAJO);

        Assert.assertNotNull(player.disparar());

        Assert.assertTrue(map.getBalas().get(0).mover());
    }

    @Test
    public void balaChocaConCosaRecogibleDerecha() {
        cosaRecogible.setPos(new Posicion(6.5, 5));
        cosas.put(cosaRecogible.getPos(), cosaRecogible);

        Assert.assertNotNull(player.disparar());

        Assert.assertTrue(map.getBalas().get(0).mover());
    }

    @Test
    public void balaChocaConCosaRecogibleIzquierda() {
        cosaRecogible.setPos(new Posicion(5.5, 5));
        cosas.put(cosaRecogible.getPos(), cosaRecogible);

        player.setOrientacion(utiles.Constantes.IZQ);

        Assert.assertNotNull(player.disparar());

        Assert.assertTrue(map.getBalas().get(0).mover());
    }
    
    @Test
    public void balaMataPlayerArriba() {
        enemy.setPos(new Posicion(6, 5.5));
        enemigos.put(enemy.getPos(), enemy);
        
        Bala bala = enemy.disparar(utiles.Constantes.ARRIBA);

        Assert.assertNotNull(bala);

        //TODO: AGREGAR BALA DEL ENEMIGO AL MAPA CON MÉTODO AddBala()
        
        bala.mover();
        
        Assert.assertTrue(player.isDead());
    }
    
    @Test
    public void balaMataPlayerAbajo() {
        enemy.setPos(new Posicion(6, 4.5));
        enemigos.put(enemy.getPos(), enemy);
        
        Bala bala = enemy.disparar(utiles.Constantes.ABAJO);

        Assert.assertNotNull(bala);
        
        bala.mover();
        
        Assert.assertTrue(player.isDead());
    }
    
    @Test
    public void balaMataPlayerDerecha() {
        enemy.setPos(new Posicion(5.5, 5));
        enemigos.put(enemy.getPos(), enemy);
        
        Bala bala = enemy.disparar(utiles.Constantes.DER);

        Assert.assertNotNull(bala);
        
        bala.mover();
        
        Assert.assertTrue(player.isDead());
    }
    
    @Test
    public void balaMataPlayerIzquierda() {
        enemy.setPos(new Posicion(6.5, 5));
        enemigos.put(enemy.getPos(), enemy);
        
        Bala bala = enemy.disparar(utiles.Constantes.IZQ);

        Assert.assertNotNull(bala);
        
        bala.mover();
        
        Assert.assertTrue(player.isDead());
    }
}
