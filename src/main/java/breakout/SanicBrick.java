package breakout;

public class SanicBrick extends Brick {

  public SanicBrick(ActionListener listener) {
    super(listener);
    setImage("brickSanic.png");
    this.command =
        (event) -> {
          switch (event.getStrickedType()) {
            case BALL:
            case HOT_BALL:
              if (!fallen) {
                fall();
                listener.addPoints(3);
                setImage("sanic.png");
              } else {
              }
              break;
            case PADDLE:
              destroy();
              listener.speedBoost();
              break;
            case WALL:
            case HOT_WALL:
              destroy();
              break;
            default:
              break;
          }
        };
  }
}
