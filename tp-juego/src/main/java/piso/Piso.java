package piso;

import javafx.scene.image.Image;

public class Piso {
    private boolean esColisionable;
    private Image imagen;
    private String queSoy;

    // Constructores

    public Piso(String imagen, boolean colisionable, String queSoy) {
        this.esColisionable = colisionable;
        this.imagen = new Image(imagen);
        this.queSoy = queSoy;
    }

    // Getters

    public Image getImage() {
        return imagen;
    }

    public boolean esColisionable() {
        return esColisionable;
    }

    public String getQueSoy(){
        return this.queSoy;
    }
}
