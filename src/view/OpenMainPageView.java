package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class OpenMainPageView implements Initializable {

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Parent root= null;
		try {
			root = FXMLLoader.load(getClass().getResource("mainPage.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();
	}
}
