package breakout;

/** A Brick that requires multiple hits to be destroyed. */
public class StrongBrick extends Brick {

  protected int health = 5;

  /**
   * Constructs a StrongBrick.
   *
   * @param listener - The interface the brick will use to communicate when it should be destroyed.
   */
  public StrongBrick(ActionListener listener) {
    super(listener);
    setImage("brickStrong.png");
    this.command =
        (event) -> {
          switch (event.getStrickedType()) {
            case BALL:
            case HOT_BALL:
              if (health == 1) {
                listener.addPoints(2);
                destroy();
              } else if (health == 2) {
                setImage("crumbledBrick.png");
              }
              health--;
            default:
              break;
          }
        };
  }
}
