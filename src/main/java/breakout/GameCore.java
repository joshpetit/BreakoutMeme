package breakout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.input.KeyCode;

public class GameCore {

  private static final String STATUS = "Health: %d Points %d";
  private static final int PADDLE_SPEED = 200;
  private static final int BALL_SPEED = 200;
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
  private Text statusText;

  public GameCore(Group platform, List<GameObject> gameObjects, int sceneWidth, int sceneHeight) {
    this.platform = platform;
    this.gameObjects = gameObjects;
    this.sceneWidth = sceneWidth;
    this.sceneHeight = sceneHeight;

    ImageView bg = new ImageView();
    Image image = new Image(GameCore.class.getResourceAsStream("bg1.png"));
    bg.setImage(image);
    platform.getChildren().add(bg);

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

    statusText = new Text(String.format(STATUS, health, 0));
    statusText.setX(sceneWidth - statusText.getBoundsInParent().getWidth() - 50);
    statusText.setY(sceneHeight - 10);
    statusText.setFill(Color.WHITE);
    platform.getChildren().add(statusText);

    nextLevel();

    platform.setOnKeyPressed((e) -> {
      movePaddle(e.getCode());
      cheat(e.getCode());
    });

    platform.setOnKeyReleased((e) -> {
      stopPaddle(e.getCode());
    });

    platform.requestFocus();
  }

  public void centerBall(Ball ball) {
    ball.setX(sceneWidth / 2);
    ball.setY(sceneHeight / 2);
  }

  /**
   * Handles potential cheat keys
   */
  public void cheat(KeyCode cheat) {
    switch (cheat) {
      case INSERT:
        brickListener.createBall();
        break;
      case ADD:
        brickListener.incrementHealth();
        break;
      case SUBTRACT:
        brickListener.decrementHealth();
        break;
      case EQUALS:
        paddle.setSpeed(400);
        break;
      case MINUS:
        ball.setSpeed(100);
        break;
    }
  }

  private void nextLevel() {
    brickListener.incrementHealth();

    System.out.println("Starting next Level...");
    if (!scan.hasNextInt()) {
      return;
    }
    centerBall(ball);
    ball.setDirectionY(-1);

    int numBricks = 0;
    List<Brick> bricks = new ArrayList<>();
    Brick brick;
    for (int brickType = 0; brickType < BrickFactory.TOTAL_TYPES && scan.hasNextInt();
        brickType++) {
      numBricks = scan.nextInt();
      for (int j = 0; j < numBricks; j++) {
        brick = BrickFactory.create(brickListener, brickType);
        bricks.add(brick);
      }
      Collections.shuffle(bricks);
    }
    int p = 0;
    for (int i = 0; p < bricks.size(); i++) {
      for (int j = 0; j < 10 && p < bricks.size(); j++) {
        bricks.get(p).setX(100 * j);
        bricks.get(p).setY(70 * i);
        addObject(bricks.get(p));
        p++;
      }
    }

    this.remainingBricks = bricks.size();
  }

  private void youWin() {

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
      case L:
        secondPaddle.setDirectionX(0);
        break;
      case A:
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

    private boolean invincible;
    private int points = 0;
    private int moneyMultiplier = 1;

    public void removeObject(GameObject object) {
      removeGameObject(object);
    }

    public void toggleInvincibility() {
      Thread thread = new Thread(() -> {
        try {
          Thread.sleep(5000);
          invincible = false;
        } catch (InterruptedException err) {
          err.printStackTrace();
        }
      });
      invincible = true;
      thread.start();

    }

    public void setPaddleSpeed(int speed) {
      paddle.setSpeed(speed);
      secondPaddle.setSpeed(speed);
    }

    public void speedBoost() {
      setPaddleSpeed(500);
      Thread thread = new Thread(() -> {
        try {
          Thread.sleep(7500);
          setPaddleSpeed(PADDLE_SPEED);
        } catch (InterruptedException err) {
          err.printStackTrace();
        }
      });
      thread.start();
    }

    public void setStatus() {
      statusText.setText(String.format(STATUS, health, points));
    }

    public void decrementHealth() {
      if (!invincible) {
        modHealth(-1);
      }
    }

    public void moneyBonus() {
      Thread thread = new Thread(() -> {
        try {
          Thread.sleep(10000);
          moneyMultiplier = 1;
        } catch (InterruptedException err) {
          err.printStackTrace();
        }
      });
      moneyMultiplier = 10;
      thread.start();
    }

    private void modHealth(int amount) {
      health += amount;
      String type = amount < 0 ? "paddleHit.png" : "paddleHealed.png";
      System.out.println(health);
      Thread thread = new Thread(() -> {
        try {
          Thread.sleep(1000);
          setPaddleImage("paddle.png");
        } catch (InterruptedException err) {
          err.printStackTrace();
        }
      });
      thread.start();
      setPaddleImage(type);
      setStatus();
    }

    private void setPaddleImage(String image) {
      paddle.setImage(image);
      secondPaddle.setImage(image);
    }

    public void incrementHealth() {
      modHealth(1);
    }

    public void createBall() {
      Ball newBall = new Ball(BALL_SPEED, -1, -1, false, "weakBall.gif", brickListener);
      centerBall(newBall);
      addObject(newBall);
    }

    public void pausePaddle() {
      setPaddleSpeed(0);
      Thread thread = new Thread(() -> {
        try {
          Thread.sleep(4500);
          setPaddleSpeed(PADDLE_SPEED);
          setPaddleImage("paddle.png");
        } catch (InterruptedException err) {
          err.printStackTrace();
        }
      });
      setPaddleImage("paddleFrozen.png");
      thread.start();
    }

    public double getWidth() {
      return sceneWidth;
    }

    public void addPoints(int amount) {
      points += amount * moneyMultiplier;
      setStatus();
    }

    public double getHeight() {
      return sceneHeight;
    }
  }
}
