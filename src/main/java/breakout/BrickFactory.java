package breakout;

public class BrickFactory {
	public static int BORING_BRICK = 0;
	public static int PAUSE_BRICK = 1;
	public static int FIRE_BRICK = 2;
	public static int GENERATIVE_BRICK = 3;
	public static int HEALTH_BRICK = 4;
	public static int SANIC_BRICK = 5;
	public static int TOTAL_TYPES = 6;

	public static Brick create(ActionListener listener, int brickType) {
		switch(brickType) {
			case 0:
				return new Brick(listener);
			case 1:
				return new PauseBrick(listener);
			case 2:
				return new FireBrick(listener);
			case 3:
				return new GenerativeBrick(listener);
			case 4:
				return new HealthBrick(listener);
			case 5:
				return new SanicBrick(listener);
			default:
				return new Brick(listener);
		}
	}
}
