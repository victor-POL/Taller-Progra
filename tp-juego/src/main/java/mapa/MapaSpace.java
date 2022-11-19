package mapa;

import java.util.Map;

import entidad.Cosa;
import entidad.Enemigo;
import javafx.scene.canvas.Canvas;
import piso.PisoHandler;
import utiles.Posicion;

public class MapaSpace extends MapaGenerico {
    private final int width;
    private final int height;

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
        return true;
    }

    @Override
    public void update(double deltaTime) {

    }

    public Posicion getPosicionInicial() {
        return this.posJugador;
    }

}
