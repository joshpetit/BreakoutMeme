package breakout;

public class BrickFactory {
	public static final int BORING_BRICK = 0;
	public static final int PAUSE_BRICK = 1;
	public static final int FIRE_BRICK = 2;
	public static final int GENERATIVE_BRICK = 3;
	public static final int HEALTH_BRICK = 4;
	public static final int SANIC_BRICK = 5;
	public static final int INVINCIBILITY_BRICK = 6;
	public static final int STRONG_BRICK = 7;
	public static final int MONEY_BRICK = 8;
	public static final int TOTAL_TYPES = 9;

	public static Brick create(ActionListener listener, int brickType) {
		switch(brickType) {
			case PAUSE_BRICK:
				return new PauseBrick(listener);
			case FIRE_BRICK:
				return new FireBrick(listener);
			case GENERATIVE_BRICK:
				return new GenerativeBrick(listener);
			case HEALTH_BRICK:
				return new HealthBrick(listener);
			case SANIC_BRICK:
				return new SanicBrick(listener);
			case INVINCIBILITY_BRICK:
				return new InvincibilityBrick(listener);
			case STRONG_BRICK:
				return new StrongBrick(listener);
			case MONEY_BRICK:
				return new MoneyBrick(listener);
			default:
				return new Brick(listener);
		}
	}
}
