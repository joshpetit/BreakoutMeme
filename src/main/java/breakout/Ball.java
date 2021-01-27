package breakout;

public class Ball extends GameObject {
	ActionListener listener;
	public Ball(double speed, int directionX, int directionY, boolean hotBall, String image, ActionListener listener) {
		super(speed, directionX, directionY, hotBall ? GameObject.TYPE.HOT_BALL : GameObject.TYPE.BALL, image);
		this.listener = listener;
		this.command = (event) -> {
			switch (event.getStrickedType()) {
			case HOT_WALL:
				System.out.println("HOT WALL");
			case WALL:
				if (getX() <= 0) {
					setDirectionX(1);
				} else if (getX() >= listener.getWidth() - getBoundsInParent().getWidth()) {
					setDirectionX(-1);
				}

				if (getY() <= 0) {
					setDirectionY(1);
				} else if (getY() >= listener.getHeight() - getBoundsInParent().getHeight()) {
					setDirectionY(-1);
				}
				break;
			case BRICK:
				System.out.println("BRICK OR PADDLE!!");
				setDirectionY(1);
				break;
			case PADDLE:
				setDirectionY(-1);
				break;
			}
		};
	}
}
