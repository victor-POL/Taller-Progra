package main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import animation.Control;
import animation.Direction;
import entidad.Bala;
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
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
    int counter = 0;
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

        control = nivel.getControl();

        root.getChildren().add(mapa.getRender());

        ImageView playerRender = (ImageView)jugador.getRender();

        playerRender.setX(jugador.getPos().getX() * TILE_WIDTH);
        playerRender.setY(jugador.getPos().getY() * TILE_HEIGHT);


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
                        Bala b;
                        if((b = jugador.disparar()) != null) {
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

    private void addUpdateEachFrameTimer() {
        previousNanoFrame = System.nanoTime();
        AnimationTimer gameTimer = new AnimationTimer() {
            @Override
            public void handle(long currentNano) {
                // Update tick
                //update((currentNano - previousNanoFrame) / NANOS_IN_SECOND_D);
                if(!update((currentNano - previousNanoFrame) / NANOS_IN_SECOND_D))
                    this.stop();
                previousNanoFrame = currentNano;
            }
        };
        gameTimer.start();
    }

    protected boolean update(double deltaTime) {
        if(jugador.isDead()){
            root.getChildren().clear();
            start(stage);
            return false; //to stop the timer

        }
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
            return false; // to stop the timer
        }
        
        //update mapa, tiles que cambiaron, etc
        mapa.update(deltaTime);
        List<Cosa> cosasASacar = new ArrayList<>();
        List<Enemigo> enemigosASacar = new ArrayList<>();
        List<Bala> balasASacar = new ArrayList<>();

        //update balas
        for (Bala b :  mapa.getBalas()) {
            if (!b.update(deltaTime)) {
                balasASacar.add(b);
            }
        }

        for(Bala b : balasASacar){
            mapa.getBalas().remove(b);
            root.getChildren().remove(b.getRender());
        }


        //update enemigos
        for (Posicion p : mapa.getEnemigos().keySet()) {
            Enemigo e = mapa.getEnemigos().get(p);
            Bala b = e.update(deltaTime);
            if(b != null){
                ImageView iv = (ImageView) b.getRender();
                iv.setX(b.getPos().getX() * TILE);
                iv.setY(b.getPos().getY() * TILE);
                root.getChildren().add(b.getRender());
            }
            if (e.Muerto()) {
                enemigosASacar.add(e);
            }
        }
        for (Enemigo e : enemigosASacar) {
            mapa.getEnemigos().remove(e.getPos());
            root.getChildren().remove(e.getRender());
        }
        
        //update cosas
        for (Posicion p : mapa.getCosas().keySet()) {
            Cosa c = mapa.getCosas().get(p);
            if(!root.getChildren().contains(c.getRender()))
                root.getChildren().add(c.getRender());
            c.update(deltaTime);
            if (c.fueRecogido) {
                cosasASacar.add(c);
            }
        }
        for (Cosa c : cosasASacar) {
            mapa.getCosas().remove(c.getPos());
            root.getChildren().remove(c.getRender());
        }

        System.out.println(counter);
        counter++;
        return true;
    }

    public static void main(String[] args) {
        ///AudioClip a = new AudioClip("file:src/main/resources/sonido/musica/musicaDeFondo.mp3");]
        //a.setCycleCount(AudioClip.INDEFINITE);
        //set intermediate volume
        //a.setVolume(0.5);
        MediaPlayer mediaPlayer = new MediaPlayer(new Media(new File("src/main/resources/sonido/musica/musicaDeFondo.mp3").toURI().toString()));
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.1);
        mediaPlayer.play();

        launch(args);
    }
}
