package mapa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entidad.Bala;
import entidad.Cosa;
import entidad.Enemigo;
import entidad.Jugador;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import piso.Piso;
import piso.PisoHandler;
import utiles.Posicion;

public class Mapa {
    private final int TILE_SIZE = 32;
    
    private double orientacionPuerta;

    private Piso[][] pisos = new Piso[13][13];
    private int limite = pisos.length;

    private Map<Posicion, Cosa> cosas = new HashMap<Posicion, Cosa>();
    private Map<Posicion, Enemigo> enemigos = new HashMap<Posicion, Enemigo>();
    private List<Bala> balas = new ArrayList<Bala>();

    private Jugador player;

    private Posicion posJugador;
    private Posicion posPasarDeNivel;
    private Posicion posItemHabilitaPasarNivel;
    private Cosa itemPasarDeNivel;

    private boolean pasarNivelHabilitado = false;
    private boolean cambie = false;
    private int cantItemsCompletarNivel;

    private final int width = TILE_SIZE;
    private final int height = TILE_SIZE;
    private Canvas canvas;

    // Constructores

    public Mapa(double[][] disenio, Map<Posicion, Cosa> cosas, Map<Posicion, Enemigo> enemies,
            Posicion posInicialJugador,
            int itemsObjetivo) {

        PisoHandler pisoHandler = new PisoHandler();
        canvas = new Canvas(limite * width, limite * height);

        for (int i = 0; i < disenio.length; i++) {
            for (int j = 0; j < disenio[0].length; j++) {
                pisos[i][j] = pisoHandler.getPisoByPosition(disenio[i][j]);

                if ((int)disenio[i][j] == PisoHandler.POSICION_PASAR_NIVEL) {
                    orientacionPuerta = disenio[i][j] - (int)disenio[i][j];
                    posPasarDeNivel = new Posicion(j, i);
                }
                    

                if ((int)disenio[i][j] == PisoHandler.POSICION_ITEM_HABILITAR_PASO_NIVEL)
                    posItemHabilitaPasarNivel = new Posicion(j, i);
            }
        }
        this.cosas = cosas;
        this.enemigos = enemies;
        this.posJugador = posInicialJugador;
        this.cantItemsCompletarNivel = itemsObjetivo;
        this.drawMap();

//        this.itemPasarDeNivel = new Cosa(0, this.posPasarDeNivel, this, true, false, "puerta");
    }

    // Consultas

    public boolean puedoPasar(int x, int y) { // x=Columnas, y=filas
        if (x < pisos.length && x >= 0 && y >= 0 && y < pisos[0].length) {
            return pisos[y][x].esColisionable() == false
                    && (cosas.get(new Posicion(x, y)) == null || cosas.get(new Posicion(x, y)).esRecogible())
                    && enemigos.get(new Posicion(x, y)) == null;
        }

        return false;
    }

    // Getters by position

    public Cosa getByPosition(Posicion p) {
        return cosas.get(p);
    }

    public Enemigo getEnemyByPosition(Posicion p) {
        return enemigos.get(p);
    }

    public Piso getPisoByPosition(int x, int y) {
        return this.pisos[x][y];
    }

    // Modificar mapa

    public void removeCosa(Posicion p) {
        cosas.remove(p);
    }

    public void actualizarCosa(Cosa c) {
        this.cosas.put(c.getPos(), c);
    }

    public boolean sacarEnemigo(Enemigo e) {
        if (enemigos.containsValue(e)) {
            enemigos.remove(e.getPos());
            return true;
        }
        return false;
    }

    public void addBala(Bala b) {
        balas.add(b);
    }

    // Habilitaciones

    public void habilitarItemParaPasarNivel() {
        pisos[(int) posItemHabilitaPasarNivel.getY()][(int) posItemHabilitaPasarNivel.getX()] = new PisoHandler()
                .getPisoByPosition(6.1);
        cosas.put(posItemHabilitaPasarNivel, new Cosa(0, this.posItemHabilitaPasarNivel, this, true, false, "llave"));
        cambie = true;

    }

    public void habilitarPasarNivel() {
        pisos[(int) posPasarDeNivel.getY()][(int) posPasarDeNivel.getX()] = new PisoHandler()
                .getPisoByPosition(3 + orientacionPuerta);
        itemPasarDeNivel = new Cosa(0, posPasarDeNivel, this, true, false, "item_pasar_de_nivel");
        cosas.put(posPasarDeNivel, itemPasarDeNivel);
        pasarNivelHabilitado = true;
        cambie = true;
    }

    // Getters

    public int getLimite() {
        return this.limite;
    }

    public int getItemsObjetivo() {
        return this.cantItemsCompletarNivel;
    }

    public Posicion getPosPuerta() {
        return this.posPasarDeNivel;
    }

    public Posicion getPosInicialJugador() {
        return this.posJugador;
    }

    public Posicion getPosJugador() {
        return this.posJugador;
    }

    public boolean getPuertaHabilitada() {
        return this.pasarNivelHabilitado;
    }

    public Map<Posicion, Cosa> getCosas() {
        return this.cosas;
    }

    public Map<Posicion, Enemigo> getEnemigos() {
        return this.enemigos;
    }

    public List<Bala> getBalas() {
        return this.balas;
    }

    public Node getRender() {
        return canvas;
    }

    public Jugador getPlayer() {
        return this.player;
    }

    // Setters

    public void setPos(Posicion p) {
        posJugador = p;
    }

    public void setPlayer(Jugador j) {
        player = j;
    }

    // JavaFX
    private void drawMap() {
        GraphicsContext context = canvas.getGraphicsContext2D();

        for (int i = 0; i < limite; i++) {
            for (int j = 0; j < limite; j++) {
                context.drawImage(pisos[j][i].getImage(), i * TILE_SIZE, j * TILE_SIZE);
            }
        }

    }

    public void update(double deltaTime) {
        if (cambie) {
            drawMap();
            cambie = false;
        }

    }
}