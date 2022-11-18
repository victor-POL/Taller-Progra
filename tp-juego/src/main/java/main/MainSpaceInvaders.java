package main;

import animation.Control;
import animation.Direction;
import entidad.Bala;
import entidad.JugadorLolo;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.MediaPlayer;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import mapa.Mapa;
import mapa.MapaSpace;
import niveles.Nivel;
import utiles.NomJuegos;

public class MainSpaceInvaders extends Application {

    public static final int TILE = 16;
    private static final double NANOS_IN_SECOND_D = 1_000_000_000.0;
    private static final int TILE_WIDTH = 16;
    private static final int TILE_HEIGHT = 16;
    private static final int X_TILES = 45;
    private static final int Y_TILES = 45;
    int vidasAct = 3;
    long previousNanoFrame;
    AnimationTimer gameTimer;
    static MediaPlayer mediaPlayer;

    JugadorLolo jugador;
    MapaSpace mapa;
    Scene currentScene;
    Stage stage;
    Group root;

    Control control;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        root = new Group();
        currentScene = new Scene(root, X_TILES * TILE_WIDTH, Y_TILES * TILE_HEIGHT);
        Nivel nivel;
        nivel = new Nivel("nivel1", NomJuegos.SPACE_INVADERS);
        jugador = nivel.getPlayer();
        mapa = nivel.getMapaSpace();
        control = nivel.getControl();

        root.getChildren().add(mapa.getRender());

        ImageView playerRender = (ImageView) jugador.getRender();

        playerRender.setX(jugador.getPos().getX() * TILE_WIDTH);
        playerRender.setY(jugador.getPos().getY() * TILE_HEIGHT);

        root.getChildren().add(jugador.getRender());

        addUpdateEachFrameTimer();

        Scale scale = new Scale(1, 1);
        scale.setPivotX(0);
        scale.setPivotY(0);
        root.getTransforms().setAll(scale);

        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> {
            double scaleX = currentScene.getWidth() / (X_TILES * TILE_WIDTH);
            double scaleY = currentScene.getHeight() / (Y_TILES * TILE_HEIGHT);

            scale.setX(scaleX);
            scale.setY(scaleY);
        };

        currentScene.widthProperty().addListener(stageSizeListener);
        currentScene.heightProperty().addListener(stageSizeListener);
        stage.setMinWidth(X_TILES * TILE_WIDTH);
        stage.setMinHeight(Y_TILES * TILE_HEIGHT);
        stage.setResizable(true);

        stage.setScene(currentScene);
        stage.setTitle("Space Invaders");
        stage.show();

        addInputEvents();

    }

    private void addUpdateEachFrameTimer() {
        previousNanoFrame = System.nanoTime();
        gameTimer = new AnimationTimer() {
            @Override
            public void handle(long currentNano) {
                // Update tick
                // update((currentNano - previousNanoFrame) / NANOS_IN_SECOND_D);
                update((currentNano - previousNanoFrame) / NANOS_IN_SECOND_D);
                previousNanoFrame = currentNano;
            }
        };
        gameTimer.start();
    }

    private void addInputEvents() {
        currentScene.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                switch (e.getCode()) {
                    case RIGHT:
                    case D:
                        control.setDirection(Direction.RIGHT);
                        jugador.step();
                        break;
                    case LEFT:
                    case A:
                        control.setDirection(Direction.LEFT);
                        jugador.step();
                        break;
                    case SPACE:
                        Bala b;
                        if ((b = jugador.disparar()) != null) {
                            AudioClip audio = new AudioClip(getClass().getResource("/sonido/laser1.wav").toString());
                            audio.play();
                            ImageView iv = (ImageView) b.getRender();
                            iv.setX(b.getPos().getX() * TILE);
                            iv.setY(b.getPos().getY() * TILE);
                            root.getChildren().add(b.getRender());
                        }
                        break;
                    default:
                        break;
                }
            }
        });
        currentScene.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                switch (e.getCode()) {
                    case RIGHT:
                    case D:
                        control.releaseDirection(Direction.RIGHT);
                        break;
                    case LEFT:
                    case A:
                        control.releaseDirection(Direction.LEFT);
                        break;
                    case UP:
                    case W:
                        control.releaseDirection(Direction.UP);
                        break;
                    case DOWN:
                    case S:
                        control.releaseDirection(Direction.DOWN);
                        break;

                    default:
                        break;
                }
            }
        });

        // Pantalla completa con alt + enter
        currentScene.getAccelerators().put(new KeyCodeCombination(KeyCode.ENTER, KeyCombination.ALT_DOWN),
                new Runnable() {
                    @Override
                    public void run() {
                        stage.setFullScreen(!stage.isFullScreen());
                    }
                });
    }

    public void update(double deltaTime) {

    }

    public static void main(String[] args) {
        launch(args);
    }

}