package niveles;

import animation.Control;
import entidad.JugadorLolo;
import entidad.JugadorSpace;
import jugadores.Instrucciones;
import mapa.Mapa;
import mapa.MapaSpace;
import utiles.Archivo;
import utiles.NomJuegos;
import utiles.Posicion;

public class Nivel implements InterfazNivel {
    private Mapa map;
    private MapaSpace mapaSpace;
    private JugadorLolo playerLolo;
    private JugadorSpace playerSpace;
    private Control control;
    private int nom_juego;

    Instrucciones instrucciones = null;

    // Constructores

    public Nivel(String nombreNivel, int nom_juego) {
        Archivo disenioNivel = new Archivo(nombreNivel);
        if (nom_juego == NomJuegos.SPACE_INVADERS) {
            //mapaSpace = disenioNivel.cargar_mapa_space_invaders();
            mapaSpace = new MapaSpace(null, null, new Posicion(20, 20));
            control = new Control();
            playerSpace = new JugadorSpace(control, mapaSpace);
        } else {
            map = disenioNivel.cargar_mapa_lolo();
            control = new Control();
            playerLolo = new JugadorLolo(control, map);
        }

    }

    public Nivel(String nombreNivel, JugadorLolo player, int nom_juego) {
        Archivo disenioNivel = new Archivo(nombreNivel);
        if (nom_juego == NomJuegos.SPACE_INVADERS) {
            //mapaSpace = disenioNivel.cargar_mapa_space_invaders();
            mapaSpace = new MapaSpace(null, null, new Posicion(20, 20));
            control = new Control();
            this.playerLolo = player;
        } else {
            map = disenioNivel.cargar_mapa_lolo();
            control = new Control();
            this.playerLolo = player;
        }
    }

    // Metodos

    public void add(Instrucciones jugador) {
        instrucciones = jugador;
    }

    public void run() {
        instrucciones.ejecutarInstrucciones(playerLolo);

        switch (this.nom_juego) {
            case NomJuegos.BOBO:
                this.run_lolo();
                break;
            case NomJuegos.SPACE_INVADERS:
                this.run_space_invaders();
                break;
            default:
                break;
        }

    }

    // TODO
    public void run_space_invaders() {

    }

    public void run_lolo() {
        if (this.playerLolo.getPos().equals(map.getPosPuerta()))
            System.out.println("Ganaste! Esa era una solucion valida!");

        else
            System.out.println("Perdiste :c, Esa no era una solucion valida..");
    }

    // Getters

    public JugadorLolo getPlayer() {
        return playerLolo;
    }

    public JugadorSpace getPlayerSpace() {
        return playerSpace;
    }

    public Mapa getMapa() {
        return this.map;
    }

    public MapaSpace getMapaSpace() {
        return this.mapaSpace;
    }

    public Control getControl() {
        return this.control;
    }
}
