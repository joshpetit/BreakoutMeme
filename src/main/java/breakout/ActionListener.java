package breakout;

public interface ActionListener {

  void removeObject(GameObject object);

  void pausePaddle();

  /**
   * Retrieves the width of the current scene.
   */
  double getWidth();

  /**
   * Retrieves the height of the current scene.
   */
  double getHeight();

  void toggleInvincibility();

  void createBall();

  void decrementHealth();

  void incrementHealth();

  void speedBoost();

  void addPoints(int amount);

  void moneyBonus();
}
