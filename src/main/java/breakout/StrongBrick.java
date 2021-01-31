package breakout;

public class StrongBrick extends Brick {
	private int health = 5;

	public StrongBrick(ActionListener listener) {
		super(listener);
		setImage("brickStrong.png");
		this.command = (event) -> {
			switch (event.getStrickedType()) {
			case BALL:
			case HOT_BALL:
				switch (health) {
				case 1:
					destroy();
					break;
				case 2:
					setImage("crumbledBrick.png");
				default:
					health--;
				}
				break;
			}
		};
	}
}
