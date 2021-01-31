package breakout;

/** A Brick that multiplies the points the player earns. */
public class MoneyBrick extends Brick {

  /**
   * Constructs a MoneyBrick.
   *
   * @param listener - The interface the brick will communicate with the platform when money should
   *     be multiplied
   */
  public MoneyBrick(ActionListener listener) {
    super(listener);
    setImage("brickMoney.png");
    this.command =
        (event) -> {
          switch (event.getStrickedType()) {
            case BALL:
            case HOT_BALL:
              if (!fallen) {
                fall();
                listener.addPoints(1);
                setImage("drphildollar.png");
              }
              break;
            case PADDLE:
              destroy();
              listener.moneyBonus();
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
