package jugadores;

import entidad.Jugador;

public class JugadorNivel1 extends Jugador {
	public JugadorNivel1(Jugador player) {
		super(player.getMap());

		this.moverDerecha();
		this.moverDerecha();

		for (int i = 0; i < 10; i++)
			this.moverAbajo();

		this.moverIzquierda();
		this.moverIzquierda();

		for (int i = 0; i < 8; i++)
			this.moverAbajo();

		for (int i = 0; i < 8; i++)
			this.moverArriba();

		for (int i = 0; i < 8; i++)
			this.moverDerecha();

		for (int i = 0; i < 8; i++)
			this.moverAbajo();

		for (int i = 0; i < 10; i++)
			this.moverDerecha();

		for (int i = 0; i < 8; i++)
			this.moverArriba();

		for (int i = 0; i < 12; i++)
			this.moverIzquierda();

		this.moverArriba();
		this.moverArriba();

		this.disparar();

		for (int i = 0; i < 8; i++)
			this.moverArriba();

		for (int i = 0; i < 12; i++)
			this.moverDerecha();

		this.moverAbajo();
		this.moverAbajo();

		this.moverDerecha();
		this.moverDerecha();
	}
}
