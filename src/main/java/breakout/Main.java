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
	List<GameObject> items;
	Group group;

	@Override
	public void start (Stage stage) {
		group = new Group();
		scene = new Scene(group);
		GameObject ball = new Ball(70.0, -1, -1, GameObject.TYPE.HOT_BALL, "ball.gif");
		Brick brick = new Brick();
		Brick brick2 = new Brick();
		brick2.setX(300);
		brick2.setY(300);
		items = new ArrayList<>();
		items.add(ball);
		items.add(brick);
		items.add(brick2);

		ball.setX(SCENE_SIZE / 2 - ball.getBoundsInLocal().getWidth() / 2);
		ball.setY(SCENE_SIZE / 2 - ball.getBoundsInLocal().getHeight() / 2);

		ball.setX(500);
		ball.setY(500);

		group.getChildren().addAll(ball, brick, brick2);
		ball.fireEvent(new HitEvent(2, GameObject.TYPE.HOT_WALL));

		stage.setScene(scene);
		stage.setTitle("AHHAHAHAHAHAHAH");
		stage.show();

		ball.setX(scene.getWidth() / 2 - ball.getBoundsInLocal().getWidth() / 2);
		KeyFrame frame = new KeyFrame(Duration.seconds(1.0 / 60), e -> step(1.0 / 60));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	private void step (double elapsedTime) {
		for (int i = 0; i < items.size(); i++) {
			checkBounds(items.get(i));
		}

		for (GameObject obj : items) {
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

		for (GameObject static_bloc : items) {
			if (static_bloc != block) {
				if (block.getBoundsInParent().intersects(static_bloc.getBoundsInParent())) {
					block.fireEvent(new HitEvent(0, static_bloc.getType()));
				}
			}
		}
	}


	public static void main(String[] args) {
		launch(args);
	}

	class Brick extends GameObject {

		public Brick() {
			super(0, 0, 0, GameObject.TYPE.BRICK, "brick.png");
			this.command = (event) -> {
				switch (event.getStrickedType()) {
				case HOT_BALL:
					System.out.println("AHHHH!!!");
					items.remove(this);
					group.getChildren().remove(this);
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
