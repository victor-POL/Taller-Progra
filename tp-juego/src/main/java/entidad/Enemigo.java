package entidad;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mapa.Mapa;
import utiles.Posicion;

public class Enemigo extends Entidad {

	private boolean estaMuerto = false;

	// Constructoresw
	
	public Enemigo(double paso, Posicion pos, Mapa map) {
		super(paso, pos, map);
		this.render = new ImageView(new Image("file:src/main/resources/enemigos/villanoGoofy.png"));
	}


	public void setMuerto(){
		estaMuerto = true;
	}

	public boolean Muerto(){
		return this.estaMuerto;
	}
	
	public void update(double deltaTime){
	
	}

}
