package niveles;

import animation.Control;
import entidad.JugadorLolo;
import jugadores.Instrucciones;
import mapa.Mapa;
import mapa.MapaSpace;
import utiles.Archivo;
import utiles.NomJuegos;

public class Nivel implements InterfazNivel {
    private Mapa map;
    private MapaSpace mapaSpace;
    private JugadorLolo player;
    private Control control;
    private int nom_juego;

    Instrucciones instrucciones = null;

    // Constructores

    public Nivel(String nombreNivel, int nom_juego) {
        Archivo disenioNivel = new Archivo(nombreNivel);
        if (nom_juego == NomJuegos.SPACE_INVADERS) {
            mapaSpace = disenioNivel.cargar_mapa_space_invaders();
            control = new Control();
            player = new JugadorLolo(control, mapaSpace);
        } else {
            map = disenioNivel.cargar_mapa_lolo();
            control = new Control();
            player = new JugadorLolo(control, map);
        }

    }

    public Nivel(String nombreNivel, JugadorLolo player, int nom_juego) {
        Archivo disenioNivel = new Archivo(nombreNivel);
        if (nom_juego == NomJuegos.SPACE_INVADERS) {
            mapaSpace = disenioNivel.cargar_mapa_space_invaders();
            control = new Control();
            this.player = player;
        } else {
            map = disenioNivel.cargar_mapa_lolo();
            control = new Control();
            this.player = player;
        }
    }

    // Metodos

    public void add(Instrucciones jugador) {
        instrucciones = jugador;
    }

    public void run() {
        instrucciones.ejecutarInstrucciones(player);

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
        if (this.player.getPos().equals(map.getPosPuerta()))
            System.out.println("Ganaste! Esa era una solucion valida!");

        else
            System.out.println("Perdiste :c, Esa no era una solucion valida..");
    }

    // Getters

    public JugadorLolo getPlayer() {
        return player;
    }

    public Mapa getMapa() {
        return this.map;
    }

    public Control getControl() {
        return this.control;
    }
}
