package breakout;

/** A Brick that casts fires from the heavens. */
public class FireBrick extends FallingBrick {

  /**
   * Constructs a firebrick.
   *
   * @param listener - The interface the brick will communicate with the platform when damage should
   *     be dealt.
   */
  public FireBrick(ActionListener listener) {
    super(listener, "brickFire.png", "fireball.png");
  }

  @Override
  protected void hitPaddle() {
    listener.decrementHealth();
    destroy();
  }
}
