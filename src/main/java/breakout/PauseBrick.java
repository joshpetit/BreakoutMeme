package breakout;

/**
 * A Brick that pauses the platform paddles.
 *
 * @author Joshua Petitma
 */
public class PauseBrick extends Brick {

  /**
   * Constructs a PauseBrick.
   *
   * @param listener - The interface the brick will communicate with the platform when the paddles
   *     should be paused.
   */
  public PauseBrick(ActionListener listener) {
    super(listener);
    setImage("brickIce.png");
  }

  @Override
  protected void hitBall() {
    destroy();
  }

  @Override
  protected void hitHotBall() {
    listener.pausePaddle();
    listener.addPoints(1);
    destroy();
  }
}
