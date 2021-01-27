package breakout;

public class PauseBrick extends Brick {
	public PauseBrick(ActionListener listener) {
		super(listener);
		this.command = (e) -> {
			switch (e.getStrickedType()) {
			case HOT_BALL:
				listener.pausePaddle();
				destroy();
				break;
			}
		};
	}
}
