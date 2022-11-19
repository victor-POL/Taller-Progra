package entidad;

import mapa.Mapa;
import mapa.MapaGenerico;
import utiles.Posicion;

public class BalaEnemigo extends Bala {

    public BalaEnemigo(Posicion pos, int direccion, Mapa map) {
        super(pos, direccion, map);
    }

    @Override
    protected boolean puedeAtravesar(Posicion p) {
        String quePisoEs = mapa.getPisoByPosition((int) p.getY(), (int) p.getX()).getTipoDePiso();

        switch (quePisoEs) {
            case "roca":
                return false;
            default:
                return true;
        }
    }

}
