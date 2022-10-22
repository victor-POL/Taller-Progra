package entidad;

import java.util.ArrayList;
import java.util.List;

import animation.Control;
import animation.Direction;
import animation.SpriteAnimation;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import mapa.Mapa;
import utiles.Constantes;
import utiles.Posicion;

public class Jugador extends Entidad {
	private int cantBalas = 0;
	private int orientacionActual = utiles.Constantes.DER;
	public boolean progreso = false;

	private List<Cosa> inventario = new ArrayList<Cosa>();

	private final int width = 16;
	private final int height = 16;
	private final Duration animationSpeed = Duration.millis(100);
	private int imageSpacing = 0;
	private boolean moving = false;
	private boolean dead = false;

	private TranslateTransition translateTransitionWait;
	private TranslateTransition translateTransitionDown;
	private TranslateTransition translateTransitionLeft;
	private TranslateTransition translateTransitionUp;
	private TranslateTransition translateTransitionRight;

	private SpriteAnimation waitAnimation;
	private SpriteAnimation runningAnimationDown;
	private SpriteAnimation runningAnimationLeft;
	private SpriteAnimation runningAnimationUp;
	private SpriteAnimation runningAnimationRight;

	private EventHandler<ActionEvent> stepEvent;


	// Constructores

	public Jugador(Control c, Mapa map) {
		super(0.5, map.getPosInicialJugador(), map);

		this.control = c;
		Image spriteImages = new Image("file:src/main/resources/jugador/lolo.png");
		render = new ImageView(spriteImages);
		
		render.setX(map.getPosInicialJugador().getX());
		render.setY(map.getPosInicialJugador().getY());
		

		createEvent();
		createAnimations();
		createTransitions();

		waitAnimation.play();
	}

	// Metodos

