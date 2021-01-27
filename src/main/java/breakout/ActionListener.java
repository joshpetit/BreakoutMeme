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

	public void setPaddleSpeed(int speed);
}