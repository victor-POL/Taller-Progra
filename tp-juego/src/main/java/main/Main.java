package main;

import java.util.ArrayList;
import java.util.List;

import animation.Control;
import animation.Direction;
import entidad.Cosa;
import entidad.Enemigo;
import entidad.Jugador;
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
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import mapa.Mapa;
import niveles.Nivel;
import utiles.Posicion;

public class Main extends Application {
    private static final int TILE = 32;
    private static final double NANOS_IN_SECOND_D = 1_000_000_000.0;
    private static final int TILE_WIDTH = 32;
    private static final int TILE_HEIGHT = 32;
    private static final int X_TILES = 13;
    private static final int Y_TILES = 13;
    long previousNanoFrame;

    Jugador jugador;
    Mapa mapa;
    Scene currentScene;
    Stage stage;
    Group root;
    String level = "nivel_1";

    Control control;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        root = new Group();
        currentScene = new Scene(root);

        Nivel nivel = new Nivel(level);
        mapa = nivel.getMapa();
        jugador = nivel.getPlayer();
        System.out.println(jugador.getPos());

        control = nivel.getControl();

        root.getChildren().add(mapa.getRender());
        root.getChildren().add(jugador.getRender());

        for (Posicion p : mapa.getCosas().keySet()) {
            Cosa c = mapa.getCosas().get(p);
            ImageView iv = (ImageView) c.getRender();
            iv.setX(p.getX() * TILE);
            iv.setY(p.getY() * TILE);
            root.getChildren().add(c.getRender());
        }

        for (Posicion p : mapa.getEnemigos().keySet()) {
            Enemigo c = mapa.getEnemigos().get(p);
            ImageView iv = (ImageView) c.getRender();
            iv.setX(p.getX() * TILE);
            iv.setY(p.getY() * TILE);
            root.getChildren().add(c.getRender());
        }

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
        // set the window size to 900x900
        stage.setResizable(true);
         stage.setWidth(900);
         stage.setHeight(900);

        stage.setScene(currentScene);
        stage.setTitle("Testigos de Java");

        stage.setScene(currentScene);
//        stage.setFullScreen(true);
        stage.show();
        addInputEvents();
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
                    case UP:
                    case W:
                        control.setDirection(Direction.UP);
                        jugador.step();
                        break;
                    case DOWN:
                    case S:
                        control.setDirection(Direction.DOWN);
                        jugador.step();
                        break;
//                    case M:
//                        mapa.redraw();
//                        break;
                    case X:
                        jugador.disparar();
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

    private void addUpdateEachFrameTimer() {
        previousNanoFrame = System.nanoTime();
        AnimationTimer gameTimer = new AnimationTimer() {
            @Override
            public void handle(long currentNano) {
                // Update tick
                update((currentNano - previousNanoFrame) / NANOS_IN_SECOND_D);
                previousNanoFrame = currentNano;
            }
        };
        gameTimer.start();
    }

    protected void update(double deltaTime) {
        if (jugador.update(deltaTime)) {
            root.getChildren().clear();
            if (level == "nivel_1") {
                level = "nivel_2";
            } else {
                // show game over on screen
                root.getChildren().add(new Text(100, 100, "Game Over"));
                System.exit(0);

            }
            start(stage);

        }

        List<Cosa> cosasASacar = new ArrayList<>();
        List<Enemigo> enemigosASacar = new ArrayList<>();

        for (Posicion p : mapa.getEnemigos().keySet()) {
            Enemigo e = mapa.getEnemigos().get(p);
            e.update(deltaTime);
            if (e.Muerto()) {
                enemigosASacar.add(e);
            }
        }
        for (Enemigo e : enemigosASacar) {
            mapa.getEnemigos().remove(e.getPos());
            root.getChildren().remove(e.getRender());
        }
        for (Posicion p : mapa.getCosas().keySet()) {
            Cosa c = mapa.getCosas().get(p);
            c.update(deltaTime);
            if (c.fueRecogido) {
                cosasASacar.add(c);
            }
        }
        for (Cosa c : cosasASacar) {
            mapa.getCosas().remove(c.getPos());
            root.getChildren().remove(c.getRender());
        }
    }

    public static void main(String[] args) {
        AudioClip a = new AudioClip("file:src/main/resources/musica/musicaDeFondo.mp3");
        a.play();
        launch(args);
    }
}
