package breakout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * Manages objects on the platform and gives objects access to manipulate the platform.
 */
public class GameCore {

  private static final String STATUS = "Health: %d Points: %d Level: %d";
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
  private int level = 0;
  private Text statusText;
  private ImageView background;

  /**
   * Constructs a core manager for the game platform.
   *
   * @param platform    - The display area for game objects.
   * @param gameObjects - A reference to the gameobjects managed by the gameloop.
   * @param sceneWidth  - How wide the window is intended to be.
   * @param sceneHeight - How tall the window is intended to be.
   */
  public GameCore(Group platform, List<GameObject> gameObjects, int sceneWidth, int sceneHeight) {
    this.platform = platform;
    this.gameObjects = gameObjects;
    this.sceneWidth = sceneWidth;
    this.sceneHeight = sceneHeight;

    background = new ImageView();
    Image image = new Image(GameCore.class.getResourceAsStream("bg1.png"));
    background.setImage(image);
    platform.getChildren().add(background);

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

    statusText = new Text(String.format(STATUS, health, 0, 0));
    statusText.setX(sceneWidth - statusText.getBoundsInParent().getWidth() - 50);
    statusText.setY(sceneHeight - 10);
    statusText.setFill(Color.WHITE);
    platform.getChildren().add(statusText);

    platform.setOnKeyPressed(
        (e) -> {
          movePaddle(e.getCode());
          cheat(e.getCode());
        });

    platform.setOnKeyReleased(
        (e) -> {
          stopPaddle(e.getCode());
        });
  }

  /**
   * Centers balls added to the platform.
   *
   * @param ball - The ball to be centered.
   */
  protected void centerBall(Ball ball) {
    ball.setX(sceneWidth / 2);
    ball.setY(sceneHeight / 2);
  }

  /**
   * Handles potential cheat keys.
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
      default:
        break;
    }
  }

  /**
   * Ends the game with a win screen.
   */
  public void youWin() {
    endGame("winscreen.png");
  }

  /**
   * Ends the game with a lose screen.
   */
  public void youLose() {
    endGame("losescreen.png");
  }

  private void endGame(String fileName) {
    background = new ImageView();
    platform.getChildren().removeAll();
    for (int i = 0; i < gameObjects.size(); i++) {
      gameObjects.remove(i);
    }
    platform.getChildren().add(background);
    Image image = new Image(Main.class.getResourceAsStream(fileName));
    this.background.setImage(image);
  }

  /**
   * Advances the game to the next level.
   */
  public void nextLevel() {
    level++;
    brickListener.incrementHealth();

    System.out.println("Starting next Level...");
    if (!scan.hasNextInt()) {
      youWin();
    }
    centerBall(ball);
    ball.setDirectionY(-1);

    int numBricks = 0;
    List<Brick> bricks = new ArrayList<>();
    Brick brick;
    for (int brickType = 0;
        brickType < BrickFactory.TOTAL_TYPES && scan.hasNextInt();
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
      for (int j = 0; j < 10 && p < bricks.size(); j++, p++) {
        bricks.get(p).setX(100 * j);
        bricks.get(p).setY(70 * i);
        addObject(bricks.get(p));
      }
    }

    this.remainingBricks = bricks.size();
  }

  /**
   * Adds an object to the platform.
   *
   * @param obj - The object to be added to the platform.
   */
  public void addObject(GameObject obj) {
    gameObjects.add(obj);
    platform.getChildren().add(obj);
  }

  /**
   * Removes an object from the platform.
   *
   * @param obj - Removes the object at this reference.
   */
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

  /**
   * Stops Resets the velocity of the paddles to zero.
   *
   * @param code - The key which was released which dictates which paddle should be suspended. H
   *             and. L for the second paddle and A and F for the first.
   */
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
      default:
        break;
    }
  }

  /**
   * Sets the velocity of the paddle.
   *
   * @param code - The key that was pressed. H and L moves the second paddle left and right
   *             respectively. A and F move the second paddle Right and Left respectively.
   */
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
      case SPACE:
        brickListener.launchBall();
        break;
      case SHIFT:
        brickListener.launchBoost();
        break;
      default:
        break;
    }
  }

  /**
   * Provides game objects access to manipulate the platform objects.
   */
  public class BrickListener implements ActionListener {

    private boolean invincible;
    private int points = 0;
    private int moneyMultiplier = 1;

    /**
     * Removes the specified object from the platform.
     *
     * @param object - The reference to the object to be removed.
     */
    public void removeObject(GameObject object) {
      removeGameObject(object);
    }

    /**
     * Launches a ball from the paddle if the player has enough points.
     */
    public void launchBall() {
      if (points >= 50) {
        points -= 50;
        Ball newBall = new Ball(BALL_SPEED, -1, -1, false, "weakBall.gif", brickListener);
        newBall.setX(paddle.getX());
        newBall.setY(paddle.getY());
        addObject(newBall);
        setStatus();
      }
    }

    /**
     * Boosts the paddle speeds if the player has enough points.
     */
    public void launchBoost() {
      if (points >= 20) {
        points -= 20;
        speedBoost();
        setStatus();
      }
    }

    /**
     * Tells the platform to prevent damage from being delt to the player.
     */
    public void toggleInvincibility() {
      Thread thread =
          new Thread(
              () -> {
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

    /**
     * Sets the speed of both paddles.
     *
     * @param speed - The speed to set both paddles to.
     */
    public void setPaddleSpeed(int speed) {
      paddle.setSpeed(speed);
      secondPaddle.setSpeed(speed);
    }

    /**
     * Tells the platform to speed up the paddles.
     */
    public void speedBoost() {
      setPaddleSpeed(500);
      Thread thread =
          new Thread(
              () -> {
                try {
                  Thread.sleep(7500);
                  setPaddleSpeed(PADDLE_SPEED);
                } catch (InterruptedException err) {
                  err.printStackTrace();
                }
              });
      thread.start();
    }

    /**
     * Refreshes the game status with updated statistics.
     */
    public void setStatus() {
      statusText.setText(String.format(STATUS, health, points, level));
    }

    /**
     * Deals damage to the player.
     */
    public void decrementHealth() {
      if (!invincible) {
        modHealth(-1);
      }
    }

    /**
     * Multiplies the the amount of points given to the user.
     */
    public void moneyBonus() {
      Thread thread =
          new Thread(
              () -> {
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
      if (health <= 0) {
        youLose();
        return;
      }
      String type = amount < 0 ? "paddleHit.png" : "paddleHealed.png";
      System.out.println(health);
      Thread thread =
          new Thread(
              () -> {
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

    /**
     * Adds health to the player.
     */
    public void incrementHealth() {
      modHealth(1);
    }

    /**
     * Adds an extra ball to the platform.
     */
    public void createBall() {
      Ball newBall = new Ball(BALL_SPEED, -1, -1, false, "weakBall.gif", brickListener);
      centerBall(newBall);
      addObject(newBall);
    }

    /**
     * Pauses the paddle within the game.
     */
    public void pausePaddle() {
      setPaddleSpeed(0);
      Thread thread =
          new Thread(
              () -> {
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

    /**
     * Retrieves the width of the current scene.
     */
    public double getWidth() {
      return sceneWidth;
    }

    /**
     * Gives points to the player.
     *
     * @param amount - The amount of points to be added to the player's score.
     */
    public void addPoints(int amount) {
      points += amount * moneyMultiplier;
      setStatus();
    }

    /**
     * Retrieves the height of the current scene.
     */
    public double getHeight() {
      return sceneHeight;
    }
  }
}
