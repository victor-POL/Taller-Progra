package entidad;

import mapa.Mapa;
import utiles.Posicion;

public class BalaEnemigo extends Bala {

    public BalaEnemigo(Posicion pos, int direccion, Mapa map) {
        super(pos, direccion, map);
    }
    
    @Override
    protected boolean atravieso(Posicion p) {
        String quePisoEs = map.getPisoByPosition((int)p.getY(), (int)p.getX()).getQueSoy();

        switch(quePisoEs){
            case "roca":
                return false;
            default:
                return true;
        }
    }
    
}
