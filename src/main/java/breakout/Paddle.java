package breakout;

/** A paddle which cannot exit the bounds of the platform */
public class Paddle extends GameObject {

  ActionListener listener;

  /**
   * Constructs a paddle
   *
   * @param speed - How fast the paddle will move within the gameloop.
   * @param listener - Used to retrieve information about the platform dimensions.
   */
  public Paddle(int speed, ActionListener listener) {
    super(speed, 0, 0, GameObject.TYPE.PADDLE, "paddle.png");
    this.listener = listener;
    this.command =
        (e) -> {
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
