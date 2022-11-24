package entidad;

import mapa.Mapa;
import mapa.MapaSpace;
import utiles.Posicion;

public class BalaEnemigo extends Bala {

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

}
