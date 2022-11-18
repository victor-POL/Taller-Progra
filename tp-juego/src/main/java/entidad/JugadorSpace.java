
import animation.Control;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mapa.MapaGenerico;
import mapa.MapaSpace;
import utiles.Posicion;

public class JugadorSpace extends Entidad {

    MapaSpace mapa;

    public JugadorSpace(Control c, MapaSpace map) {
        super(0.1, map.getPosicionInicial(), map);

        this.control = c;
        Image spriteImage = new Image("file:src/main/resources/sprites/jugadores/carpincho.png");
        render = new ImageView(spriteImage);

        render.setX(map.getPosInicialJugador().getX());
        render.setY(map.getPosInicialJugador().getY());
    }

}