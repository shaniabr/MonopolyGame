package view;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainPageView implements Initializable {
	
	@FXML
	private Button startGame;
	@FXML
	private Button scoreBoard;
	@FXML
	private Button information;
	@FXML
	private Button adminLogin;
	@FXML
	private Button exit;
	
	private ShowBackAlertView sbav;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void action(MouseEvent e) throws IOException {

		if (e.getSource() == startGame){
			GeneralView.openPage(startGame,"choosePlayersNum.fxml", this);
		}
		
		else if (e.getSource() == adminLogin){
			GeneralView.openPage(adminLogin,"adminlogin.fxml", this);
			}
			//added by ron//
		else if (e.getSource() == information){
			GeneralView.openPage(information,"information.fxml", this);
			}
		else if (e.getSource() == scoreBoard){
			GeneralView.openPage(scoreBoard,"Scoreboard.fxml", this);
			}
		else {
			Stage s = (Stage) exit.getScene().getWindow();
			GeneralView.openPageAlert( "ExitAlert.fxml", this,sbav, s);

			}
		
	}

	
	
	


}