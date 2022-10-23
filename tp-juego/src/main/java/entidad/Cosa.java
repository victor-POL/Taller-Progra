package entidad;

import utiles.Posicion;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mapa.Mapa;

public class Cosa extends Entidad {
    private final int TILE = 32;
    private final boolean esRecogible;
    private final boolean esEmpujable;
    public boolean fueRecogido = false;
    private boolean cambie = false;
    public String queSoy = "";

    // Constructores

    public Cosa(Posicion pos, Mapa map, boolean esRecogible, boolean esEmpujable, String queSoy) {
        super(0, pos, map);
        this.esRecogible = esRecogible;
        this.esEmpujable = esEmpujable;
        this.queSoy = queSoy;
        this.setRender();
    }

    public Cosa(double STEP_SIZE, Posicion pos, Mapa map, boolean esRecogible, boolean esEmpujable, String queSoy) {
        super(STEP_SIZE, pos, map);
        this.esRecogible = esRecogible;
        this.esEmpujable = esEmpujable;
        this.queSoy = queSoy;
        this.setRender();
    }

    // Consultas

    public boolean esRecogible() {
        return esRecogible;
    }

    // Movimientos

    @Override
    public boolean moverDerecha() {
        if (map.puedoPasar((int) (pos.getX() + 1), (int) pos.getY())
                && map.getByPosition(new Posicion(pos.getX() + 1, (int) pos.getY())) == null) {
            pos.setX(pos.getX() + STEP_SIZE);
            return true;
        }
        return false;
    }

    @Override
    public boolean moverIzquierda() {
        if (map.puedoPasar((int) Math.round(pos.getX() - 1), (int) pos.getY())
                && map.getByPosition(new Posicion((int) Math.round(pos.getX() - 1), (int) pos.getY())) == null) {
            pos.setX(pos.getX() - STEP_SIZE);
            return true;
        }
        return false;
    }

    @Override
    public boolean moverArriba() {
        if (map.puedoPasar((int) (pos.getX()), (int) Math.round(pos.getY() - 1))
                && map.getByPosition(new Posicion((int) (pos.getX()), (int) Math.round(pos.getY() - 1))) == null) {
            pos.setY(pos.getY() - STEP_SIZE);
            return true;
        }
        return false;
    }

    @Override
    public boolean moverAbajo() {
        if (map.puedoPasar((int) (pos.getX()), (int) (pos.getY() + 1))
                && map.getByPosition(new Posicion((int) (pos.getX()), (int) (pos.getY() + 1))) == null) {
            pos.setY(pos.getY() + STEP_SIZE);
            return true;
        }
        return false;
    }

    // Setters

    public void setRender() {
        switch (queSoy) {
            case "corazon":
                this.render = new ImageView(new Image("file:src/main/resources/piso/imagenes/corazon.png"));
                break;

            case "caja":
                this.render = new ImageView(new Image("file:src/main/resources/cosas/caja.png"));
                break;
            case "cofre abierto":
                this.render = new ImageView(new Image("file:src/main/resources/piso/imagenes/cofre abierto.png"));
                break;

            case "puerta abierta":
                this.render = new ImageView(new Image("file:src/main/resources/piso/imagenes/puerta abierta.png"));
                break;

            case "recogido":
                this.render = new ImageView(new Image("file:src/main/resources/piso/imagenes/empty.png"));
                break;

            default:
                this.render = new ImageView(new Image("file:src/main/resources/piso/imagenes/agua.png"));

                break;
        }
    }

    public void setRecogido() {
        this.fueRecogido = true;
    }

    public void update(double deltaTime) {
        if (fueRecogido) {
            if (!cambie) {
                cambie = true;
                return;
            }
        }

        this.render.setX(pos.getX() * TILE);
        this.render.setY(pos.getY() * TILE);

    }

    @Override
    public String toString() {
        return "Cosa [esRecogible=" + esRecogible + ", esEmpujable=" + esEmpujable + ", fueRecogido=" + fueRecogido
                + ", cambie=" + cambie + ", queSoy=" + queSoy + ", STEP_SIZE=" + STEP_SIZE + ", pos=" + pos + ", map="
                + map + ", render=" + render + "]";
    }
}
