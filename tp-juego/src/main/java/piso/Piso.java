package piso;

import javafx.scene.image.Image;

public class Piso {
    private boolean esColisionable;
    private Image imagen;
    private String tipoDePiso;

    // Constructores

    public Piso(String imagen, boolean colisionable, String tipoDePiso) {
        this.esColisionable = colisionable;
        this.imagen = new Image(imagen);
        this.tipoDePiso = tipoDePiso;
    }

    // Getters

    public Image getImage() {
        return imagen;
    }

    public boolean esColisionable() {
        return esColisionable;
    }

    public String getTipoDePiso(){
        return this.tipoDePiso;
    }
}
