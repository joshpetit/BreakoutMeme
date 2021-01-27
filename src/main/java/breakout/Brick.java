package breakout;

public class Brick extends GameObject {
	ActionListener listener;

	public Brick(ActionListener listener) {
		super(0, 0, 0, GameObject.TYPE.BRICK, "brick.png");
		this.listener = listener;

		this.command = (event) -> {
			switch (event.getStrickedType()) {
			case HOT_BALL:
				System.out.println("The Ball hit the brick!");
				destroy();
				break;
			}
		};
	}

	protected void fall() {
		setDirectionY(1);
		setSpeed(100);
	}

	public void destroy() {
		listener.removeObject(this);
	}
}
