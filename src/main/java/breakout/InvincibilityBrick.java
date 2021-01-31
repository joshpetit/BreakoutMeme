
package breakout;

public class InvincibilityBrick extends Brick {

	public InvincibilityBrick(ActionListener listener) {
		super(listener);
		setImage("brickInvincibility.png");
		this.command = (event) -> {
			switch (event.getStrickedType()) {
			case BALL:
			case HOT_BALL:
				if (!fallen) {
					listener.addPoints(1);
					fall();
					setImage("homeworkpass.png");
				} else {
				}
				break;
			case PADDLE:
				destroy();
				listener.toggleInvincibility();
				break;
			case WALL:
			case HOT_WALL:
				destroy();
				break;
			}
		};
	}
}
