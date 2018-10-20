package view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InformationView implements Initializable {
	@FXML
	Button b;
	@FXML
	Button back;
	@FXML
	ImageView img;
	@FXML
	ImageView img2;
	@FXML
	Button backToMain;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       // System.out.println("View is now loaded!");
    	
    	img2.setVisible(false);
    	img.setVisible(true);
    	b.setVisible(true);
    	back.setVisible(false);
    }
    
    public void info2() {
    	
    	img.setVisible(false);
    	img2.setVisible(true);
    	b.setVisible(false);
    	back.setVisible(true);
         
     }
    public void back() {
    	
    	img2.setVisible(false);
    	img.setVisible(true);
    	b.setVisible(true);
    	back.setVisible(false);
         
     }
public void backToMain() throws IOException {

	GeneralView.openPageDecorated("mainPage.fxml", this);
	
	//Wait few seconds and close automatically the question window
	Timeline timeline = new Timeline(new KeyFrame(
			Duration.millis(0001),
			ae -> {
				closeWindow();	        	
			}));
	timeline.play();
		
	}




/**
* The method close the window automatically
*/
private void closeWindow() {
Stage stage = (Stage) backToMain.getScene().getWindow();
stage.close();
}
}