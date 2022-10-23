package entidad;

import mapa.Mapa;
import utiles.Posicion;
import utiles.Constantes;

public class Bala extends Entidad {
    private int direccion;

    // Constructores

    public Bala(Posicion pos, int direccion, Mapa map) {
        super(1, pos, map);
        this.direccion = direccion;
    }

    // Movimiento

    public boolean mover() {
        switch (direccion) {
            case Constantes.ARRIBA:
                if (this.pos.getY() - 0.5 != 0) {
                    this.pos.setPos(this.pos.getX(), this.pos.getY() - 0.5);
                    return true;
                }
                break;
            case Constantes.ABAJO:
                if (this.pos.getY() + 0.5 != map.getLimite()) {
                    this.pos.setPos(this.pos.getX(), this.pos.getY() + 0.5);
                    return true;
                }
                break;
            case Constantes.IZQ:
                if (this.pos.getX() - 0.5 != 0) {
                    this.pos.setPos(this.pos.getX() - 0.5, this.pos.getY());
                    return true;
                }
                break;
            case Constantes.DER:
                if (this.pos.getX() + 0.5 != map.getLimite()) {
                    this.pos.setPos(this.pos.getX() + 0.5, this.pos.getY());
                    return true;
                }
                break;
        }
        return false;
    }

}