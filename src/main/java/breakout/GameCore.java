package breakout;

import javafx.scene.Group;
import javafx.scene.Scene;
import java.util.List;

public class GameCore {
	private Group platform;
	private List<GameObject> gameObjects;
	private int brickCount;
	private Scene scene;
	public GameCore(Group platform, List<GameObject> gameObjects, Scene scene) {
		this.platform = platform;
		this.gameObjects = gameObjects;
		this.scene = scene;


		Ball ball = new Ball(70.0, -1, -1, GameObject.TYPE.HOT_BALL, "ball.gif");
		Brick brick = new Brick();
		Brick brick2 = new Brick();
		brick2.setX(300);
		brick2.setY(300);

		gameObjects.add(ball);
		gameObjects.add(brick);
		gameObjects.add(brick2);

		ball.setX(500);
		ball.setY(500);

		platform.getChildren().addAll(ball, brick, brick2);
	}

	class Brick extends GameObject {

		public Brick() {
			super(0, 0, 0, GameObject.TYPE.BRICK, "brick.png");
			this.command = (event) -> {
				switch (event.getStrickedType()) {
				case HOT_BALL:
					System.out.println("AHHHH!!!");
					gameObjects.remove(this);
					platform.getChildren().remove(this);
				}
			};
		}
	}

	class Ball extends GameObject {

		public Ball(double speed, int directionX, int directionY, GameObject.TYPE type, String image) {
			super(speed, directionX, directionY, type, image);
			this.command = (event) -> {
				switch (event.getStrickedType()) {
				case HOT_WALL:
					System.out.println("HOT WALL");
				case WALL:
					if (getX() <= 0) {
						setDirectionX(1);
					} else if (getX() >= scene.getWidth() - getBoundsInParent().getWidth()) {
						setDirectionX(-1);
					}

					if (getY() <= 0) {
						setDirectionY(1);
					} else if (getY() >= scene.getHeight() - getBoundsInParent().getHeight()) {
						setDirectionY(-1);
					}
					break;
				case BRICK:
					setDirectionY(1);
				}
			};
		}
	}
}
