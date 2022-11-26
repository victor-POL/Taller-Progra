package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utiles.NomJuegos;

// Fijarse si queremos hacer una interfaz grafica para que el usuario seleccione el juego.
// Hay una simple interfaz hecha en la funcion start.
public class SuperMain extends Application implements NomJuegos {
    Stage stage;
    Group root;
    Scene currentScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws FileNotFoundException {
        MediaPlayer media = new MediaPlayer(
                new Media(new File("src/main/resources/menuMusic.mp3").toURI().toString()));
        media.setVolume(0.5);
        media.setCycleCount(MediaPlayer.INDEFINITE);
        media.play();
        this.stage = new Stage();
        Button button_bobo = new Button("BOBO");
        Button button_space = new Button("SPACE");

        button_bobo.setLayoutX(400);
        button_bobo.setLayoutY(350);

        button_space.setLayoutX(400);
        button_space.setLayoutY(400);

        button_bobo.setOnAction(e -> {
            MainLolo ma = new MainLolo();
            this.stage.close();
            this.stage = new Stage();
            ma.start(this.stage);

        });

        button_space.setOnAction(e -> {
            MainSpaceInvaders ma = new MainSpaceInvaders();
            this.stage.close();
            this.stage = new Stage();
            ma.start(this.stage);

        });

        // ImageView fondoMenu = new ImageView(new
        // Image("file:src/main/resources/Space_Invaders/FondoMenu.jpg"));
        // fondoMenu.setFitHeight(700);
        // fondoMenu.setFitWidth(700);
        //
        // fondoMenu.setOnMouseClicked(e -> {
        // MainLolo ma = new MainLolo();
        // this.stage.close();
        // this.stage = new Stage();
        // ma.start(this.stage);
        //
        // });

        ImageView fondoBobo = new ImageView(new Image("file:src/main/resources/Space_Invaders/FondoBoboBlur.jpg"));
        fondoBobo.setX(0);
        fondoBobo.setY(0);

        ImageView fondoSpace = new ImageView(new Image("file:src/main/resources/Space_Invaders/FondoSpaceBlur.jpg"));
        fondoSpace.setX(0);
        fondoSpace.setY(350);

        fondoBobo.setOnMouseClicked(e -> {
            MainLolo ma = new MainLolo();
            this.stage.close();
            this.stage = new Stage();
            media.stop();
            ma.start(this.stage);

        });

        fondoBobo.setOnMouseEntered(e -> {
            Image imagen = new Image("file:src/main/resources/Space_Invaders/FondoBobo.jpg");
            fondoBobo.setImage(imagen);

        });

        fondoBobo.setOnMouseExited(e -> {
            Image imagen = new Image("file:src/main/resources/Space_Invaders/FondoBoboBlur.jpg");
            fondoBobo.setImage(imagen);

        });

        fondoSpace.setOnMouseClicked(e -> {
            MainSpaceInvaders ma = new MainSpaceInvaders();
            this.stage.close();
            this.stage = new Stage();
            media.stop();
            ma.start(this.stage);

        });

        fondoSpace.setOnMouseEntered(e -> {
            Image imagen = new Image("file:src/main/resources/Space_Invaders/FondoSpace.jpg");
            fondoSpace.setImage(imagen);

        });

        fondoSpace.setOnMouseExited(e -> {
            Image imagen = new Image("file:src/main/resources/Space_Invaders/FondoSpaceBlur.jpg");
            fondoSpace.setImage(imagen);

        });

        root = new Group();
        root.getChildren().add((Node) fondoBobo);
        root.getChildren().add((Node) fondoSpace);

        // root.getChildren().add(button_bobo);
        // root.getChildren().add(button_space);

        this.stage.setScene(new Scene(root, 700, 700));
        this.stage.setTitle("Menu");
        this.stage.show();

    }

}
