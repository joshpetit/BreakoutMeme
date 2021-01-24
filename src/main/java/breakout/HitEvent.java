package breakout;
import javafx.event.Event;
import javafx.event.EventType;

public class HitEvent extends Event {
	public static final EventType<HitEvent> HIT = new EventType<>(ANY, "HIT");

	private int strength;
	private GameObject.TYPE hitType;

	public HitEvent(int strength, GameObject.TYPE hitType) {
		super(HitEvent.HIT);
		this.strength = strength;
		this.hitType = hitType;
	}

	public GameObject.TYPE getStrickedType() {
		return this.hitType;
	}
}
