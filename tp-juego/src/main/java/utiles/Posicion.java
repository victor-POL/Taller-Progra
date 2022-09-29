package utiles;


public class Posicion implements Comparable<Posicion> {
	private double x, y;
	public Posicion(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return x;
    }
    public double getY() {
        return y;
    }
    public void setX(double x) {
        this.x = x;
    }
    public void setY(double y) {
        this.y = y;
    }
    public void setPos(double x, double y) {
        this.x = x;
        this.y = y;
    }
    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posicion other = (Posicion) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
	public void setPos(Posicion pos) {
        this.x = pos.getX();
        this.y = pos.getY();
    }
    public Posicion getPos() {
        return new Posicion(x, y);
    }

    @Override
    public String toString() {
        return "Posicion [x=" + x + ", y=" + y + "]";
    }
    public int compareTo(Posicion o) {
        if (Double.doubleToLongBits(this.x) == Double.doubleToLongBits(o.x)) {
            if (Double.doubleToLongBits(this.y) == Double.doubleToLongBits(o.y)) {
                return 0;
            } else if (Double.doubleToLongBits(this.y) > Double.doubleToLongBits(o.y)) {
                return 1;
            } else {
                return -1;
            }
        } else if (Double.doubleToLongBits(this.x) > Double.doubleToLongBits(o.x)) {
            return 1;
        } else {
            return -1;
        }
    }

}
