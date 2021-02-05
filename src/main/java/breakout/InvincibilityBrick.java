package breakout;

/** A Brick that gives health to the player. */
public class InvincibilityBrick extends FallingBrick {

  /**
   * Constructs an InvincibilityBrick tha.
   *
   * @param listener - The interface the brick will communicate with the platform when the homework
   *     pass should be applied.
   */
  public InvincibilityBrick(ActionListener listener) {
    super(listener, "brickInvincibility.png", "homeworkpass.png");
    setImage("brickInvincibility.png");
  }

  @Override
  protected void hitPaddle() {
    destroy();
    listener.toggleInvincibility();
  }
}
