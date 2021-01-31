package breakout;

public class StrongBrick extends Brick {

  private int health = 5;

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
