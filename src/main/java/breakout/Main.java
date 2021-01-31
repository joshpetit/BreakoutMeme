package breakout;

import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * The platform which manages the gameloop and window size.
 */
public class Main extends Application {

  private Scene scene;
  private final int sceneWidth = 1000;
  private final int sceneHeight = 750;
  private List<GameObject> gameObjects;
  private Group platform;

  @Override
  public void start(Stage stage) {
    platform = new Group();
    scene = new Scene(platform, sceneWidth, sceneHeight);
    gameObjects = new ArrayList<>();

    ImageView bg = new ImageView();
    Image image = new Image(Main.class.getResourceAsStream("splash.png"));
    bg.setImage(image);
    platform.getChildren().add(bg);
    stage.setScene(scene);
    stage.setTitle("floating");
    stage.show();

    scene.setOnKeyPressed(e -> {
      scene.setOnKeyPressed(ev -> {
      });
      GameCore core = new GameCore(platform, gameObjects, sceneWidth, sceneHeight);
      core.nextLevel();
      platform.requestFocus();
    });

    KeyFrame frame = new KeyFrame(Duration.seconds(1.0 / 60), e -> step(1.0 / 60));
    Timeline animation = new Timeline();
    animation.setCycleCount(Timeline.INDEFINITE);
    animation.getKeyFrames().add(frame);
    animation.play();
  }

  /**
   * Moves the objects on the platform.
   */
  private void step(double elapsedTime) {
    for (int i = 0; i < gameObjects.size(); i++) {
      checkBounds(gameObjects.get(i));
    }

    for (GameObject obj : gameObjects) {
      obj.setX(obj.getX() + (obj.getDirectionX() * obj.getSpeed()) * elapsedTime);
      obj.setY(obj.getY() + (obj.getDirectionY() * obj.getSpeed()) * elapsedTime);
    }
  }

  /**
   * Checks to see if any game objects have collided with the passed in object.
   *
   * @param block - The GameObject to check against others to see if any collisions have occured.
   */
  private void checkBounds(GameObject block) {
    if (block.getX() <= 0 || (block.getX() >= sceneWidth - block.getBoundsInParent().getWidth())) {
      block.fireEvent(new HitEvent(GameObject.TYPE.WALL));
    }

    if (block.getY() <= 0) {
      block.fireEvent(new HitEvent(GameObject.TYPE.WALL));
    } else if (block.getY() >= sceneHeight - block.getBoundsInParent().getHeight()) {
      block.fireEvent(new HitEvent(GameObject.TYPE.HOT_WALL));
    }

    for (int i = 0; i < gameObjects.size(); i++) {
      GameObject test = gameObjects.get(i);
      if (test != block && (block.getBoundsInParent().intersects(test.getBoundsInParent()))) {
        block.fireEvent(new HitEvent(test.getType()));
      }
    }
  }

  /**
   * Starts the platform for game execution.
   */
  public static void main(String[] args) {
    launch(args);
  }
}
