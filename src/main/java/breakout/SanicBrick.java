package breakout;

/**
 * A Brick that speeds up the paddles. GOTTA GO FAST!
 */
public class SanicBrick extends Brick {

  /**
   * Constructs a SanicBrick.
   *
   * @param listener - The interface the brick will communicate when paddles should speed up.
   */
  public SanicBrick(ActionListener listener) {
    super(listener);
    setImage("brickSanic.png");
    this.command =
        (event) -> {
          switch (event.getStrickedType()) {
            case BALL:
            case HOT_BALL:
              if (!fallen) {
                fall();
                listener.addPoints(3);
                setImage("sanic.png");
              }
              break;
            case PADDLE:
              destroy();
              listener.speedBoost();
              break;
            case WALL:
            case HOT_WALL:
              destroy();
              break;
            default:
              break;
          }
        };
  }
}
