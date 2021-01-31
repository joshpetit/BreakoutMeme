package breakout;

/**
 * A Brick that gives health to the player.
 */
public class InvincibilityBrick extends Brick {

  /**
   * Constructs an InvincibilityBrick tha.
   *
   * @param listener - The interface the brick will communicate with the platform when the homework
   *                 pass should be applied.
   */
  public InvincibilityBrick(ActionListener listener) {
    super(listener);
    setImage("brickInvincibility.png");
    this.command =
        (event) -> {
          switch (event.getStrickedType()) {
            case BALL:
            case HOT_BALL:
              if (!fallen) {
                listener.addPoints(1);
                fall();
                setImage("homeworkpass.png");
              }
              break;
            case PADDLE:
              destroy();
              listener.toggleInvincibility();
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
