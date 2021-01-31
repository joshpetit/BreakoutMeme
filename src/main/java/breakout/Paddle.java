package breakout;

public class Paddle extends GameObject {

  ActionListener listener;

  public Paddle(int speed, ActionListener listener) {
    super(speed, 0, 0, GameObject.TYPE.PADDLE, "paddle.png");
    this.listener = listener;
    this.command = (e) -> {
      if (e.getStrickedType() == TYPE.WALL) {
        if ((getX() <= 0 && this.directionX == -1)
            || (getX() >= listener.getWidth() - getBoundsInParent().getWidth()
            && this.directionX == 1)) {
          setDirectionX(0);
        }
      }
    };
  }
}
