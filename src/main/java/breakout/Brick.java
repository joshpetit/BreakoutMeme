package breakout;

/**
 * The general class for Bricks. A Brick is any object that spawns in a stationary position. Bricks
 * also are capable of falling and being destroyed.
 */
public class Brick extends GameObject {

  protected ActionListener listener;
  protected boolean fallen = false;

  /**
   * Constructs a standard brick that destroys itself on contact.
   *
   * @param listener - The action reader the brick uses to manipulate the platform
   */
  public Brick(ActionListener listener) {
    super(0, 0, 0, GameObject.TYPE.BRICK, "brick.png");
    this.listener = listener;

    this.command =
        (event) -> {
          switch (event.getStrickedType()) {
            case BALL:
            case HOT_BALL:
              System.out.println("The Ball hit the brick!");
              destroy();
              break;
            default:
              break;
          }
        };
  }

  protected void fall() {
    setDirectionY(1);
    setSpeed(150);
    fallen = true;
  }

  /** Does whatever necessary for the brick to destroy itself. */
  public void destroy() {
    listener.removeObject(this);
  }
}
