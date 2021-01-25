package breakout;
import javafx.application.Application;
import java.util.ArrayList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	Scene scene;
	private int SCENE_SIZE = 500;
	List<GameObject> gameObjects;
	Group platform;

	@Override
	public void start (Stage stage) {
		platform = new Group();
		scene = new Scene(platform, 500, 500);
		gameObjects = new ArrayList<>();
		GameCore core = new GameCore(platform, gameObjects, scene);

		stage.setScene(scene);
		stage.setTitle("floating");
		stage.show();

		KeyFrame frame = new KeyFrame(Duration.seconds(1.0 / 60), e -> step(1.0 / 60));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	private void step (double elapsedTime) {
		for (int i = 0; i < gameObjects.size(); i++) {
			checkBounds(gameObjects.get(i));
		}

		for (GameObject obj : gameObjects) {
			obj.setX(obj.getX() + (obj.getDirectionX() * obj.getSpeed()) * elapsedTime);
			obj.setY(obj.getY() + ( obj.getDirectionY() * obj.getSpeed()) * elapsedTime);
		}
	}

	private void checkBounds(GameObject block) {
		if (block.getX() <= 0) {
			block.fireEvent(new HitEvent(2, GameObject.TYPE.WALL));
		} else if (block.getX() >= scene.getWidth() - block.getBoundsInParent().getWidth()) {
			block.fireEvent(new HitEvent(2, GameObject.TYPE.WALL));
		}

		if (block.getY() <= 0) {
			block.fireEvent(new HitEvent(2, GameObject.TYPE.WALL));
		} else if (block.getY() >= scene.getHeight() - block.getBoundsInParent().getHeight()) {
			block.fireEvent(new HitEvent(2, GameObject.TYPE.HOT_WALL));
		}

		for (int i = 0; i < gameObjects.size(); i++) {
			GameObject test = gameObjects.get(i);
			if (test != block) {
				if (block.getBoundsInParent().intersects(test.getBoundsInParent())) {
					block.fireEvent(new HitEvent(0, test.getType()));
				}
			}
		}
	}


	public static void main(String[] args) {
		launch(args);
	}
}
