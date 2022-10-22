package animation;

public class Control {
    boolean directionRight = false;
    boolean directionLeft = false;
    boolean directionUp = false;
    boolean directionDown = false;

    public void setDirection(Direction direction) {
        switch (direction) {
        case DOWN:
            directionDown = true;
            break;
        case LEFT:
            directionLeft = true;
            break;
        case RIGHT:
            directionRight = true;
            break;
        case UP:
            directionUp = true;
            break;
        default:
            break;
        }
    }

    public void releaseDirection(Direction direction) {
        switch (direction) {
        case DOWN:
            directionDown = false;
            break;
        case LEFT:
            directionLeft = false;
            break;
        case RIGHT:
            directionRight = false;
            break;
        case UP:
            directionUp = false;
            break;
        default:
            break;
        }
    }

    public Direction getDirection() {
        return directionRight ? Direction.RIGHT
                : (directionLeft ? Direction.LEFT
                        : (directionUp ? Direction.UP : (directionDown ? Direction.DOWN : Direction.NONE)));
    }
}