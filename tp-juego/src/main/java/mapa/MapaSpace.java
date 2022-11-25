package mapa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import entidad.Cosa;
import entidad.Enemigo;
import entidad.JugadorLolo;
import entidad.JugadorSpace;
import javafx.scene.canvas.Canvas;
import piso.PisoHandler;
import utiles.Posicion;

public class MapaSpace extends MapaGenerico {
    private final int width;
    private final int height;
    private JugadorSpace player;

    public MapaSpace(Map<Posicion, Cosa> cosas, Map<Posicion, Enemigo> enemies, Posicion posInicialJugador) {
        super(16, 45);

        this.width = this.TILE_SIZE;
        this.height = this.TILE_SIZE;

        PisoHandler pisoHandler = new PisoHandler();
        canvas = new Canvas(limite * width, limite * height);

        for (int i = 0; i < limite; i++) {
            for (int j = 0; j < limite; j++) {
                pisos[i][j] = pisoHandler.getPisoByPosition(69);
            }
        }

        this.cosas = cosas;
        this.enemigos = enemies;
        this.posJugador = posInicialJugador;

        this.drawMap();

    }

    @Override
    public boolean puedoPasar(int x, int y) {
        if (x < pisos.length && x >= 0 && y >= 0 && y < pisos[0].length && cosas.get(new Posicion(x, y)) == null) {
            return true;
        }
        return false;
    }

    @Override
    public void update(double deltaTime) {
        drawMap();
    }

    public Posicion getPosicionInicial() {
        return this.posJugador;
    }

    public void setPlayer(JugadorSpace player) {
        this.player = player;
    }

    public JugadorSpace getPlayer() {
        return this.player;
    }

    public int getTileSize() {
        return this.TILE_SIZE;
    }
}
