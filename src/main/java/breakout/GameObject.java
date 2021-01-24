package breakout;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.event.Event;

// If time abstract into Moveable/immovable game object classes
abstract class GameObject extends ImageView {

	protected double speed;
	protected int directionX;
	protected int directionY;
	protected GameObject.TYPE type;
	protected HitCommand command;

	public GameObject(double speed, int directionX, int directionY, GameObject.TYPE type, String image) {
		super();
		this.speed = speed;
		this.directionX = directionX;
		this.directionY = directionY;
		this.type = type;
		setImage(image);
		command = (e) -> {};
		addEventHandler(HitEvent.HIT, event -> command.execute(event));
	}

	enum TYPE {
		HOT_WALL, WALL, BRICK, PADDLE
	}

	public double getSpeed() {
		return this.speed;
	}

	public void setImage(String imageName) {
		Image image = new Image(GameObject.class.getResourceAsStream(imageName));
		setImage(image);
	}

	public int getDirectionX() {
		return this.directionX;
	}

	public int getDirectionY() {
		return this.directionY;
	}

	public GameObject.TYPE getType() {
		return type;
	}

	public void setOnHit(HitCommand command) {
		this.command = command;
	}
}
