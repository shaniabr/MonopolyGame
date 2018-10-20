package view;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class OpenScreenView extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader = new FXMLLoader(getClass().getResource("openScreen.fxml"));
		Parent root = loader.load();
		Scene scene = new Scene (root);
		FadeTransition ft = new FadeTransition(Duration.millis(4000),root);
		ft.setFromValue(0.0);
		ft.setToValue(1.0);
		ft.play();
		stage.setScene(scene);
		stage.show();
		PauseTransition delay = new PauseTransition(Duration.seconds(3));
		delay.setOnFinished( event -> stage.close() );
		delay.play();

	}
	

	public static void startScreen() {
		launch();
	}
}