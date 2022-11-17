package mapa;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import entidad.Bala;
import entidad.Cosa;
import entidad.Enemigo;
import entidad.Jugador;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import piso.Piso;
import utiles.Posicion;

public class MapaGenerico {
    // Atributos
    protected final int TILE_SIZE;
    protected Piso[][] pisos;
    protected int limite;
    protected Map<Posicion, Cosa> cosas = new ConcurrentHashMap<Posicion, Cosa>();
    protected Map<Posicion, Enemigo> enemigos = new ConcurrentHashMap<Posicion, Enemigo>();
    protected List<Bala> balas = new CopyOnWriteArrayList<Bala>();
    protected Jugador player;
    protected Posicion posJugador;
    protected Canvas canvas;
    
    // Constructores
    public MapaGenerico(int tile_size, int tam_matriz) {
        this.TILE_SIZE = tile_size;
        this.pisos = new Piso[tam_matriz][tam_matriz];
        this.limite = this.pisos.length;
    }
    
    // Metodos
    public Cosa getCosaByPosition(Posicion p) {
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
    // Getters

    public int getLimite() {
        return this.limite;
    }

    public Posicion getPosInicialJugador() {
        return this.posJugador;
    }

    public Posicion getPosJugador() {
        return this.posJugador;
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
    protected void drawMap() {
        GraphicsContext context = canvas.getGraphicsContext2D();

        for (int i = 0; i < this.limite; i++) {
            for (int j = 0; j < this.limite; j++) {
                context.drawImage(pisos[j][i].getImage(), i * TILE_SIZE, j * TILE_SIZE);
            }
        }

    }

}
