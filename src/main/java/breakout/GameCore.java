package breakout;

import javafx.scene.input.KeyCode;
import javafx.scene.Group;
import javafx.scene.Scene;
import java.util.List;

public class GameCore {
	private Group platform;
	private List<GameObject> gameObjects;
	private int brickCount = 5;
	private Scene scene;
	private Paddle paddle;

	public GameCore(Group platform, List<GameObject> gameObjects, Scene scene) {
		this.platform = platform;
		this.gameObjects = gameObjects;
		this.scene = scene;

		Ball ball = new Ball(70.0, -1, -1, true, "ball.gif");
		paddle = new Paddle(5);

		gameObjects.add(ball);
		gameObjects.add(paddle);
		for (int i = 0; i < brickCount; i++) {
			Brick brick = new Brick();
			brick.setX(100 * i );
			gameObjects.add(brick);
			platform.getChildren().add(brick);
		}
		ball.setX(500);
		ball.setY(500);

		paddle.setX(500);
		paddle.setY(700);

		platform.getChildren().addAll(ball, paddle);
		platform.setOnKeyPressed( (e) -> {
			movePaddle(e.getCode());
		});
		platform.requestFocus();
	}

	public void movePaddle(KeyCode code) {
		switch (code) {
		case LEFT:
			paddle.setX(paddle.getX() - paddle.getSpeed());
			break;
		case RIGHT:
			paddle.setX(paddle.getX() + paddle.getSpeed());
			break;

		}
	}

	class Brick extends GameObject {

		public Brick() {
			super(0, 0, 0, GameObject.TYPE.BRICK, "brick.png");
			this.command = (event) -> {
				switch (event.getStrickedType()) {
				case HOT_BALL:
					System.out.println("The Ball hit the brick!");
					gameObjects.remove(this);
					platform.getChildren().remove(this);
				}
			};
		}
	}

	class Paddle extends GameObject {
		public Paddle(int speed) {
			super(speed, 0, 0, GameObject.TYPE.PADDLE, "paddle.png");

		}
	}

	class Ball extends GameObject {

		public Ball(double speed, int directionX, int directionY, boolean hotBall, String image) {
			super(speed, directionX, directionY, hotBall ? GameObject.TYPE.HOT_BALL : null, image);
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
					System.out.println("BRICK OR PADDLE!!");
					setDirectionY(1);
					break;
				case PADDLE:
					setDirectionY(-1);
					break;
				}
			};
		}
	}
}
