package entidad;

import utiles.Posicion;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mapa.Mapa;

public class Cosa extends Entidad {
    private final int TILE_SIZE = 32;
    private final boolean esRecogible;
    public boolean fueRecogido = false;
    public String clasificacion;

    // Constructores

    public Cosa(double STEP_SIZE, Posicion pos, Mapa map, boolean esRecogible, boolean esEmpujable, String queSoy) {
        super(STEP_SIZE, pos, map);
        this.esRecogible = esRecogible;
        this.clasificacion = queSoy;
        this.setRender();
    }

    // Movimientos

    @Override
    public boolean moverDerecha() {
        if (mapa.puedoPasar((int) (pos.getX() + this.STEP_SIZE), (int) pos.getY())
                && mapa.getCosaByPosition(new Posicion(pos.getX() + this.STEP_SIZE, (int) pos.getY())) == null) {
            pos.setX(pos.getX() + STEP_SIZE);
            return true;
        }
        return false;
    }

    @Override
    public boolean moverIzquierda() {
        if (mapa.puedoPasar((int) Math.round(pos.getX() - this.STEP_SIZE), (int) pos.getY())
                && mapa.getCosaByPosition(
                        new Posicion((int) Math.round(pos.getX() - this.STEP_SIZE), (int) pos.getY())) == null) {
            pos.setX(pos.getX() - STEP_SIZE);
            return true;
        }
        return false;
    }

    @Override
    public boolean moverArriba() {
        if (mapa.puedoPasar((int) (pos.getX()), (int) Math.round(pos.getY() - this.STEP_SIZE))
                && mapa.getCosaByPosition(
                        new Posicion((int) (pos.getX()), (int) Math.round(pos.getY() - this.STEP_SIZE))) == null) {
            pos.setY(pos.getY() - STEP_SIZE);
            return true;
        }
        return false;
    }

    @Override
    public boolean moverAbajo() {
        if (mapa.puedoPasar((int) (pos.getX()), (int) (pos.getY() + this.STEP_SIZE))
                && mapa.getCosaByPosition(new Posicion((int) (pos.getX()), (int) (pos.getY() + this.STEP_SIZE))) == null) {
            pos.setY(pos.getY() + STEP_SIZE);
            return true;
        }
        return false;
    }

    // Getters

    public boolean esRecogible() {
        return this.esRecogible;
    }

    // Setters

    public void setRender() {
        switch (clasificacion) {
            case "municion":
                this.render = new ImageView(new Image("file:src/main/resources/sprites/items/carpincho_muerto.png"));
                break;

            case "obstaculo empujable":
                this.render = new ImageView(new Image("file:src/main/resources/sprites/obstaculos_movibles/piedra.png"));
                break;

//            case "cofre abierto":
//                this.render = new ImageView(new Image("file:src/main/resources/piso/cofres/cofre_abierto.png"));
//                break;

            case "item_pasar_de_nivel":
                this.render = new ImageView(new Image("file:src/main/resources/piso/puertas/puerta_abierta.png"));
                break;

            case "recogido":
                this.render = new ImageView(new Image("file:src/main/resources/sprites/caminables/empty.png"));
                break;

            case "llave":
                this.render = new ImageView(new Image("file:src/main/resources/sprites/items/llave.png"));
                break;

            default:
                this.render = new ImageView(new Image("file:src/main/resources/piso/agua.png"));

                break;
        }
    }

    public void setRecogido() {
        this.fueRecogido = true;
    }
    
   // JavaFX

    public boolean update(double deltaTime, Group root) {
        if (fueRecogido) {
            root.getChildren().remove(getRender());
            return false;
        }
        
        this.render.setX(pos.getX() * TILE_SIZE);
        this.render.setY(pos.getY() * TILE_SIZE);

        if(!root.getChildren().contains(getRender())) {
            root.getChildren().add(getRender());
        }
        return true;
    }
}
