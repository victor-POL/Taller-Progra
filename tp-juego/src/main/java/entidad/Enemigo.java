package entidad;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mapa.Mapa;
import utiles.Constantes;
import utiles.Posicion;

public class Enemigo extends Entidad {

    private boolean estaMuerto = false;
    private double count = 0;

    // Constructores

    public Enemigo(double paso, Posicion pos, Mapa map) {
        super(paso, pos, map);
        this.render = new ImageView(new Image("file:src/main/resources/enemigos/enemigo.png"));
    }

    public void setMuerto() {
        estaMuerto = true;
    }

    public boolean Muerto() {
        return this.estaMuerto;
    }
    
    public Bala disparar(int direccion) {
        return new Bala(new Posicion(pos.getX() ,pos.getY() + 0.5),direccion, map);
    }

    public Bala update(double deltaTime) {
        if(count > 0.5){
            Bala b = disparar(Constantes.ABAJO);
            map.addBala(b);
            count = 0;
            return b;
        }else
            count+=deltaTime;
        return null;
    }

}