	public void createEvent(){
		stepEvent = new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event){
			    setX(getPos().getX() );
                setY(getPos().getY() );
				render.setTranslateX(0);
				render.setTranslateY(0);
				moving = false;
				if(control.getDirection() != Direction.NONE){
					step();
				}
			}
		};
	}
	private void setX(double x) {
	    pos.setX(x);
	    render.setX(x * width);
	}
	
	private void setY(double y) {
	    pos.setY(y);
	    render.setY(y * height);
	}
	
	//transiciones JavaFX
	
	private void createTransitions() {
		translateTransitionDown = new TranslateTransition(animationSpeed, render);
		translateTransitionDown.setFromY(0);
		translateTransitionDown.setToY(STEP_SIZE * height);
		translateTransitionDown.setOnFinished(stepEvent);
		
		translateTransitionLeft = new TranslateTransition(animationSpeed, render);
		translateTransitionLeft.setFromX(0);
		translateTransitionLeft.setToX(-STEP_SIZE * width);
		translateTransitionLeft.setOnFinished(stepEvent);
		
		translateTransitionUp = new TranslateTransition(animationSpeed, render);
		translateTransitionUp.setFromY(0);
		translateTransitionUp.setToY(-STEP_SIZE * height);
		translateTransitionUp.setOnFinished(stepEvent);
		
		translateTransitionRight = new TranslateTransition(animationSpeed, render);
		translateTransitionRight.setFromX(0);
		translateTransitionRight.setToX(STEP_SIZE * width);
		translateTransitionRight.setOnFinished(stepEvent);
		
		translateTransitionWait = new TranslateTransition(animationSpeed, render);
		translateTransitionWait.setOnFinished(stepEvent);
	}

	private void createAnimations() {
	    waitAnimation = new SpriteAnimation(render, animationSpeed, 5, 4, 7, 0, 0, imageSpacing, width, height);
		runningAnimationDown = new SpriteAnimation(render, animationSpeed, 5, 4, 7, 0, 0, imageSpacing, width, height);
		runningAnimationDown.setCustomFrames(new int[]{3,4,3,2});
		runningAnimationLeft = new SpriteAnimation(render, animationSpeed, 5, 4, 7, 0, 0, imageSpacing, width, height);
		runningAnimationLeft.setCustomFrames(new int[]{10,11,10,9});
		runningAnimationUp = new SpriteAnimation(render, animationSpeed, 5, 4, 7, 0, 0, imageSpacing, width, height);
		runningAnimationUp.setCustomFrames(new int[]{17,18,17,16});
		runningAnimationRight = new SpriteAnimation(render, animationSpeed, 5, 4, 7, 0, 0, imageSpacing, width, height);
		runningAnimationRight.setCustomFrames(new int[]{24,25,24,23});
	}

	public void step() {
		if (!dead && !moving) {
			moving = true;
			switch(control.getDirection()) {
			case DOWN:
				if (this.moverAbajo()) {
					translateTransitionDown.play();
				} else {
					translateTransitionWait.play();
				}
				runningAnimationDown.playFromStart();
				break;
			case LEFT:
				if (this.moverIzquierda()) {
					translateTransitionLeft.play();
				} else {
					translateTransitionWait.play();
				}
				runningAnimationLeft.playFromStart();
				break;
			case RIGHT:
				if (this.moverDerecha()) {
					translateTransitionRight.play();
				} else {
					translateTransitionWait.play();
				}
				runningAnimationRight.playFromStart();
				break;
			case UP:
				if (this.moverArriba()) {
					translateTransitionUp.play();
				} else {
					translateTransitionWait.play();
				}
				runningAnimationUp.playFromStart();
				break;
			case NONE:
				break;
			}
		}
	}

	private boolean chequeo_items_y_progreso(Cosa c) {
		if ((c = map.getByPosition(pos.getPos())) != null) {

			map.getByPosition(pos.getPos()).setRecogido();
			inventario.add(c);
			System.out.println(c);
			if(c.queSoy == "puerta abierta"){
				progreso = true;
			}
			//map.removeCosa(pos.getPos());
			System.out.println("Recogiste un item");

			cantBalas++;

			if (inventario.size() == map.getItemsObjetivo()) {
				map.habilitarCofre();
			} else if (inventario.size() > map.getItemsObjetivo() && !map.getPuertaHabilitada()) {
				map.habilitarPuerta();
			}
		}
		return true;
	}
	
	public boolean disparar() {
		if (cantBalas > 0) {
			Bala b = new Bala(new Posicion(this.pos.getX(), this.pos.getY()), orientacionActual, map);
			cantBalas--;
			while (b.mover()) {

				Cosa c = map.getByPosition(b.getPos());
				Enemigo e = map.getEnemyByPosition(b.getPos());

				if (c != null && !c.esRecogible()) {
					break;
				}
				if (e != null) {
					e.setMuerto();
					break;
				}
			}

			return true;
		}

		return false;
	}

	// Movimientos

	@Override
	public boolean moverDerecha() {
		Cosa c = null;
		boolean res = false;

		if (super.moverDerecha()) {
			res = chequeo_items_y_progreso(c);

		} else {

			c = map.getByPosition(this.pos.posDerecha());

			// 1 - Hay algo en esa casilla?
			if (c != null) {

				this.map.removeCosa(c.getPos());
				res = c.moverDerecha();
				this.map.actualizarCosa(c);

				super.moverDerecha();
			} else
				res = false;

		}

		orientacionActual = Constantes.DER;
		return res;
	}

	@Override
	public boolean moverIzquierda() {

		Cosa c = null;
		boolean res = false;

		if (super.moverIzquierda()) {
			res = chequeo_items_y_progreso(c);

		} else {

			c = map.getByPosition(this.pos.posIzquierda());

			// 1 - Hay algo en esa casilla?
			if (c != null) {

				this.map.removeCosa(c.getPos());
				res = c.moverIzquierda();
				this.map.actualizarCosa(c);

				super.moverIzquierda();
			} else
				res = false;

		}

		orientacionActual = Constantes.IZQ;
		return res;
	}



	@Override
	public boolean moverArriba() {
		Cosa c = null;
		boolean res = false;

		if (super.moverArriba()) {
			res = chequeo_items_y_progreso(c);

		} else {

			c = map.getByPosition(this.pos.posArriba());

			// 1 - Hay algo en esa casilla?
			if (c != null) {

				this.map.removeCosa(c.getPos());
				res = c.moverArriba();
				this.map.actualizarCosa(c);

				super.moverArriba();
			} else
				res = false;

		}

		orientacionActual = Constantes.ARRIBA;
		return res;
	}

	@Override
	public boolean moverAbajo() {
		Cosa c = null;
		boolean res = false;

		if (super.moverAbajo()) {
			res = chequeo_items_y_progreso(c);
		} else {

			c = map.getByPosition(this.pos.posAbajo());

			// 1 - Hay algo en esa casilla?
			if (c != null) {
				this.map.removeCosa(c.getPos());
				res = c.moverAbajo();
				this.map.actualizarCosa(c);

				super.moverAbajo();
			} else
				res = false;
		}

		orientacionActual = Constantes.ABAJO;
		return res;
	}
	
	
	public List<Cosa> getInventario() {
		return inventario;
	}


	public boolean update(double deltaTime) {
		return this.progreso;
	}

}
