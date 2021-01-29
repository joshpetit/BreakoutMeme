package breakout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;

public class GameCore {
	private Group platform;
	private List<GameObject> gameObjects;
	private int brickCount = 5;
	private Paddle paddle;
	private Paddle secondPaddle;
	private Ball ball;
	private BrickListener brickListener;
	private Scanner scan;
	private int sceneHeight;
	private int sceneWidth;
	private int remainingBricks = 0;
	private int health = 4;
	private final int PADDLE_SPEED = 200;
	private final int BALL_SPEED = 200;

	public GameCore(Group platform, List<GameObject> gameObjects, int sceneWidth, int sceneHeight) {
		this.platform = platform;
		this.gameObjects = gameObjects;
		this.sceneWidth = sceneWidth;
		this.sceneHeight = sceneHeight;

		brickListener = new BrickListener();
		scan = new Scanner(GameCore.class.getResourceAsStream("levels.conf"));
		ball = new Ball(BALL_SPEED, -1, -1, true, "ball.gif", brickListener);
		paddle = new Paddle(PADDLE_SPEED, brickListener);
		secondPaddle = new Paddle(PADDLE_SPEED, brickListener);

		addObject(ball);
		addObject(paddle);
		addObject(secondPaddle);

		centerBall(ball);

		paddle.setX(sceneWidth / 2);
		paddle.setY(sceneHeight - paddle.getBoundsInParent().getHeight() - 100);

		secondPaddle.setX(sceneWidth / 2);
		secondPaddle.setY(sceneHeight - paddle.getBoundsInParent().getHeight());
		nextLevel();

		platform.setOnKeyPressed( (e) -> {
			movePaddle(e.getCode());
		});

		platform.setOnKeyReleased( (e) -> {
			stopPaddle(e.getCode());
		});

		platform.requestFocus();
	}

	public void centerBall(Ball ball) {
		ball.setX(sceneWidth / 2);
		ball.setY(sceneHeight / 2 );
	}

	private void nextLevel() {
		this.health++;
		System.out.println("Starting next Level...");
		if (!scan.hasNextInt()) {
			return;
		}
		centerBall(ball);
		ball.setDirectionY(-1);

		int numBricks = 0;
		List<Brick> bricks = new ArrayList<>();
		Brick brick;
		for (int brickType = 0; brickType < BrickFactory.TOTAL_TYPES && scan.hasNextInt(); brickType++) {
			numBricks = scan.nextInt();
			for (int j = 0; j < numBricks; j++) {
				brick = BrickFactory.create(brickListener, brickType);
				bricks.add(brick);
			}
			Collections.shuffle(bricks);
		}

		for (int i = 0; i < bricks.size(); i++) {
			bricks.get(i).setX(70 * i);
			addObject(bricks.get(i));
		}

		this.remainingBricks = bricks.size();
	}

	public void addObject(GameObject obj) {
		gameObjects.add(obj);
		platform.getChildren().add(obj);
	}

	public void removeGameObject(GameObject obj) {
		gameObjects.remove(obj);
		platform.getChildren().remove(obj);
		if (obj instanceof Brick) {
			this.remainingBricks--;
			System.out.println("Brick Destroyed");
		}
		if (this.remainingBricks <= 0) {
			nextLevel();
		}
	}

	public void stopPaddle(KeyCode code) {
		switch (code) {
		case H:
			secondPaddle.setDirectionX(0);
			break;
		case L:
			secondPaddle.setDirectionX(0);
			break;
		case A:
			paddle.setDirectionX(0);
			break;
		case F:
			paddle.setDirectionX(0);
			break;
		}
	}

	public void movePaddle(KeyCode code) {
		switch (code) {
		case H:
			secondPaddle.setDirectionX(-1);
			break;
		case L:
			secondPaddle.setDirectionX(1);
			break;
		case A:
			paddle.setDirectionX(-1);
			break;
		case F:
			paddle.setDirectionX(1);
			break;

		}
	}


	public class BrickListener implements ActionListener {

		public void removeObject(GameObject object) {
			removeGameObject(object);
		}

		public void setPaddleSpeed(int speed) {
			paddle.setSpeed(speed);
		}

		public void decrementHealth() {
			health--;
			System.out.println(health);
			Thread thread = new Thread( () -> {
				try {
					Thread.sleep(1000);
					paddle.setImage("paddle.png");
					secondPaddle.setImage("paddle.png");
				} catch (InterruptedException err) {
					err.printStackTrace();
				}
			});
			thread.start();
			paddle.setImage("paddleHit.png");
			secondPaddle.setImage("paddleHit.png");
		}

		public void incrementHealth() {
			health++;
		}

		public void createBall() {
			Ball ball = new Ball(BALL_SPEED, -1, -1, false, "weakBall.gif", brickListener);
			centerBall(ball);
			addObject(ball);
		}

		public void pausePaddle() {
			paddle.setSpeed(0);
			Thread thread = new Thread( () -> {
				try {
					Thread.sleep(4500);
					paddle.setSpeed(PADDLE_SPEED);
					paddle.setImage("paddle.png");
				} catch (InterruptedException err) {
					err.printStackTrace();
				}
			});
			paddle.setImage("paddleFrozen.png");
			thread.start();
		}

		public double getWidth() {
			return sceneWidth;
		}
		public double getHeight() {
			return sceneHeight;
		}
	}
}
