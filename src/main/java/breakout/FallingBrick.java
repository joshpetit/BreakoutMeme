package breakout;

/**
 * A brick that can fall.
 *
 * @author Joshua Petitma
 */
public abstract class FallingBrick extends Brick {
  protected boolean fallen = false;
  protected String fallingImage;

  protected FallingBrick(ActionListener listener, String image, String fallingImage) {
    super(listener);
    setImage(image);
    this.fallingImage = fallingImage;
  }

  @Override
  protected void hitBall() {
    hitHotBall();
  }

  @Override
  protected void hitHotBall() {
    if (!fallen) {
      fall();
      setImage(fallingImage);
      listener.addPoints(2);
    }
  }

  @Override
  protected void hitHotWall() {
    destroy();
  }

  protected void fall() {
    setDirectionY(1);
    setSpeed(150);
    fallen = true;
  }
}
