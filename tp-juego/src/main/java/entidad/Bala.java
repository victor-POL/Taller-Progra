package entidad;

import javafx.scene.Group;
import main.Main;
import mapa.Mapa;
import utiles.Posicion;
import utiles.Constantes;

public class Bala extends Entidad {
    private int direccion;
    private double counter = 0;
    private Group root;

    // Constructores

    public Bala(Posicion pos, int direccion, Mapa map) {
        super(0.5, pos, map);
        this.direccion = direccion;

        switch (direccion) {
            case Constantes.ARRIBA:
                this.setImage("file:src/main/resources/sprites/balas/bala_arriba.png");
                break;
            case Constantes.ABAJO:
                this.setImage("file:src/main/resources/sprites/balas/bala_abajo.png");
                break;
            case Constantes.DER:
                this.setImage("file:src/main/resources/sprites/balas/bala_derecha.png");
                break;
            case Constantes.IZQ:
                this.setImage("file:src/main/resources/sprites/balas/bala_izquierda.png");
                break;
        }
        render.setX(pos.getX() * Main.TILE);
        render.setY(pos.getY() * Main.TILE);
    }
    public Bala(Posicion pos, int direccion, Mapa map, Group root) {
        super(0.5, pos, map);
        this.direccion = direccion;

        switch (direccion) {
            case Constantes.ARRIBA:
                this.setImage("file:src/main/resources/sprites/balas/bala_arriba.png");
                break;
            case Constantes.ABAJO:
                this.setImage("file:src/main/resources/sprites/balas/bala_abajo.png");
                break;
            case Constantes.DER:
                this.setImage("file:src/main/resources/sprites/balas/bala_derecha.png");
                break;
            case Constantes.IZQ:
                this.setImage("file:src/main/resources/sprites/balas/bala_izquierda.png");
                break;
        }
        render.setX(pos.getX() * Main.TILE);
        render.setY(pos.getY() * Main.TILE);
        this.root = root;
    }

    // Movimiento

    public boolean mover() {
        boolean puedoMover = false;
        switch (direccion) {
            case Constantes.ARRIBA:
                if (this.pos.getY() - 0.5 != 0 && puedeAtravesar(new Posicion(pos.getX(), Math.ceil(pos.getY())))) {
                    this.pos.setPos(this.pos.getX(), this.pos.getY() - 0.5);
                    puedoMover = true;
                }
                break;
            case Constantes.ABAJO:
                if (this.pos.getY() + 0.5 != mapa.getLimite() - 0.5
                        && puedeAtravesar(new Posicion(pos.getX(), Math.ceil(pos.getY())))) {
                    this.pos.setPos(this.pos.getX(), this.pos.getY() + 0.5);
                    puedoMover = true;
                }
                break;
            case Constantes.IZQ:
                if (this.pos.getX() - 0.5 != 0
                        && puedeAtravesar(new Posicion(Math.ceil(pos.getX() - 0.5), pos.getY()))) {
                    this.pos.setPos(this.pos.getX() - 0.5, this.pos.getY());
                    puedoMover = true;
                }
                break;
            case Constantes.DER:
                if (this.pos.getX() + 0.5 != mapa.getLimite() - 0.5
                        && puedeAtravesar(new Posicion(Math.ceil(pos.getX()), pos.getY()))) {
                    this.pos.setPos(this.pos.getX() + 0.5, this.pos.getY());
                    puedoMover = true;
                }
                break;
        }

        if (puedoMover) {
            Cosa c = mapa.getByPosition(getPos());
            Enemigo e = mapa.getEnemyByPosition(getPos());
            Jugador p = mapa.getPlayer();

            if (p.getPos().equals(this.pos)) {
                p.setDead(true);
                return false;
            }

            if (c != null && !c.esRecogible())
                return false;

            if (e != null) {
                e.setEstaMuerto();
                return false;
            }
        }

        return puedoMover;
    }

    // Metodos

    protected boolean puedeAtravesar(Posicion p) {
        String quePisoEs = mapa.getPisoByPosition((int) p.getY(), (int) p.getX()).getTipoDePiso();

        switch (quePisoEs) {
            case "camino":
                return true;
            default:
                return false;
        }
    }

    // JavaFX

    public void update(double deltaTime, Group root) {
        if (counter >= 0.04) {
            this.render.setX(pos.getX() * 32);
            this.render.setY(pos.getY() * 32);
            counter = 0;
            if(!this.mover()){
                mapa.getBalas().remove(this);
                root.getChildren().remove(getRender());
            }
        } else
            counter += deltaTime;

    }


    public void show(){
        root.getChildren().add(getRender());
    }

}