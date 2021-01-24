package breakout;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// If time abstract into Moveable/immovable game object classes
abstract class GameObject extends ImageView {

	protected double speed;
	protected int directionX;
	protected int directionY;
	protected GameObject.TYPE type;

	public GameObject(double speed, int directionX, int directionY, int width, int height, GameObject.TYPE type, String image) {
		super();
		this.speed = speed;
		this.directionX = directionX;
		this.directionY = directionY;
		this.type = type;
		setImage(image);
	}

	enum TYPE {
		HOT_WALL, WALL, BRICK, PADDLE
	}

	public double getSpeed() {
		return this.speed;
	}

	public void setImage(String imageName) {
        Image image = new Image(this.getClass().getClassLoader().getResourceAsStream(imageName));
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

	public abstract void onHit(HitContext context);
}
