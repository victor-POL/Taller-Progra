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
    private final int TILE_SIZE = 32;
    private int cantBalas = 0;
    private int orientacionActual = utiles.Constantes.DER;
    public boolean completoNivel = false;
    private List<Cosa> inventario = new ArrayList<Cosa>();

    private final int width = TILE_SIZE;
    private final int height = TILE_SIZE;
    private final Duration animationSpeed = Duration.millis(100);
    private int imageSpacing = 0;
    private boolean estaMoviendose = false;

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
        Image spriteImages = new Image("file:src/main/resources/sprites/jugadores/carpincho.png");
        render = new ImageView(spriteImages);

        render.setX(map.getPosInicialJugador().getX());
        render.setY(map.getPosInicialJugador().getY());

        createEvent();
        createAnimations();
        createTransitions();

        waitAnimation.play();
    }

    public Jugador(Mapa map) {
        super(0.5, map.getPosInicialJugador(), map);
    }

    // Movimientos

    @Override
    public boolean moverDerecha() {
        Cosa item = null;
        boolean res = false;

        if (super.moverDerecha()) {
            res = chequeo_items_y_progreso(item);

        } else {
            item = mapa.getByPosition(this.pos.posDerecha());

            if (item != null) {

                this.mapa.removeCosa(item.getPos());
                res = item.moverDerecha();
                this.mapa.actualizarCosa(item);

                super.moverDerecha();
            } else
                res = false;
        }

        orientacionActual = Constantes.DER;
        mapa.setPos(pos);

        return res;
    }

    @Override
    public boolean moverIzquierda() {

        Cosa item = null;
        boolean res = false;

        if (super.moverIzquierda()) {
            res = chequeo_items_y_progreso(item);

        } else {
            item = mapa.getByPosition(this.pos.posIzquierda());

            if (item != null) {

                this.mapa.removeCosa(item.getPos());
                res = item.moverIzquierda();
                this.mapa.actualizarCosa(item);

                super.moverIzquierda();
            } else
                res = false;
        }

        orientacionActual = Constantes.IZQ;
        mapa.setPos(pos);

        return res;
    }

    @Override
    public boolean moverArriba() {
        Cosa item = null;
        boolean res = false;

        if (super.moverArriba()) {
            res = chequeo_items_y_progreso(item);

        } else {

            item = mapa.getByPosition(this.pos.posArriba());

            // 1 - Hay algo en esa casilla?
            if (item != null) {

                this.mapa.removeCosa(item.getPos());
                res = item.moverArriba();
                this.mapa.actualizarCosa(item);

                super.moverArriba();
            } else
                res = false;
        }

        orientacionActual = Constantes.ARRIBA;
        mapa.setPos(pos);

        return res;
    }

    @Override
    public boolean moverAbajo() {
        Cosa item = null;
        boolean res = false;

        if (super.moverAbajo()) {
            res = chequeo_items_y_progreso(item);
        } else {

            item = mapa.getByPosition(this.pos.posAbajo());

            // 1 - Hay algo en esa casilla?
            if (item != null) {
                this.mapa.removeCosa(item.getPos());
                res = item.moverAbajo();
                this.mapa.actualizarCosa(item);

                super.moverAbajo();
            } else
                res = false;
        }

        orientacionActual = Constantes.ABAJO;
        mapa.setPos(pos);

        return res;
    }

    // Metodos

    private boolean chequeo_items_y_progreso(Cosa item) {
        if ((item = mapa.getByPosition(pos.getPos())) != null) {

            mapa.getByPosition(pos.getPos()).setRecogido();
            inventario.add(item);

            if (item.queSoy == "item_pasar_de_nivel") {
                completoNivel = true;
            }

            cantBalas++;

            if (inventario.size() == mapa.getItemsObjetivo()) {
                mapa.habilitarItemParaPasarNivel();
            } else if (inventario.size() > mapa.getItemsObjetivo() && !mapa.getPuertaHabilitada()) {
                mapa.habilitarPasarNivel();
            }
        }
        return true;
    }

    public Bala disparar() {
        if (cantBalas > 0) {
            Bala bala = new Bala(new Posicion(this.pos.getX(), this.pos.getY()), orientacionActual, mapa);
            mapa.addBala(bala);
            cantBalas--;

            return bala;
        }

        return null;
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
        runningAnimationDown.setCustomFrames(new int[] { 3, 4, 3, 2 });
        runningAnimationLeft = new SpriteAnimation(render, animationSpeed, 5, 4, 7, 0, 0, imageSpacing, width, height);
        runningAnimationLeft.setCustomFrames(new int[] { 10, 11, 10, 9 });
        runningAnimationUp = new SpriteAnimation(render, animationSpeed, 5, 4, 7, 0, 0, imageSpacing, width, height);
        runningAnimationUp.setCustomFrames(new int[] { 17, 18, 17, 16 });
        runningAnimationRight = new SpriteAnimation(render, animationSpeed, 5, 4, 7, 0, 0, imageSpacing, width, height);
        runningAnimationRight.setCustomFrames(new int[] { 24, 25, 24, 23 });
    }

    public void step() {
        if (!estaMuerto && !estaMoviendose) {
            estaMoviendose = true;
            switch (control.getDirection()) {
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
    }

    public void setOrientacion(int orientacion) {
        this.orientacionActual = orientacion;
    }

    public void setCantBalas(int cant) {
        this.cantBalas = cant;
    }

    // Getters

    public List<Cosa> getInventario() {
        return inventario;
    }


    public void tocarDerecha(){
        control.setDirection(Direction.RIGHT);
        this.step();
        control.releaseDirection(Direction.RIGHT);
    }
    public void tocarAbajo(){
        control.setDirection(Direction.DOWN);
        this.step();
        control.releaseDirection(Direction.DOWN);
    }

    public void tocarIzquierda(){
        control.setDirection(Direction.LEFT);
        this.step();
        control.releaseDirection(Direction.LEFT);
    }

    public void tocarArriba(){
        control.setDirection(Direction.UP);
        this.step();
        control.releaseDirection(Direction.UP);
    }


}
