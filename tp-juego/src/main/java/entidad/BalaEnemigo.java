package entidad;

import javafx.scene.Group;
import mapa.Mapa;
import mapa.MapaSpace;
import utiles.Posicion;

public class BalaEnemigo extends Bala {
    private double counter = 0;

    public BalaEnemigo(Posicion pos, int direccion, Mapa map) {
        super(pos, direccion, map);
    }

    public BalaEnemigo(Posicion pos, int direccion, MapaSpace map) {
        super(pos, direccion, map);
    }

    @Override
    protected boolean puedeAtravesar(Posicion p) {
        String quePisoEs = null;

        if (mapa != null)
            quePisoEs = mapa.getPisoByPosition((int) p.getY(), (int) p.getX()).getTipoDePiso();
        else
            quePisoEs = mapaSpace.getPisoByPosition((int) p.getY(), (int) p.getX()).getTipoDePiso();

        switch (quePisoEs) {
            case "roca":
                return false;
            default:
                return true;
        }
    }

    @Override
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
            if (counter >= 0.04) {
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

}
