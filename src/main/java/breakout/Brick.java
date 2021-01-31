package breakout;

public class Brick extends GameObject {

  ActionListener listener;
  protected boolean fallen = false;

  public Brick(ActionListener listener) {
    super(0, 0, 0, GameObject.TYPE.BRICK, "brick.png");
    this.listener = listener;

    this.command = (event) -> {
      switch (event.getStrickedType()) {
        case BALL:
        case HOT_BALL:
          System.out.println("The Ball hit the brick!");
          destroy();
          break;
      }
    };
  }

  protected void fall() {
    setDirectionY(1);
    setSpeed(150);
    fallen = true;
  }

  public void destroy() {
    listener.removeObject(this);
  }
}
