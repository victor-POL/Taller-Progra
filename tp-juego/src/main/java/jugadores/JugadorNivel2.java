package jugadores;

import entidad.JugadorLolo;

public class JugadorNivel2 implements Instrucciones {
    public JugadorNivel2() {
    }

    public void ejecutarInstrucciones(JugadorLolo player) {
        for (int i = 0; i < 6; i++)
            player.moverDerecha();

        for (int i = 0; i < 4; i++)
            player.moverAbajo();

        for (int i = 0; i < 14; i++)
            player.moverDerecha();

        player.moverArriba();
        player.moverArriba();
        player.moverArriba();
        player.moverArriba();

        for (int i = 0; i < 4; i++)
            player.moverAbajo();

        for (int i = 0; i < 8; i++)
            player.moverIzquierda();

        player.moverArriba();
        player.disparar();

        for (int i = 0; i < 10; i++) {
            player.moverArriba();
        }

        for (int i = 0; i < 4; i++)
            player.moverIzquierda();

        for (int i = 0; i < 4; i++)
            player.moverDerecha();

        for (int i = 0; i < 16; i++)
            player.moverAbajo();

        for (int i = 0; i < 4; i++)
            player.moverIzquierda();

        for (int i = 0; i < 4; i++)
            player.moverDerecha();

        for (int i = 0; i < 20; i++)
            player.moverArriba();
    }
}