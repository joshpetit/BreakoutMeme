package breakout;

public class MoneyBrick extends Brick {

	public MoneyBrick(ActionListener listener) {
		super(listener);
		setImage("brickMoney.png");
		this.command = (event) -> {
			switch (event.getStrickedType()) {
			case BALL:
			case HOT_BALL:
				if (!fallen) {
					fall();
					listener.addPoints(1);
					setImage("drphildollar.png");
				} else {
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
			}
		};
	}
}
