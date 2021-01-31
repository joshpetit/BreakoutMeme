package breakout;

public class FireBrick extends Brick {

  public FireBrick(ActionListener listener) {
    super(listener);
    setImage("brickFire.png");
    this.command =
        (event) -> {
          switch (event.getStrickedType()) {
            case PADDLE:
              listener.decrementHealth();
              destroy();
              break;
            case BALL:
            case HOT_BALL:
              if (!fallen) {
                fall();
                setImage("fireball.png");
                listener.addPoints(2);
              }
              break;
            case HOT_WALL:
              destroy();
              break;
            default:
              break;
          }
        };
  }
}
