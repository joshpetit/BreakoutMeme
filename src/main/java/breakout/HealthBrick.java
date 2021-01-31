package breakout;

public class HealthBrick extends Brick {

  public HealthBrick(ActionListener listener) {
    super(listener);
    setImage("brickHealth.png");
    this.command =
        (event) -> {
          switch (event.getStrickedType()) {
            case BALL:
            case HOT_BALL:
              if (!fallen) {
                fall();
                listener.addPoints(1);
                setImage("heart.png");
              }
              break;
            case PADDLE:
              destroy();
              listener.incrementHealth();
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
