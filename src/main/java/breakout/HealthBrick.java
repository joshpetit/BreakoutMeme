package breakout;

/** A Brick that gives health to the player. */
public class HealthBrick extends FallingBrick {

  /**
   * Constructs a HealthBrick.
   *
   * @param listener - The interface the brick will communicate with the platform when health should
   *     be added.
   */
  public HealthBrick(ActionListener listener) {
    super(listener, "brickHealth.png", "heart.png");
  }

  @Override
  protected void hitPaddle() {
    destroy();
    listener.incrementHealth();
  }
}
