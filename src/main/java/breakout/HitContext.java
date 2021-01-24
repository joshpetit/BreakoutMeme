package breakout;

public class HitContext {
	private int strength;
	private GameObject.TYPE hitType;

	public HitContext(int strength, GameObject.TYPE hitType) {
		this.strength = strength;
		this.hitType = hitType;
	}

	public GameObject.TYPE getStrickedType() {
		return hitType;
	}
}
