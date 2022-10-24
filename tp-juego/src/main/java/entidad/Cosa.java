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
        if (map.puedoPasar((int) (pos.getX() + this.STEP_SIZE), (int) pos.getY())
                && map.getByPosition(new Posicion(pos.getX() + this.STEP_SIZE, (int) pos.getY())) == null) {
            pos.setX(pos.getX() + STEP_SIZE);
            return true;
        }
        return false;
    }

    @Override
    public boolean moverIzquierda() {
        if (map.puedoPasar((int) Math.round(pos.getX() - this.STEP_SIZE), (int) pos.getY())
                && map.getByPosition(new Posicion((int) Math.round(pos.getX() - this.STEP_SIZE), (int) pos.getY())) == null) {
            pos.setX(pos.getX() - STEP_SIZE);
            return true;
        }
        return false;
    }

    @Override
    public boolean moverArriba() {
        if (map.puedoPasar((int) (pos.getX()), (int) Math.round(pos.getY() - this.STEP_SIZE))
                && map.getByPosition(new Posicion((int) (pos.getX()), (int) Math.round(pos.getY() - this.STEP_SIZE))) == null) {
            pos.setY(pos.getY() - STEP_SIZE);
            return true;
        }
        return false;
    }

    @Override
    public boolean moverAbajo() {
        if (map.puedoPasar((int) (pos.getX()), (int) (pos.getY() + this.STEP_SIZE))
                && map.getByPosition(new Posicion((int) (pos.getX()), (int) (pos.getY() + this.STEP_SIZE))) == null) {
            pos.setY(pos.getY() + STEP_SIZE);
            return true;
        }
        return false;
    }

    // Setters

    public void setRender() {
        switch (queSoy) {
            case "municion":
                this.render = new ImageView(new Image("file:src/main/resources/cosas/municion.png"));
                break;

            case "obstaculo empujable":
                this.render = new ImageView(new Image("file:src/main/resources/cosas/obstaculos/obstaculo_empujable.png"));
                break;
                
            case "cofre abierto":
                this.render = new ImageView(new Image("file:src/main/resources/piso/cofres/cofre_abierto.png"));
                break;

            case "puerta abierta":
                this.render = new ImageView(new Image("file:src/main/resources/piso/puertas/puerta_abierta.png"));
                break;

            case "recogido":
                this.render = new ImageView(new Image("file:src/main/resources/piso/empty.png"));
                break;
            
            case "llave":
                this.render = new ImageView(new Image("file:src/main/resources/cosas/llave.png"));
                break;

            default:
                this.render = new ImageView(new Image("file:src/main/resources/piso/agua.png"));

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
