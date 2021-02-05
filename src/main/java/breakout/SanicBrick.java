package breakout;

/**
 * A Brick that speeds up the paddles. GOTTA GO FAST!
 *
 * @author Joshua Petitma
 */
public class SanicBrick extends FallingBrick {

  /**
   * Constructs a SanicBrick.
   *
   * @param listener - The interface the brick will communicate when paddles should speed up.
   */
  public SanicBrick(ActionListener listener) {
    super(listener, "brickSanic.png", "sanic.png");
  }

  @Override
  protected void hitPaddle() {
    destroy();
    listener.speedBoost();
  }
}
