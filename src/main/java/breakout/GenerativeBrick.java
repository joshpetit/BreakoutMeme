package breakout;

/**
 * A Brick that generates a new ball on destruction.
 *
 * @author Joshua Petitma
 */
public class GenerativeBrick extends Brick {

  /**
   * Constructs a generative brick.
   *
   * @param listener - The interface the brick will communicate with the platform when a new ball
   *     should be created.
   */
  public GenerativeBrick(ActionListener listener) {
    super(listener);
    setImage("brickBall.png");
    this.command =
        (event) -> {
          switch (event.getStrickedType()) {
            case BALL:
            case HOT_BALL:
              listener.createBall();
              listener.addPoints(1);
              destroy();
              break;
            default:
              break;
          }
        };
  }
}
