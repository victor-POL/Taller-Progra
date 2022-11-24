package main;

import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import utiles.NomJuegos;

// Fijarse si queremos hacer una interfaz grafica para que el usuario seleccione el juego.
// Hay una simple interfaz hecha en la funcion start.
public class SuperMain extends Application implements NomJuegos {
    Stage stage;
    Group root;
    Scene currentScene;

    public static void main(String[] args) {

        // Para que salga la interfaz hay que comentar todo esto
        // Dejando nada mas el launch(args);

        System.out.println("A que juego queres jugar?");
        System.out.println(NomJuegos.BOBO + " => lolo");
        System.out.println(NomJuegos.SPACE_INVADERS + " => Space");

        Scanner scanner = new Scanner(System.in);

        int res = scanner.nextInt();

        switch (res) {
            case NomJuegos.BOBO:
                MainLolo.main(args);
                break;
            case NomJuegos.SPACE_INVADERS:
                MainSpaceInvaders.main(args);
            default:
                break;
        }

        scanner.close();

//        launch(args);
    }

    @Override
    public void start(Stage stage) {

        stage.setTitle("Testigos de Java");
        Button button_bobo = new Button("BOBO");
        Button button_space = new Button("SPACE");

        button_bobo.setLayoutX(120);
        button_bobo.setLayoutY(50);

        button_space.setLayoutX(120);
        button_space.setLayoutY(150);

        button_bobo.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                MainLolo.main(null);
            }
        });

        button_space.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Falta implementar...");
//                MainSpaceInvaders.main(null);
            }
        });

        root = new Group();
        root.getChildren().add(button_bobo);
        root.getChildren().add(button_space);
        stage.setScene(new Scene(root, 300, 250));
        stage.show();

    }

}
