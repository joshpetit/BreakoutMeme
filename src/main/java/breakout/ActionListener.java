package breakout;

public interface ActionListener {
	public void removeObject(GameObject object);
	public void pausePaddle();
	/**
	 * Retrieves the width of the current scene.
	 */
	public double getWidth();

	/**
	 * Retrieves the height of the current scene.
	 */
	public double getHeight();

	public void toggleInvincibility();
	public void createBall();
	public void decrementHealth();
	public void incrementHealth();
	public void speedBoost();
	public void addPoints(int amount);
	public void moneyBonus();
}
