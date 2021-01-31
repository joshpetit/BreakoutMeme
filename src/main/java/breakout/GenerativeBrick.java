package breakout;

public class GenerativeBrick extends Brick {

  public GenerativeBrick(ActionListener listener) {
    super(listener);
    setImage("brickBall.png");
    this.command =
        (event) -> {
          switch (event.getStrickedType()) {
            case BALL:
            case HOT_BALL:
              listener.createBall();
              listener.addPoints(1);
              destroy();
              break;
            default:
              break;
          }
        };
  }
}
