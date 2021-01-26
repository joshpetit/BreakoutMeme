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

	/**
	 * The kind of GameObject.
	 * This is a generic enum so that GameObjects can possibly
	 * change their type based on circumstances in the game.
	 */
	enum TYPE {
		HOT_WALL, WALL, BRICK, PADDLE, HOT_BALL
	}

	/**
	 * returns the speed at which this current object moves.
	 */
	public double getSpeed() {
		return this.speed;
	}

	/**
	 * Changes the current images within this object.
	 * @param imageName - The name of the image file corresponding to this game's resources.
	 */
	public void setImage(String imageName) {
		Image image = new Image(GameObject.class.getResourceAsStream(imageName));
		setImage(image);
	}

	/**
	 * Returns -1 if moving left in the X direction, 1 if moving right.
	 */
	public int getDirectionX() {
		return this.directionX;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * Sets which direction on the X axis this object is moving.
	 * @param dir - positive for forward on the X axis and negative for backwards
	 */
	public void setDirectionX(int dir) {
		this.directionX = dir;
	}

	/**
	 * Sets which direction on the Y axis this object is moving.
	 * @param dir - positive for down on the Y axis and negative for up
	 */
	public void setDirectionY(int dir) {
		this.directionY = dir;
	}

	/**
	 * Returns -1 if moving up in the Y direction, 1 if moving down.
	 */
	public int getDirectionY() {
		return this.directionY;
	}


	/**
	 * Returns the kind of object this is.
	 */
	public GameObject.TYPE getType() {
		return type;
	}

	/**
	 * Sets the function to be executed when this object is hit.
	 * @param HitCommand - The function that handles the {@link breakout.HitEvent}
	 */
	public void setOnHit(HitCommand command) {
		this.command = command;
	}
}
