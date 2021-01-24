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
		scene = new Scene(group);
		stage.setScene(scene);
		stage.setTitle("AHHAHAHAHAHAHAH");
		stage.show();
		// attach "game loop" to timeline to play it (basically just calling step() method repeatedly forever)
	}

    public static void main (String[] args) {
        launch(args);
    }
}
