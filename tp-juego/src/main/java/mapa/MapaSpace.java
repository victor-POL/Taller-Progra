package mapa;

import utiles.Posicion;

public class MapaSpace extends MapaGenerico implements MapaInterfaz {

    public MapaSpace() {
        super(16, 45);

    }

    @Override
    public boolean puedoPasar(int x, int y) {
        return false;
    }

    @Override
    public void update(double deltaTime) {

    }

    public Posicion getPosicionInicial() {
        return this.posJugador;
    }

}
