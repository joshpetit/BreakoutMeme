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
	GameObject ball;
	List<GameObject> items;
	Group group;

	@Override
	public void start (Stage stage) {
		group = new Group();
		scene = new Scene(group);
		ball = new Ball(20.0, -1, -1, GameObject.TYPE.HOT_BALL, "ball.gif");
		Brick brick = new Brick();
		items = new ArrayList<>();
		items.add(ball);
		items.add(brick);
		ball.setX(scene.getWidth() / 2 - ball.getBoundsInLocal().getWidth() / 2);

		group.getChildren().addAll(ball, brick);
		ball.fireEvent(new HitEvent(2, GameObject.TYPE.HOT_WALL));

		stage.setScene(scene);
		stage.setTitle("AHHAHAHAHAHAHAH");
		stage.show();

		KeyFrame frame = new KeyFrame(Duration.seconds(1.0 / 60), e -> step(1.0 / 60));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	private void step (double elapsedTime) {
		GameObject obj;
		for (int i = 0; i < items.size(); i++) {
			obj = items.get(i);
			if (obj.getX() <= 0) {
				obj.fireEvent(new HitEvent(2, GameObject.TYPE.WALL));
			} else if (obj.getX() >= scene.getWidth() - obj.getBoundsInParent().getWidth()) {
				obj.fireEvent(new HitEvent(2, GameObject.TYPE.WALL));
			}

			obj.setX(obj.getX() + (obj.getDirectionX() * obj.getSpeed()) * elapsedTime);

			if (obj.getY() <= 0) {
				obj.fireEvent(new HitEvent(2, GameObject.TYPE.WALL));
			} else if (obj.getY() >= scene.getHeight() - obj.getBoundsInParent().getHeight()) {
				obj.fireEvent(new HitEvent(2, GameObject.TYPE.HOT_WALL));
			}

			obj.setY(obj.getY() + ( obj.getDirectionY() * obj.getSpeed()) * elapsedTime);
		}
	}

	public static void main (String[] args) {
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
				}
			};
		}
	}

	class Ball extends GameObject {

		public Ball(double speed, int directionX, int directionY, GameObject.TYPE type, String image) {
			super(speed, directionX, directionY, type, image);
			this.command = (event) -> {
				switch (event.getStrickedType()) {
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
				case HOT_WALL:
					System.out.println("HOT WALL");
				}
			};
		}
	}
}
