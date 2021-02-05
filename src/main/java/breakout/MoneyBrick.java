package breakout;

/**
 * A Brick that multiplies the points the player earns.
 *
 * @author Joshua Petitma
 */
public class MoneyBrick extends FallingBrick {

  /**
   * Constructs a MoneyBrick.
   *
   * @param listener - The interface the brick will communicate with the platform when money should
   *     be multiplied
   */
  public MoneyBrick(ActionListener listener) {
    super(listener, "brickMoney.png", "drphildollar.png");
  }

  @Override
  protected void hitPaddle() {
    destroy();
    listener.moneyBonus();
  }
}
