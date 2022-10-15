package piso;

public class Piso {
	private String sprite;
	private boolean esColisionable;

	// Constructores

	public Piso(String sprite, boolean colisionable) {
		this.sprite = sprite;
		this.esColisionable = colisionable;
	}

	// Getters
	
	public String getSprite() {
		return sprite;
	}

	public boolean esColisionable() {
		return esColisionable;
	}
}
