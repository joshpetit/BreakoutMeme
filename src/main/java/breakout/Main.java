package breakout;
import javafx.application.Application;
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

/**
 * Feel free to completely change this code or delete it entirely.
 */
public class Main extends Application {
	Scene scene;

	@Override
	public void start (Stage stage) {
		// attach scene to the stage and display it
		Group group = new Group();

		GameObject obj = new Ball(5.0, 5, 5, GameObject.TYPE.WALL, "ball.gif");
		obj.addEventHandler(HitEvent.HIT, event -> {
			System.out.println(event.getStrickedType());
		});

		obj.addEventHandler(HitEvent.HIT, event -> {
			System.out.println("OK");
		});

		group.getChildren().add(obj);
		System.out.println("
		obj.fireEvent(new HitEvent(2, GameObject.TYPE.HOT_WALL));
		scene = new Scene(group);
		stage.setScene(scene);
		stage.setTitle("AHHAHAHAHAHAHAH");
		stage.show();
		// attach "game loop" to timeline to play it (basically just calling step() method repeatedly forever)
	}

	public static void main (String[] args) {
		launch(args);
	}

	class Ball extends GameObject {

		public Ball(double speed, int directionX, int directionY, GameObject.TYPE type, String image) {
			super(speed, directionX, directionY, type, image);

		}

		public void onHit(HitEvent event) {

		}

	}
}
