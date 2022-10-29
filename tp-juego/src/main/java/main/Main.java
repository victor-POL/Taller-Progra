package main;

import java.io.File;
import java.util.Iterator;
import java.util.Scanner;

import animation.Control;
import animation.Direction;
import entidad.Bala;
import entidad.Cosa;
import entidad.Enemigo;
import entidad.Jugador;
import hilos.solutionThread;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import mapa.Mapa;
import niveles.Nivel;
import utiles.Posicion;

public class Main extends Application {
    public static final int TILE = 32;
    private static final double NANOS_IN_SECOND_D = 1_000_000_000.0;
    private static final int TILE_WIDTH = 32;
    private static final int TILE_HEIGHT = 32;
    private static final int X_TILES = 14;
    private static final int Y_TILES = 13;
    int vidasAct = 3;
    long previousNanoFrame;
    boolean setear = false;
    static boolean simu = false;
    AnimationTimer gameTimer;
    static MediaPlayer mediaPlayer;
    solutionThread st;

    Jugador jugador;
    Mapa mapa;
    Scene currentScene;
    Stage stage;
    Group root;
    String level = "nivel_1";

    Control control;

    @Override
    public void start(Stage stage) {
        if (level.equals("final")) {
            mediaPlayer.stop();
            mediaPlayer = new MediaPlayer(
                    new Media(new File("src/main/resources/sonido/musica/musicaDeVictoria.mp3").toURI().toString()));
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setVolume(0.3);
            mediaPlayer.play();
            stage.close();
            this.stage = new Stage();
            root.getChildren().clear();
            root = new Group();
            currentScene = new Scene(root, 220, 75);
            this.stage.setScene(currentScene);
            Text text = new Text(0, 50, "GANASTE");
            text.setFont(new Font(STYLESHEET_CASPIAN, 50));
            ImageView fondoGanaste = new ImageView(new Image("file:src/main/resources/sprites/caminables/pasto.png"));
            fondoGanaste.setFitHeight(75);
            fondoGanaste.setFitWidth(220);
            root.getChildren().add((Node)fondoGanaste);
            root.getChildren().add(text);
            this.stage.show();
            return;
        }
        this.stage = stage;
        root = new Group();
        currentScene = new Scene(root);
        Nivel nivel;
        nivel = new Nivel(level);
        jugador = nivel.getPlayer();
        jugador.setVidas(vidasAct);
        mapa = nivel.getMapa();
        control = nivel.getControl();
        root.getChildren().add(mapa.getRender());

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
        if (!setear) {
            stage.setWidth(720);
            stage.setHeight(720);
            setear = true;
        }

        stage.setScene(currentScene);
        stage.setTitle("Testigos de Java");

        stage.setScene(currentScene);
        root.getChildren().add(new Text(416, 100, "Vidas\n-----\n" + vidasAct));
        root.getChildren().add(new Text(416, 200, "Nivel\n-----\n" + level.substring(level.length() - 1)));
        stage.show();
        addInputEvents();
        onClose();
        // jugador.solucionNivel();
        if (simu == true) {
            st = new solutionThread(jugador, level);
            st.start();
        }
    }

    private void onClose() {
        stage.setOnCloseRequest(event -> {
            // System.out.println("Stage is closing");
            if (simu)
                st.stop();
        });
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
                    case R:
                        jugador.setDead(true);
                        break;
                    case X:
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

    protected void update(double deltaTime) {
        if (jugador.estaMuerto()) {
            vidasAct--;
            if (jugador.getVidas() == 0) {
                level = "nivel_1";
                vidasAct = 3;
            }
            root.getChildren().clear();
            start(stage);
            gameTimer.stop();
        }
        if (jugador.completoNivel) {
            root.getChildren().clear();
            if(!level.equals("final")) {
                this.avanzar_nivel();
                start(stage);
                gameTimer.stop();
            }
                
        }

        // update mapa, tiles que cambiaron, etc
        mapa.update(deltaTime);

        // update balas
        Iterator<Bala> it = mapa.getBalas().iterator();
        while (it.hasNext()) {
            Bala b = it.next();
            b.update(deltaTime, root);
        }

        // update enemigos
        Iterator<Enemigo> it2 = mapa.getEnemigos().values().iterator();
        while (it2.hasNext()) {
            Enemigo e = it2.next();
            if (!e.update(deltaTime, root))
                it2.remove();
        }

        // update cosas
        Iterator<Cosa> it3 = mapa.getCosas().values().iterator();
        while (it3.hasNext()) {
            Cosa c = it3.next();
            if (!c.update(deltaTime, root))
                it3.remove();
        }
    }

    private void avanzar_nivel() {
        switch (level) {
            case "nivel_1":
                level = "nivel_2";
                break;
            case "nivel_2":
                level = "nivel_3";
                break;
            case "nivel_3":
                level = "nivel_4";
                break;
            case "nivel_4":
                level = "nivel_5";
                break;
            case "nivel_5":
                level = "nivel_6";
                break;
            case "nivel_6":
                level = "nivel_7";
                break;
            case "nivel_7":
                level = "final";
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String option = "";

        System.out.println("Como quieres jugar? (T : Teclas, S : Simulado)");
        option = sc.next();
        option = option.toLowerCase();

        while (!option.equals("s") && !option.equals("t")) {
            System.out.println("Opcion invalida");
            System.out.println("Las opciones son ----> T : Teclas, S : Simulado");
            option = sc.next();
            option = option.toLowerCase();
            System.out.println(option);
        }

        sc.close();

        if (option.equals("s"))
            simu = true;

        mediaPlayer = new MediaPlayer(
                new Media(new File("src/main/resources/sonido/musica/musicaDeFondo.mp3").toURI().toString()));
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(0.1);
        mediaPlayer.play();

        launch(args);

    }

}
