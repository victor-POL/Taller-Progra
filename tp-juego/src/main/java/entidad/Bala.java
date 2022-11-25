package entidad;

import javafx.scene.Group;
import main.MainLolo;
import main.MainSpaceInvaders;
import mapa.Mapa;
import mapa.MapaSpace;
import utiles.Constantes;
import utiles.Posicion;

public class Bala extends Entidad {
    private int direccion;
    private double counter = 0;
    private Group root;
    Mapa mapa;
    MapaSpace mapaSpace;
    // Constructores

    public Bala(Posicion pos, int direccion, MapaSpace mapaSpace) {
        super(0.5, pos, mapaSpace);
        this.mapaSpace = mapaSpace;
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
        render.setX(pos.getX() * MainSpaceInvaders.TILE);
        render.setY(pos.getY() * MainSpaceInvaders.TILE);

    }

    public Bala(Posicion pos, int direccion, MapaSpace map, Group root) {
        super(0.5, pos, map);
        this.mapaSpace = map;
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
        render.setX(pos.getX() * MainLolo.TILE);
        render.setY(pos.getY() * MainLolo.TILE);
        this.root = root;
    }

    public Bala(Posicion pos, int direccion, Mapa map) {
        super(0.5, pos, map);
        this.mapa = map;
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
        render.setX(pos.getX() * MainLolo.TILE);
        render.setY(pos.getY() * MainLolo.TILE);
    }

    public Bala(Posicion pos, int direccion, Mapa map, Group root) {
        super(0.5, pos, map);
        this.mapa = map;
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
        render.setX(pos.getX() * MainLolo.TILE);
        render.setY(pos.getY() * MainLolo.TILE);
        this.root = root;
    }

    // Movimiento

    public boolean mover() {
        boolean puedoMover = false;
        switch (direccion) {
            case Constantes.ARRIBA:
                if (this.pos.getY() - this.STEP_SIZE != 0
                        && puedeAtravesar(new Posicion(pos.getX(), Math.ceil(pos.getY())))) {
                    this.pos.setPos(this.pos.getX(), this.pos.getY() - this.STEP_SIZE);
                    puedoMover = true;
                }
                break;
            case Constantes.ABAJO:
                if (mapa != null) {
                    if (this.pos.getY() + this.STEP_SIZE != mapa.getLimite() - this.STEP_SIZE
                            && puedeAtravesar(new Posicion(pos.getX(), Math.ceil(pos.getY())))) {
                        this.pos.setPos(this.pos.getX(), this.pos.getY() + this.STEP_SIZE);
                        puedoMover = true;
                    }
                } else {
                    if (this.pos.getY() + this.STEP_SIZE != mapaSpace.getLimite() - this.STEP_SIZE
                            && puedeAtravesar(new Posicion(pos.getX(), Math.ceil(pos.getY())))) {
                        this.pos.setPos(this.pos.getX(), this.pos.getY() + this.STEP_SIZE);
                        puedoMover = true;
                    }
                }

                break;
            case Constantes.IZQ:
                if (this.pos.getX() - this.STEP_SIZE != 0
                        && puedeAtravesar(new Posicion(Math.ceil(pos.getX() - this.STEP_SIZE), pos.getY()))) {
                    this.pos.setPos(this.pos.getX() - this.STEP_SIZE, this.pos.getY());
                    puedoMover = true;
                }
                break;
            case Constantes.DER:
                if (this.pos.getX() + this.STEP_SIZE != mapa.getLimite() - this.STEP_SIZE
                        && puedeAtravesar(new Posicion(Math.ceil(pos.getX()), pos.getY()))) {
                    this.pos.setPos(this.pos.getX() + this.STEP_SIZE, this.pos.getY());
                    puedoMover = true;
                }
                break;
        }

        if (mapa != null) {
            // Es el lolo
            if (puedoMover) {
                Cosa c = mapa.getCosaByPosition(getPos());
                Enemigo e = mapa.getEnemyByPosition(getPos());
                JugadorLolo p = mapa.getPlayer();

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
        } else {
            // Es el Space
            if (puedoMover) {
                Posicion pos = getPos();
                Enemigo e = mapaSpace.getEnemyByPosition(pos);
                Enemigo e2 = mapaSpace.getEnemyByPosition(new Posicion(Math.ceil(pos.getX()), pos.getY()));
                Enemigo e3 = mapaSpace.getEnemyByPosition(new Posicion(Math.floor(pos.getX()), pos.getY()));
                JugadorSpace p = mapaSpace.getPlayer();

                Posicion playerPos = p.getPos();
                playerPos = new Posicion((int) playerPos.getX(), playerPos.getY());
                if (pos.equals(playerPos)
                        || pos.equals(new Posicion(playerPos.getX() + 0.25, playerPos.getY()))
                        || pos.equals(new Posicion(playerPos.getX() + 0.5, playerPos.getY()))
                        || pos.equals(new Posicion(playerPos.getX() + 0.75, playerPos.getY()))
                        || pos.equals(new Posicion(playerPos.getX() + 1, playerPos.getY()))) {
                    p.setDead(true);
                    return false;
                }

                if (e != null) {
                    e.setEstaMuerto();
                    return false;
                }

                if (e2 != null) {
                    e2.setEstaMuerto();
                    return false;
                }

                if (e3 != null) {
                    e3.setEstaMuerto();
                    return false;
                }
            }
        }

        return puedoMover;
    }

    // Metodos

    protected boolean puedeAtravesar(Posicion p) {

        if (mapa != null) {
            // Es el lolo
            String quePisoEs = mapa.getPisoByPosition((int) p.getY(), (int) p.getX()).getTipoDePiso();

            switch (quePisoEs) {
                case "camino":
                    return true;
                default:
                    return false;
            }
        } else {
            // Es el space
            String quePisoEs = mapaSpace.getPisoByPosition((int) p.getY(), (int) p.getX()).getTipoDePiso();

            switch (quePisoEs) {
                case "camino":
                    return true;
                default:
                    return false;
            }
        }

    }

    // JavaFX

    public void update(double deltaTime, Group root) {

        if (mapa != null) {
            // Es el lolo
            if (counter >= 0.04) {
                this.render.setX(pos.getX() * mapa.getTileSize());
                this.render.setY(pos.getY() * mapa.getTileSize());
                counter = 0;
                if (!this.mover()) {
                    mapa.getBalas().remove(this);
                    root.getChildren().remove(getRender());
                }
            } else
                counter += deltaTime;
        } else {
            // Es space
            if (counter >= 0.003) {
                this.render.setX(pos.getX() * mapaSpace.getTileSize());
                this.render.setY(pos.getY() * mapaSpace.getTileSize());
                counter = 0;
                if (!this.mover()) {
                    if (this.equals(mapaSpace.getPlayer().getBala()))
                        mapaSpace.getPlayer().eliminarBala();

                    mapaSpace.getBalas().remove(this);
                    root.getChildren().remove(getRender());
                }
            } else
                counter += deltaTime;
        }

    }

    public void show() {
        root.getChildren().add(getRender());
    }

}