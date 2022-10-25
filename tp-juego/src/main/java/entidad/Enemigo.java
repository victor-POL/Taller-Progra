package entidad;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mapa.Mapa;
import utiles.Constantes;
import utiles.Posicion;

public class Enemigo extends Entidad {

    private boolean estaMuerto = false;
    private double count = 0;
    //private int tiros;
    private int orientacion = 0;

    // Constructores

    public Enemigo(double paso, Posicion pos, Mapa map, int orientacion) {
        super(paso, pos, map);
        this.render = new ImageView(new Image("file:src/main/resources/enemigos/enemigo.png"));
        this.orientacion = orientacion;
        //this.tiros = orientacion;
    }

    public void setMuerto() {
        estaMuerto = true;
    }

    public boolean Muerto() {
        return this.estaMuerto;
    }
    
    public BalaEnemigo disparar(int direccion) {
        switch(direccion) {
            case Constantes.ABAJO:
                return new BalaEnemigo(new Posicion(pos.getX() ,pos.getY() + 0.5),direccion, map);
            case Constantes.ARRIBA:
                return new BalaEnemigo(new Posicion(pos.getX() ,pos.getY() - 0.5),direccion, map);
            case Constantes.DER:
                return new BalaEnemigo(new Posicion(pos.getX() + 0.5 ,pos.getY()),direccion, map);
            case Constantes.IZQ:
                return new BalaEnemigo(new Posicion(pos.getX() - 0.5 ,pos.getY()),direccion, map);
        }
        return null;
    }

    public Bala update(double deltaTime) {
        if(count > 0.5){
            BalaEnemigo b = disparar(orientacion);
            map.addBala(b);
            count = 0;
            //tiros++;
            //orientacion = tiros % Constantes.ORIENTACIONES;
            return b;
        }else
            count+=deltaTime;
        return null;
    }

}
