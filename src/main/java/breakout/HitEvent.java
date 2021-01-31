package breakout;

import javafx.event.Event;
import javafx.event.EventType;

public class HitEvent extends Event {

  public static final EventType<HitEvent> HIT = new EventType<>(ANY, "HIT");

  /**
   * How fast/hard the strike object striked this object.
   */
  private int strength;

  /**
   * The type of object that hit this object.
   */
  private GameObject.TYPE hitType;

  /**
   * Constructs event that is passed to the object after being struct.
   *
   * @param strength - The speed/force at which the object was hit.
   * @param hitType  - The type of object that hit the object.
   */
  public HitEvent(int strength, GameObject.TYPE hitType) {
    super(HitEvent.HIT);
    this.strength = strength;
    this.hitType = hitType;
  }

  /**
   * Returns the type of object that caused this event.
   */
  public GameObject.TYPE getStrickedType() {
    return this.hitType;
  }
}
