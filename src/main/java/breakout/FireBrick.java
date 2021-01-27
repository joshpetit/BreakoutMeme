package breakout;

public class FireBrick extends Brick {
	public FireBrick(ActionListener listener) {
		super(listener);
		this.command = (event) -> {
			switch (event.getStrickedType()) {
			case HOT_BALL:
				fall();
				setImage("fireball.png");
				break;
			case HOT_WALL:
				destroy();
				break;
			}
		};
	}
}
