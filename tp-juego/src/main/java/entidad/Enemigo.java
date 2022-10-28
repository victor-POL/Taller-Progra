package entidad;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mapa.Mapa;
import utiles.Constantes;
import utiles.Posicion;

public class Enemigo extends Entidad {
    private double count = 0;
    private int orientacion = 0;

    // Constructores

    public Enemigo(double paso, Posicion pos, Mapa map, int orientacion) {
        super(paso, pos, map);
        this.render = new ImageView(new Image("file:src/main/resources/sprites/enemigos/enemigo.png"));
        this.orientacion = orientacion;
    }

    // Metodos

    public BalaEnemigo disparar(int direccion) {
        switch (direccion) {
            case Constantes.ABAJO:
                return new BalaEnemigo(new Posicion(pos.getX(), pos.getY() + 0.5), direccion, mapa);
            case Constantes.ARRIBA:
                return new BalaEnemigo(new Posicion(pos.getX(), pos.getY() - 0.5), direccion, mapa);
            case Constantes.DER:
                return new BalaEnemigo(new Posicion(pos.getX() + 0.5, pos.getY()), direccion, mapa);
            case Constantes.IZQ:
                return new BalaEnemigo(new Posicion(pos.getX() - 0.5, pos.getY()), direccion, mapa);
        }
        return null;
    }

    // JavaFX

    public boolean update(double deltaTime, Group root) {
        if(estaMuerto){
            root.getChildren().remove(getRender());
            return false;
        }
        if (count > 0.5) {
            BalaEnemigo b = disparar(orientacion);
            mapa.addBala(b);
            root.getChildren().add(b.getRender());
            count = 0;
        } else
            count += deltaTime;
        return true;
    }

    // Setters

    public void setEstaMuerto() {
        this.estaMuerto = true;
    }

}
