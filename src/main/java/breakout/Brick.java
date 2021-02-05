package breakout;

/**
 * The general class for Bricks. A Brick is any object that spawns in a stationary position. Bricks
 * also are capable of falling and being destroyed.
 *
 * @author Joshua Petitma
 */
public class Brick extends GameObject {

  /**
   * Constructs a standard brick that destroys itself on contact.
   *
   * @param listener - The action reader the brick uses to manipulate the platform
   */
  public Brick(ActionListener listener) {
    super(0, 0, 0, GameObject.TYPE.BRICK, "brick.png", listener);
  }

  @Override
  protected void hitBall() {
    hitHotBall();
  }

  protected void hitHotBall() {
    destroy();
  }
}
