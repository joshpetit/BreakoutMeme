package breakout;

public class BrickFactory {
	public static int BORING_BRICK = 0;
	public static int PAUSE_BRICK = 1;
	public static int FIRE_BRICK = 2;

	public static Brick create(ActionListener listener, int brickType) {
		switch(brickType) {
			case 0:
				return new Brick(listener);
			case 1:
				return new PauseBrick(listener);
			case 2:
				return new FireBrick(listener);
			default:
				return new Brick(listener);
		}
	}
}
