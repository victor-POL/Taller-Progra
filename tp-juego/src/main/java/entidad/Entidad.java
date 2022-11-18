package entidad;

import animation.Control;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mapa.MapaGenerico;
import utiles.Constantes;
import utiles.Posicion;

public class Entidad implements Constantes {
    protected final double STEP_SIZE;
    protected Posicion pos;
    protected MapaGenerico mapa;
    protected boolean estaMuerto = false;

    protected ImageView render;
    protected Control control;

    // Constructores

    public Entidad(double paso, Posicion pos, MapaGenerico mapa) {
        STEP_SIZE = paso;
        this.pos = pos;
        this.mapa = mapa;
    }

    // Movimientos

    public boolean moverDerecha() {
        if (mapa.puedoPasar((int) (pos.getX() + 1), (int) Math.ceil(pos.getY())) &&
                mapa.puedoPasar((int) (pos.getX() + 1), (int) Math.floor(pos.getY()))) {
            pos.setX(pos.getX() + STEP_SIZE);

            return true;
        }

        return false;
    }

    public boolean moverIzquierda() {

        if (mapa.puedoPasar((int) Math.round(pos.getX() - 1), (int) Math.ceil(pos.getY())) &&
                mapa.puedoPasar((int) Math.round(pos.getX() - 1), (int) Math.floor(pos.getY()))) {
            pos.setX(pos.getX() - STEP_SIZE);

            return true;
        }

        return false;
    }

    public boolean moverAbajo() {
        if (mapa.puedoPasar((int) (Math.ceil(pos.getX())), (int) (pos.getY() + 1)) &&
                mapa.puedoPasar((int) (Math.floor(pos.getX())), (int) (pos.getY() + 1))) {
            pos.setY(pos.getY() + STEP_SIZE);

            return true;
        }
        return false;
    }

    public boolean moverArriba() {
        if (mapa.puedoPasar((int) (Math.ceil(pos.getX())), (int) Math.round(pos.getY() - 1)) &&
                mapa.puedoPasar((int) (Math.floor(pos.getX())), (int) Math.round(pos.getY() - 1))) {
            pos.setY(pos.getY() - STEP_SIZE);

            return true;
        }

        return false;
    }

    // Getters

    public Posicion getPos() {
        return pos;
    }

    public Node getRender() {
        return render;
    }

    public boolean estaMuerto() {
        return this.estaMuerto;
    }

    // Setters

    public void setImage(ImageView image) {
        this.render = image;
    }

    public void setImage(String path) {
        Image i = new Image(path);
        this.render = new ImageView(i);
    }

    public void setPos(Posicion pos) {
        this.pos = pos;
    }

}
