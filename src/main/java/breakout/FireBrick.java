package breakout;

/**
 * A Brick that casts fires from the heavens.
 */
public class FireBrick extends Brick {

  /**
   * Constructs a firebrick.
   *
   * @param listener - The interface the brick will communicate with the platform when damage should
   *                 be dealt.
   */
  public FireBrick(ActionListener listener) {
    super(listener);
    setImage("brickFire.png");
    this.command =
        (event) -> {
          switch (event.getStrickedType()) {
            case PADDLE:
              listener.decrementHealth();
              destroy();
              break;
            case BALL:
            case HOT_BALL:
              if (!fallen) {
                fall();
                setImage("fireball.png");
                listener.addPoints(2);
              }
              break;
            case HOT_WALL:
              destroy();
              break;
            default:
              break;
          }
        };
  }
}
