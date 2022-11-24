package mapa;

import java.util.Map;

import entidad.Cosa;
import entidad.Enemigo;
import entidad.JugadorLolo;
import javafx.scene.canvas.Canvas;
import piso.PisoHandler;
import utiles.Posicion;

public class Mapa extends MapaGenerico implements MapaInterfaz {
    private double orientacionPuerta;
    private Posicion posPasarDeNivel;
    private Posicion posItemHabilitaPasarNivel;
    private Cosa itemPasarDeNivel;

    private boolean pasarNivelHabilitado = false;
    private boolean sufrioModificacion = false;
    private int cantItemsCompletarNivel;

    private JugadorLolo player;

    private final int width;
    private final int height;

    // Constructores

    public Mapa(
            double[][] disenio, Map<Posicion, Cosa> cosas, Map<Posicion, Enemigo> enemies,
            Posicion posInicialJugador,
            int itemsObjetivo) {

        super(32, 13);

        this.width = this.TILE_SIZE;
        this.height = this.TILE_SIZE;

        PisoHandler pisoHandler = new PisoHandler();
        canvas = new Canvas(limite * width, limite * height);

        for (int i = 0; i < disenio.length; i++) {
            for (int j = 0; j < disenio[0].length; j++) {
                pisos[i][j] = pisoHandler.getPisoByPosition(disenio[i][j]);

                if ((int) disenio[i][j] == PisoHandler.POSICION_PASAR_NIVEL) {
                    orientacionPuerta = disenio[i][j] - (int) disenio[i][j];
                    posPasarDeNivel = new Posicion(j, i);
                }

                if ((int) disenio[i][j] == PisoHandler.POSICION_ITEM_HABILITAR_PASO_NIVEL)
                    posItemHabilitaPasarNivel = new Posicion(j, i);
            }
        }
        this.cosas = cosas;
        this.enemigos = enemies;
        this.posJugador = posInicialJugador;
        this.cantItemsCompletarNivel = itemsObjetivo;
        this.drawMap();

    }

    // Consultas

    @Override
    public boolean puedoPasar(int x, int y) { // x=Columnas, y=filas
        if (x < pisos.length && x >= 0 && y >= 0 && y < pisos[0].length) {
            return pisos[y][x].esColisionable() == false
                    && (cosas.get(new Posicion(x, y)) == null || cosas.get(new Posicion(x, y)).esRecogible())
                    && enemigos.get(new Posicion(x, y)) == null;
        }

        return false;
    }

    // Habilitaciones

    public void habilitarItemParaPasarNivel() {
        pisos[(int) posItemHabilitaPasarNivel.getY()][(int) posItemHabilitaPasarNivel.getX()] = new PisoHandler()
                .getPisoByPosition(6.1);
        cosas.put(posItemHabilitaPasarNivel, new Cosa(0, this.posItemHabilitaPasarNivel, this, true, false, "llave"));
        sufrioModificacion = true;

    }

    public void habilitarPasarNivel() {
        pisos[(int) posPasarDeNivel.getY()][(int) posPasarDeNivel.getX()] = new PisoHandler()
                .getPisoByPosition(3 + orientacionPuerta);
        itemPasarDeNivel = new Cosa(0, posPasarDeNivel, this, true, false, "item_pasar_de_nivel");
        cosas.put(posPasarDeNivel, itemPasarDeNivel);
        pasarNivelHabilitado = true;
        sufrioModificacion = true;
    }

    // Getters

    public int getItemsObjetivo() {
        return this.cantItemsCompletarNivel;
    }

    public Posicion getPosPuerta() {
        return this.posPasarDeNivel;
    }

    public boolean getPuertaHabilitada() {
        return this.pasarNivelHabilitado;
    }

    public JugadorLolo getPlayer() {
        return this.player;
    }

    public void setPlayer(JugadorLolo player) {
        this.player = player;
    }

    public int getTileSize() {
        return this.TILE_SIZE;
    }

    @Override
    public void update(double deltaTime) {
        if (sufrioModificacion) {
            drawMap();
            sufrioModificacion = false;
        }

    }
}