package breakout;

public class GenerativeBrick extends Brick {

	public GenerativeBrick(ActionListener listener) {
		super(listener);

		this.command = (event) -> {
			switch (event.getStrickedType()) {
			case BALL:
				destroy();
				break;
			case HOT_BALL:
				listener.createBall();
				destroy();
				break;
			}
		};
	}
}