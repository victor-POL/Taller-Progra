package piso;

import javafx.scene.image.Image;

public class Piso {
    private boolean esColisionable;
    private Image imagen;

    // Constructores

    public Piso(String imagen, boolean colisionable) {
        this.esColisionable = colisionable;
        this.imagen = new Image(imagen);
    }

    // Getters

    public Image getImage() {
        return imagen;
    }

    public boolean esColisionable() {
        return esColisionable;
    }
}
