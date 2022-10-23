package jugadores;

import entidad.Jugador;

public class JugadorNivel1 implements Instrucciones {
    public JugadorNivel1() {
    }

    public void ejecutarInstrucciones(Jugador player) {
        player.moverDerecha();
        player.moverDerecha();

        for (int i = 0; i < 10; i++)
            player.moverAbajo();

        player.moverIzquierda();
        player.moverIzquierda();

        for (int i = 0; i < 8; i++)
            player.moverAbajo();

        for (int i = 0; i < 8; i++)
            player.moverArriba();

        for (int i = 0; i < 8; i++)
            player.moverDerecha();

        for (int i = 0; i < 8; i++)
            player.moverAbajo();

        for (int i = 0; i < 10; i++)
            player.moverDerecha();

        for (int i = 0; i < 8; i++)
            player.moverArriba();

        for (int i = 0; i < 12; i++)
            player.moverIzquierda();

        player.moverArriba();
        player.moverArriba();

        player.disparar();

        for (int i = 0; i < 8; i++)
            player.moverArriba();

        for (int i = 0; i < 12; i++)
            player.moverDerecha();

        player.moverAbajo();
        player.moverAbajo();

        player.moverDerecha();
        player.moverDerecha();

    }
}
