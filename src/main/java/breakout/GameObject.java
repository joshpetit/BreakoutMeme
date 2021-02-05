package breakout;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The general type of object that appears upon the platform. This class implements defines what an
 * object that can be placed on the platform managed by the gameloop.
 */
public abstract class GameObject extends ImageView {

  protected double speed;
  protected int directionX;
  protected int directionY;
  protected GameObject.TYPE type;
  protected HitCommand command;
  protected ActionListener listener;

  protected GameObject(
      double speed,
      int directionX,
      int directionY,
      GameObject.TYPE type,
      String image,
      ActionListener listener) {
    super();
    this.speed = speed;
    this.directionX = directionX;
    this.directionY = directionY;
    this.type = type;
    setImage(image);
    command =
        (e) -> {
          switch (e.getStrickedType()) {
            case PADDLE:
              hitPaddle();
              break;
            case BALL:
              hitBall();
              break;
            case HOT_BALL:
              hitHotBall();
              break;
            case HOT_WALL:
              hitHotWall();
              break;
            default:
              hitUnknown();
              break;
          }
        };
    addEventHandler(HitEvent.HIT, event -> command.execute(event));
    this.listener = listener;
  }

  // I make these empty and not abstract since a lot
  // of classes actually do nothing when hitting certain things.
  protected void hitPaddle() {}

  protected void hitBall() {}

  protected void hitHotBall() {}

  protected void hitHotWall() {}

  protected void hitUnknown() {}

  /** Does whatever necessary for the brick to destroy itself. */
  public void destroy() {
    listener.removeObject(this);
  }

  /**
   * The kind of GameObject. This is a generic enum so that GameObjects can possibly change their
   * type based on circumstances in the game.
   */
  enum TYPE {
    HOT_WALL,
    WALL,
    BRICK,
    PADDLE,
    HOT_BALL,
    BALL
  }

  /** Returns the speed at which this current object moves. */
  public double getSpeed() {
    return this.speed;
  }

  /**
   * Changes the current images within this object.
   *
   * @param imageName - The name of the image file corresponding to this game's resources.
   */
  public void setImage(String imageName) {
    Image image = new Image(GameObject.class.getResourceAsStream(imageName));
    setImage(image);
  }

  /** Returns -1 if moving left in the X direction, 1 if moving right. */
  public int getDirectionX() {
    return this.directionX;
  }

  /**
   * Sets the speed of this object.
   *
   * @param speed - The speed at which to set this object.
   */
  public void setSpeed(int speed) {
    this.speed = speed;
  }

  /**
   * Sets which direction on the X axis this object is moving.
   *
   * @param dir - positive for forward on the X axis and negative for backwards
   */
  public void setDirectionX(int dir) {
    this.directionX = dir;
  }

  /**
   * Sets which direction on the Y axis this object is moving.
   *
   * @param dir - positive for down on the Y axis and negative for up
   */
  public void setDirectionY(int dir) {
    this.directionY = dir;
  }

  /** Returns -1 if moving up in the Y direction, 1 if moving down. */
  public int getDirectionY() {
    return this.directionY;
  }

  /** Returns the kind of object this is. */
  public GameObject.TYPE getType() {
    return type;
  }

  /**
   * Sets the function to be executed when this object is hit.
   *
   * @param command - The function that handles the {@link breakout.HitEvent}
   */
  public void setOnHit(HitCommand command) {
    this.command = command;
  }
}
