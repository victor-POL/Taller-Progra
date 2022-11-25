package entidad;

import java.util.Iterator;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mapa.Mapa;
import mapa.MapaSpace;
import utiles.Constantes;
import utiles.Posicion;

public class Enemigo extends Entidad {
    private double count = 0;
    private int orientacion = 0;
    Mapa mapa;
    MapaSpace mapaSpace;

    // Constructores

    public Enemigo(double paso, Posicion pos, Mapa map, int orientacion) {
        super(paso, pos, map);
        this.mapa = map;
        this.render = new ImageView(new Image("file:src/main/resources/sprites/enemigos/enemigo.png"));
        this.orientacion = orientacion;
    }

    public Enemigo(double paso, Posicion pos, MapaSpace map, int orientacion) {
        super(paso, pos, map);
        this.mapaSpace = map;
        this.render = new ImageView(new Image("file:src/main/resources/Space_Invaders/enemigo.png"));
        this.orientacion = orientacion;
        this.count = pos.getX() / 2.0;
    }

    // Metodos

    public BalaEnemigo disparar(int direccion) {
        if (mapa != null) {
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
        } else {
            switch (direccion) {
                case Constantes.ABAJO:
                    return new BalaEnemigo(new Posicion(pos.getX(), pos.getY() + 0.5), direccion, mapaSpace);
                case Constantes.ARRIBA:
                    return new BalaEnemigo(new Posicion(pos.getX(), pos.getY() - 0.5), direccion, mapaSpace);
                case Constantes.DER:
                    return new BalaEnemigo(new Posicion(pos.getX() + 0.5, pos.getY()), direccion, mapaSpace);
                case Constantes.IZQ:
                    return new BalaEnemigo(new Posicion(pos.getX() - 0.5, pos.getY()), direccion, mapaSpace);
            }
        }

        return null;
    }

    // JavaFX

    public boolean update(double deltaTime, Group root) {
        if (estaMuerto) {
            root.getChildren().remove(getRender());
            return false;
        }

        if (mapa != null) {
            // Es lolo
            if (count > 0.5) {
                BalaEnemigo b = disparar(orientacion);
                mapa.addBala(b);

                root.getChildren().add(b.getRender());
                count = 0;
            } else
                count += deltaTime;
        } else {
            boolean puedeDisparar = true;
            Iterator<Enemigo> it = mapaSpace.getEnemigos().values().iterator();
            while (it.hasNext()) {
                Enemigo e = it.next();
                if ((e.getPos().getY() > this.getPos().getY() && e.getPos().getX() == this.getPos().getX())
                        || mapaSpace.getBalas().size() > 5)
                    puedeDisparar = false;
            }

            if (puedeDisparar && count > Math.random() * 60) {
                BalaEnemigo b = disparar(orientacion);
                mapaSpace.addBala(b);

                root.getChildren().add(b.getRender());
                count = 0;
            } else
                count += deltaTime;
        }

        return true;
    }

    // Setters

    public void setEstaMuerto() {
        this.estaMuerto = true;
    }

}
