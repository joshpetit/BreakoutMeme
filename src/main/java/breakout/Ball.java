package breakout;

/** Standard ball class of the {@link breakout.GameObject.TYPE.HOT_BALL} type */
public class Ball extends GameObject {

  protected ActionListener listener;

  /**
   * Constructs a standard ball.
   *
   * @param speed - How fast the ball travels on the gameloop.
   * @param directionX - The horizontal velocity of the ball.
   * @param directionY - The vertical velocity of the ball.
   * @param hotBall - Whether this is a ball capable of dealing damage and triggering anti-powerups.
   *     True creates a hot ball while false a normal one.
   * @param image - The image to be used for the ball.
   * @param listener - The interface the ball uses to communicate when it should be destroyed or to
   *     change its direction.
   */
  public Ball(
      double speed,
      int directionX,
      int directionY,
      boolean hotBall,
      String image,
      ActionListener listener) {
    super(
        speed,
        directionX,
        directionY,
        hotBall ? GameObject.TYPE.HOT_BALL : GameObject.TYPE.BALL,
        image, listener);
    this.listener = listener;
    this.command =
        (event) -> {
          switch (event.getStrickedType()) {
            case HOT_WALL:
              System.out.println("HOT WALL");
              if (getType() == GameObject.TYPE.HOT_BALL) {
                listener.decrementHealth();
              } else {
                destroy();
                break;
              }
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
              setDirectionY(1);
              break;
            case PADDLE:
              setDirectionY(-1);
              break;
            default:
              break;
          }
        };
  }

  /**
   * Removes the ball from the platform.
   */
  public void destroy() {
    listener.removeObject(this);
  }
}
