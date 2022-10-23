package entidad;

import mapa.Mapa;
import utiles.Posicion;
import utiles.Constantes;

public class Bala extends Entidad {
    private int direccion;
    private int counter = 0;
    private int speed = 2;
    // Constructores

    public Bala(Posicion pos, int direccion, Mapa map) {
        super(0.5, pos, map);
        this.direccion = direccion;
        switch (direccion) {
            case Constantes.ARRIBA:
                this.setImage("file:src/main/resources/cosas/balaArriba.png");
                break;
            case Constantes.ABAJO:
                this.setImage("file:src/main/resources/cosas/balaAbajo.png");
                break;
            case Constantes.DER:
                this.setImage("file:src/main/resources/cosas/balaDerecha.png");
                break;
            case Constantes.IZQ:
                this.setImage("file:src/main/resources/cosas/balaIzquierda.png");
                break;
        }
    }

    // Movimiento

    public boolean mover() {
        boolean puedoMover = false;
        switch (direccion) {
            case Constantes.ARRIBA:
                if (this.pos.getY() - 0.5 != 0) {
                    this.pos.setPos(this.pos.getX(), this.pos.getY() - 0.5);
                    puedoMover = true;
                }
                break;
            case Constantes.ABAJO:
                if (this.pos.getY() + 0.5 != map.getLimite() - 0.5) {
                    this.pos.setPos(this.pos.getX(), this.pos.getY() + 0.5);
                    puedoMover = true;
                }
                break;
            case Constantes.IZQ:
                if (this.pos.getX() - 0.5 != 0) {
                    this.pos.setPos(this.pos.getX() - 0.5, this.pos.getY());
                    puedoMover = true;
                }
                break;
            case Constantes.DER:
                if (this.pos.getX() + 0.5 != map.getLimite() - 0.5) {
                    this.pos.setPos(this.pos.getX() + 0.5, this.pos.getY());
                    puedoMover = true;
                }
                break;
        }

        if (puedoMover) {
            Cosa c = map.getByPosition(getPos());
            Enemigo e = map.getEnemyByPosition(getPos());

            if (c != null && !c.esRecogible())
                return false;

            if (e != null) {
                e.setMuerto();
                return false;
            }
        }

        return puedoMover;
    }

    public boolean update(double deltaTime) {
        if (counter >= speed) {
            this.render.setX(pos.getX() * 32);
            this.render.setY(pos.getY() * 32);
            counter = 0;
            return this.mover();
        } else
            counter++;
        return true;

    }

}