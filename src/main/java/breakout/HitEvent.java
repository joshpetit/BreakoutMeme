package breakout;

import javafx.event.Event;
import javafx.event.EventType;

/**
 * An event that contains information on when two {@link breakout.GameObject} collide.
 */
public class HitEvent extends Event {

  public static final EventType<HitEvent> HIT = new EventType<>(ANY, "HIT");

  /**
   * The type of object that hit this object.
   */
  private GameObject.TYPE hitType;

  /**
   * Constructs event that is passed to the object after being struct.
   *
   * @param hitType - The type of object that hit the object.
   */
  public HitEvent(GameObject.TYPE hitType) {
    super(HitEvent.HIT);
    this.hitType = hitType;
  }

  /**
   * Returns the type of object that caused this event.
   */
  public GameObject.TYPE getStrickedType() {
    return this.hitType;
  }
}
