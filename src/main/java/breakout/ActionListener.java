package breakout;

/**
 * The interface {@link breakout.GameObject}s use to communicate with the platform.
 */
public interface ActionListener {

  /**
   * Removes the specified object from the platform.
   *
   * @param object - The reference to the object to be removed.
   */
  void removeObject(GameObject object);

  /**
   * Pauses the paddle within the game.
   */
  void pausePaddle();

  /**
   * Retrieves the width of the current scene.
   */
  double getWidth();

  /**
   * Retrieves the height of the current scene.
   */
  double getHeight();

  /**
   * Tells the platform to prevent damage from being delt to the player.
   */
  void toggleInvincibility();

  /**
   * Adds an extra ball to the platform.
   */
  void createBall();

  /**
   * Deals damage to the player.
   */
  void decrementHealth();

  /**
   * Adds health to the player.
   */
  void incrementHealth();

  /**
   * Tells the platform to speed up the paddles.
   */
  void speedBoost();

  /**
   * Gives points to the player.
   *
   * @param amount - The amount of points to be added to the player's score.
   */
  void addPoints(int amount);

  /**
   * Multiplies the the amount of points given to the user.
   */
  void moneyBonus();
}
