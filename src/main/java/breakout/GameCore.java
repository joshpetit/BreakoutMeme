package breakout;

import javafx.scene.Group;
import javafx.scene.Scene;
import java.util.List;

public class GameCore {
	private Group platform;
	private List<GameObject> gameObjects;
	private int brickCount = 10;
	private Scene scene;
	public GameCore(Group platform, List<GameObject> gameObjects, Scene scene) {
		this.platform = platform;
		this.gameObjects = gameObjects;
		this.scene = scene;

		Ball ball = new Ball(70.0, -1, -1, GameObject.TYPE.HOT_BALL, "ball.gif");
		gameObjects.add(ball);
		for (int i = 0; i < brickCount; i++) {
			Brick brick = new Brick();
			brick.setX(100 * i );
			gameObjects.add(brick);
			platform.getChildren().add(brick);
		}

		ball.setX(500);
		ball.setY(500);

		platform.getChildren().addAll(ball);
	}

	class Brick extends GameObject {

		public Brick() {
			super(0, 0, 0, GameObject.TYPE.BRICK, "brick.png");
			this.command = (event) -> {
				switch (event.getStrickedType()) {
				case HOT_BALL:
					System.out.println("AHHHH!!!");
					System.out.println(gameObjects);
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
					System.out.println("BRICK!");
					setDirectionY(1);
				}
			};
		}
	}
}
