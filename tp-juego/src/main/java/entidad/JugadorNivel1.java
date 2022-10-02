package entidad;

public class JugadorNivel1 extends Jugador {
	public JugadorNivel1(Jugador player) {
		super(player.getMap());
		
		for (int i = 0; i < 6; i++) 
			this.moverDerecha();
		
		
		for (int i = 0; i < 4; i++)
			this.moverAbajo();
		
		for (int i = 0; i < 14; i++)
			this.moverDerecha();
		
		this.moverArriba();
		this.moverArriba();
		this.moverArriba();
		this.moverArriba();
		
		for (int i = 0; i < 4; i++)
			this.moverAbajo();
		
		for (int i = 0; i < 8; i++)
			this.moverIzquierda();
		
		this.moverArriba();
		this.disparar();

		for (int i = 0; i < 10; i++) {
			this.moverArriba();
		}
		
		for (int i = 0; i < 4; i++)
			this.moverIzquierda();
		
		for (int i = 0; i < 4; i++)
			this.moverDerecha();
		
		for (int i = 0; i < 16; i++)
			this.moverAbajo();
		
		for (int i = 0; i < 4; i++)
			this.moverIzquierda();
		
		for (int i = 0; i < 4; i++)
			this.moverDerecha();
		
		for (int i = 0; i < 20; i++)
			this.moverArriba();
		
		
	}		
}