package breakout;

public class BrickFactory {
	public static int BORING_BRICK = 0;
	public static int PAUSE_BRICK = 1;
	public static int FIRE_BRICK = 2;
	public static int GENERATIVE_BRICK = 3;
	public static int HEALTH_BRICK = 4;
	public static int SANIC_BRICK = 5;
	public static int INVINCIBILITY_BRICK = 6;
	public static int STRONG_BRICK = 7;
	public static int MONEY_BRICK = 8;
	public static int TOTAL_TYPES = 9;

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
			case 6:
				return new InvincibilityBrick(listener);
			case 7:
				return new StrongBrick(listener);
			case 8:
				return new MoneyBrick(listener);
			default:
				return new Brick(listener);
		}
	}
}
