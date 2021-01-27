package breakout;

import javafx.scene.input.KeyCode;
import javafx.scene.Group;
import javafx.scene.Scene;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.io.IOException;
import java.util.Scanner;

public class GameCore {
	private Group platform;
	private List<GameObject> gameObjects;
	private int brickCount = 5;
	private Scene scene;
	private Paddle paddle;
	private Scanner scan;
	private int remainingBricks = 0;

	public GameCore(Group platform, List<GameObject> gameObjects, Scene scene) {
		this.platform = platform;
		this.gameObjects = gameObjects;
		this.scene = scene;
		System.out.println("ok");
		scan = new Scanner(GameCore.class.getResourceAsStream("levels.conf"));
		Ball ball = new Ball(200.0, -1, -1, true, "ball.gif");
		paddle = new Paddle(100);

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
		System.out.println("Starting next Level...");
		if (!scan.hasNextInt()) {
			return;
		}

		int numBricks = 0;
		List<Brick> bricks = new ArrayList<>();
		Brick brick;
		for (int i = 0; i < 3 && scan.hasNextInt(); i++) {
			numBricks = scan.nextInt();
			for (int j = 0; j < numBricks; j++) {
				switch (i) {
				case 0:
					brick = new Brick();
					break;
				case 1:
					brick = new PauseBrick();
					break;
				case 2:
					brick = new FireBrick();
					break;
				default:
					brick = new Brick();
					break;
				}
				bricks.add(brick);
				addObject(brick);
			}
			Collections.shuffle(bricks);
		}
		for (int i = 0; i < bricks.size(); i++) {
			bricks.get(i).setX(70 * i);
		}

		this.remainingBricks = bricks.size();
	}

	public void addObject(GameObject obj) {
		gameObjects.add(obj);
		platform.getChildren().add(obj);
	}

	public void removeObject(GameObject obj) {
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


	class FireBrick extends Brick {
		public FireBrick() {
			super();
			this.command = (event) -> {
				switch (event.getStrickedType()) {
				case HOT_BALL:
					fall();
					setImage("fireball.png");

				}
			};
		}
	}

	class PauseBrick extends Brick {
		public PauseBrick() {
			super();
			this.command = (e) -> {
				switch (e.getStrickedType()) {
				case HOT_BALL:
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
					destroy();
					break;
				}
			};
		}
	}

	class Brick extends GameObject {
		public Brick() {
			super(0, 0, 0, GameObject.TYPE.BRICK, "brick.png");
			this.command = (event) -> {
				switch (event.getStrickedType()) {
				case HOT_BALL:
					System.out.println("The Ball hit the brick!");
					destroy();
				}
			};
		}

		protected void fall() {
			setDirectionY(1);
			setSpeed(100);
		}

		public void destroy() {
			removeObject(this);
		}
	}

	class Paddle extends GameObject {
		public Paddle(int speed) {
			super(speed, 0, 0, GameObject.TYPE.PADDLE, "paddle.png");
			this.command = (e) -> {
				switch (e.getStrickedType()) {
				case WALL:
					if (getX() <= 0 && this.directionX == -1) {
						setDirectionX(0);
					} else if (getX() >= scene.getWidth() - getBoundsInParent().getWidth() && this.directionX == 1) {
						setDirectionX(0);
					}
					break;
				}
			};
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
