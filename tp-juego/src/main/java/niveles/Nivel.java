package niveles;

import animation.Control;
import entidad.Jugador;
import jugadores.Instrucciones;
import mapa.Mapa;
import utiles.Archivo;
import utiles.NomJuegos;

public class Nivel implements InterfazNivel {
    private Mapa map;
    private Jugador player;
    private Control control;
    private int nom_juego;
    
    Instrucciones instrucciones = null;

    // Constructores

    public Nivel(String nombreNivel, int nom_juego) {
        Archivo disenioNivel = new Archivo(nombreNivel);
        
        map = disenioNivel.cargarMapa(nom_juego);
        control = new Control();
        this.player = new Jugador(control, map);
        map.setPlayer(player);
        
        this.nom_juego = nom_juego;
    }

    public Nivel(String nombreNivel, Jugador player, int nom_juego){
        Archivo disenioNivel = new Archivo(nombreNivel);    
        map = disenioNivel.cargarMapa(nom_juego);
        control = new Control();
        this.player = player;
        map.setPlayer(player);
        this.nom_juego = nom_juego;
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

    public Jugador getPlayer() {
        return player;
    }

    public Mapa getMapa() {
        return this.map;
    }

    public Control getControl() {
        return this.control;
    }
}
