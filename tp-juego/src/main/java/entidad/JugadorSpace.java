package entidad;

import animation.Control;
import animation.Direction;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import mapa.MapaSpace;
import utiles.Posicion;

public class JugadorSpace extends Entidad {

    private final int TILE_SIZE = 16;
    private final int ORIENTACION = utiles.Constantes.ARRIBA;
    public boolean completoNivel = false;
    private int vidas = 3;
    MapaSpace mapa;

    private final int width = TILE_SIZE;
    private final int height = TILE_SIZE;

    private final Duration animationSpeed = Duration.millis(1);
    private int imageSpacing = 0;
    private boolean estaMoviendose = false;

    private TranslateTransition translateTransitionRight;
    private TranslateTransition translateTransitionLeft;
    private TranslateTransition translateTransitionWait;

    private EventHandler<ActionEvent> stepEvent;

    public JugadorSpace(Control c, MapaSpace map) {
        super(0.5, map.getPosicionInicial(), map);
        
        this.mapa = map;

        this.control = c;
        Image spriteImage = new Image("file:src/main/resources/Space_Invaders/nave-espacial.png");
        render = new ImageView(spriteImage);

        render.setX(map.getPosInicialJugador().getX());
        render.setY(map.getPosInicialJugador().getY());

        createEvent();
        createTransitions();

    }

    public JugadorSpace(MapaSpace map) {
        super(0.5, map.getPosInicialJugador(), map);
    }

    // Metodos

    // Movimientos

    // TODO
    @Override
    public boolean moverDerecha() {
        boolean res = false;

        res = super.moverDerecha();
        mapa.setPos(pos);
        return res;
    }

    // TODO
    @Override
    public boolean moverIzquierda() {
        boolean res = false;

        res = super.moverIzquierda();
        mapa.setPos(pos);
        
        return res;
    }

    public Bala disparar() {
        Bala bala = new Bala(new Posicion(this.pos.getX(), this.pos.getY()), ORIENTACION, mapa);
        this.mapa.addBala(bala);

        return bala;
    }

    // JavaFX
    public void createEvent() {
        stepEvent = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setX(getPos().getX());
                setY(getPos().getY());
                render.setTranslateX(0);
                render.setTranslateY(0);
                estaMoviendose = false;
                if (control.getDirection() != Direction.NONE) {
                    step();
                }
            }
        };
    }

    // Transiciones JavaFX

    private void createTransitions() {

        translateTransitionLeft = new TranslateTransition(animationSpeed, render);
        translateTransitionLeft.setFromX(0);
        translateTransitionLeft.setToX(-STEP_SIZE * width);
        translateTransitionLeft.setOnFinished(stepEvent);

        translateTransitionRight = new TranslateTransition(animationSpeed, render);
        translateTransitionRight.setFromX(0);
        translateTransitionRight.setToX(STEP_SIZE * width);
        translateTransitionRight.setOnFinished(stepEvent);

        translateTransitionWait = new TranslateTransition(animationSpeed, render);
        translateTransitionWait.setOnFinished(stepEvent);

    }

    public void step() {
        switch (control.getDirection()) {
            case LEFT:
                if (this.moverIzquierda()) {
                    translateTransitionLeft.play();
                } else {
                    translateTransitionWait.play();
                }
                break;
            case RIGHT:
                if (this.moverDerecha()) {
                    translateTransitionRight.play();
                } else {
                    translateTransitionWait.play();
                }
                break;
            default:
                break;
        }
    }

    // Setters

    private void setX(double x) {
        pos.setX(x);
        render.setX(x * width);
    }

    private void setY(double y) {
        pos.setY(y);
        render.setY(y * height);
    }
    
    public void setDead(boolean estaMuerto) {
        this.estaMuerto = estaMuerto;
        vidas--;
    }
    
    // TODO
//    public void update() {
//        // TODO Auto-generated method stub
//
//    }

}