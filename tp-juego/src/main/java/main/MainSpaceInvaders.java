package main;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import animation.Control;
import animation.Direction;
import entidad.Bala;
import entidad.Cosa;
import entidad.Enemigo;
import entidad.JugadorSpace;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Paint;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import mapa.MapaSpace;
import niveles.Nivel;
import utiles.NomJuegos;
import utiles.Posicion;

public class MainSpaceInvaders extends Application {

    public static final int TILE = 16;
    private static final double NANOS_IN_SECOND_D = 1_000_000_000.0;
    private static final int TILE_WIDTH = 16;
    private static final int TILE_HEIGHT = 16;
    private static final int X_TILES = 45;
    private static final int Y_TILES = 45;
    private static final int DURATION = 180;
    int vidasAct = 3;
    long previousNanoFrame;
    AnimationTimer gameTimer;
    MediaPlayer mediaPlayer;
    boolean firstTime = true;
    boolean gane = false;

    JugadorSpace jugador;
    MapaSpace mapa;
    Scene currentScene;
    Stage stage;
    Group root;
    Label label;
    Control control;
    Label timeLabel;
    double time = DURATION; // 5 minutos

    @Override
    public void start(Stage primaryStage) {
        if (!gane) {
            label = new Label();
            label.setText("Vidas: " + vidasAct);
            label.setLayoutX(0);
            label.setLayoutY(0);
            label.setPrefSize(100, 20);
            label.setStyle("-fx-background-color: #000000; -fx-text-fill: #f54242;");

            timeLabel = new Label();
            timeLabel.setText("Tiempo: " + millisToMinSeg(time));
            timeLabel.setLayoutX(300);
            timeLabel.setLayoutY(0);
            timeLabel.setPrefSize(150, 20);
            timeLabel.setStyle("-fx-background-color: #000000; -fx-text-fill: #07fa18;");

            if (firstTime) {
                mediaPlayer = new MediaPlayer(
                        new Media(new File("src/main/resources/Space_Invaders/Space_Music.mp3").toURI().toString()));
                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaPlayer.setVolume(0.4);
                mediaPlayer.play();
                firstTime = false;
            }

            this.stage = primaryStage;
            root = new Group();
            currentScene = new Scene(root, X_TILES * TILE_WIDTH, Y_TILES * TILE_HEIGHT);
            Nivel nivel;
            nivel = new Nivel("nivel1", NomJuegos.SPACE_INVADERS);
            jugador = nivel.getPlayerSpace();
            mapa = nivel.getMapaSpace();
            mapa.setPlayer(jugador);
            control = nivel.getControl();

            root.getChildren().add(mapa.getRender());
            root.getChildren().add(label);
            root.getChildren().add(timeLabel);

            // root.getChildren().add(uiGT);

            ImageView playerRender = (ImageView) jugador.getRender();

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
                Enemigo e = mapa.getEnemigos().get(p);
                ImageView iv = (ImageView) e.getRender();
                iv.setX(p.getX() * TILE);
                iv.setY(p.getY() * TILE);
                root.getChildren().add(e.getRender());
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
            stage.setResizable(true);

            stage.setScene(currentScene);
            stage.setTitle("Space Invaders");
            stage.show();

            addInputEvents();
        } else {
            label = new Label();
            label.setText("Ganaste");
            label.setLayoutX(0);
            label.setLayoutY(0);
            label.setPrefSize(100, 20);
            label.setStyle("-fx-background-color: #000000; -fx-text-fill: #f54242;");
            root.getChildren().add(label);
            stage = new Stage();
            currentScene = new Scene(root, X_TILES * TILE_WIDTH, Y_TILES * TILE_HEIGHT);
            stage.setScene(currentScene);
            stage.setTitle("YOU WIN!");
            stage.show();
        }

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
        // jugador.update();
        if (jugador.estaMuerto()) {
            vidasAct--;
            gane = true;
            if (vidasAct == 0) {
                System.exit(0);
            }
            time = DURATION;
            root.getChildren().clear();
            start(stage);
            gameTimer.stop();
        }

        updateTime(deltaTime);

        mapa.update(deltaTime);

        // update balas
        Iterator<Bala> it = mapa.getBalas().iterator();
        while (it.hasNext()) {
            Bala b = it.next();
            Iterator<Bala> it2 = mapa.getBalas().iterator();
            while (it2.hasNext()) {
                Bala b2 = it2.next();
                if (!b.equals(b2) && b.getPos().equals(b2.getPos())) {
                    if (b.equals(jugador.getBala()) || b2.equals(jugador.getBala()))
                        jugador.eliminarBala();

                    mapa.getBalas().remove(b);
                    mapa.getBalas().remove(b2);
                    root.getChildren().remove(b.getRender());
                    root.getChildren().remove(b2.getRender());
                }
            }
            b.update(deltaTime, root);
        }

        // update enemigos
        Iterator<Enemigo> it3 = mapa.getEnemigos().values().iterator();
        while (it3.hasNext()) {
            Enemigo e = it3.next();
            if (!e.update(deltaTime, root))
                it3.remove();
        }

        if (mapa.getEnemigos().isEmpty()) {
            root.getChildren().clear();

            start(stage);
            gane = true;
            gameTimer.stop();
        }

        // update cosas
        Map<Posicion, Cosa> cosasAux = new ConcurrentHashMap<Posicion, Cosa>();
        Iterator<Cosa> it4 = mapa.getCosas().values().iterator();
        while (it4.hasNext()) {
            Cosa c = it4.next();
            if (c.update(deltaTime, root)) {
                it4.remove();
                cosasAux.put(c.getPos(), c);
            }
        }

        if (!cosasAux.isEmpty())
            mapa.getCosas().putAll(cosasAux);
    }

    public void updateTime(double deltaTime) {
        time -= deltaTime;
        if (time <= 0) {
            vidasAct--;
            time = DURATION;
            root.getChildren().clear();
            start(stage);
            gameTimer.stop();
        } else {
            root.getChildren().remove(timeLabel);
            timeLabel.setText(millisToMinSeg(time));
            if (time < 60) {
                timeLabel.setStyle("-fx-background-color: #000000; -fx-text-fill: #ed001c;");
            }
            root.getChildren().add(timeLabel);
        }

    }

    public String millisToMinSeg(double millis) {
        int min = (int) (millis / 60);
        int seg = (int) (millis % 60);
        return String.format("Tiempo restante : %02d:%02d", min, seg);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
