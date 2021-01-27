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
	private Scene scene;
	private Paddle paddle;
	private Ball ball;
	private BrickListener brickListener;
	private Scanner scan;
	private int remainingBricks = 0;
	private int health = 4;

	public GameCore(Group platform, List<GameObject> gameObjects, Scene scene) {
		this.platform = platform;
		this.gameObjects = gameObjects;
		this.scene = scene;
		brickListener = new BrickListener();
		scan = new Scanner(GameCore.class.getResourceAsStream("levels.conf"));
		ball = new Ball(200.0, -1, -1, true, "ball.gif", brickListener);
		paddle = new Paddle(100, brickListener);

		addObject(ball);
		addObject(paddle);
		ball.setX(500);
		ball.setY(500);

		paddle.setX(500);
		paddle.setY(700);
		nextLevel();

		platform.setOnKeyPressed( (e) -> {
			movePaddle(e.getCode());
		});

		platform.setOnKeyReleased( (e) -> {
			stopPaddle(e.getCode());
		});

		platform.requestFocus();
	}

	private void nextLevel() {
		this.health++;
		System.out.println("Starting next Level...");
		if (!scan.hasNextInt()) {
			return;
		}
		ball.setX(500);
		ball.setY(500);
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
		case LEFT:
			paddle.setDirectionX(0);
			break;
		case L:
		case RIGHT:
			paddle.setDirectionX(0);
			break;
		}
	}

	public void movePaddle(KeyCode code) {
		switch (code) {
		case H:
		case LEFT:
			paddle.setDirectionX(-1);
			break;
		case L:
		case RIGHT:
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
		}

		public void incrementHealth() {
			health++;
		}

		public void createBall() {
			Ball ball = new Ball(200.0, -1, -1, false, "weakBall.gif", brickListener);
			ball.setX(500);
			ball.setY(500);
			addObject(ball);
		}

		public void pausePaddle() {
			paddle.setSpeed(0);
			Thread thread = new Thread( () -> {
				try {
					Thread.sleep(2500);
					paddle.setSpeed(100);
					paddle.setImage("paddle.png");
				} catch (InterruptedException err) {
					err.printStackTrace();
				}
			});
			paddle.setImage("paddleFrozen.png");
			thread.start();
		}

		public double getWidth() {
			return scene.getWidth();
		}
		public double getHeight() {
			return scene.getHeight();
		}
	}
}
