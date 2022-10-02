package piso;

public class Piso {
	private String sprite;
	private boolean collisionable;

	public Piso() {
		sprite = "pasto";
		collisionable = false;
	}

	public Piso(String sprite) {
		this.sprite = sprite;
		collisionable = false;
	}

	public Piso(String sprite, boolean collisionable) {
		this.sprite = sprite;
		this.collisionable = collisionable;
	}

	public String getSprite() {
		return sprite;
	}

	public boolean isCollisionable() {
		return collisionable;
	}
}
