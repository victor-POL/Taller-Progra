package entidad;

public class JugadorNivel1 extends Jugador {
	public JugadorNivel1(Jugador player) {
		super(player.getMap());
        this.moverDerecha();
        this.moverDerecha();
        this.moverArriba();
        this.moverDerecha();
        this.moverDerecha();
        this.moverDerecha();
        this.moverDerecha();
    }
}
