package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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


public class ChoosePlayersNumView implements Initializable  {
	
	@FXML
	private Button one;
	@FXML
	private Button two;
	@FXML
	private Button three;
	@FXML
	private Button four;
	@FXML
	private Button back;
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public void action(MouseEvent e) throws IOException {
		
		if (e.getSource() == one){
			GeneralView.openPage(one, "playersDetails.fxml", this ,new PlayersDetailsView(),1);

		}
		else if (e.getSource() == two){
			GeneralView.openPage(two, "playersDetails.fxml", this ,new PlayersDetailsView(),2);

		}
		else if (e.getSource() == three){
			GeneralView.openPage(three, "playersDetails.fxml", this ,new PlayersDetailsView(),3);

		}
		else if (e.getSource() == four){
			GeneralView.openPage(four, "playersDetails.fxml", this ,new PlayersDetailsView(),4);

		}
		else {
			GeneralView.openPage(back,"mainPage.fxml", this );

		}
		
	}
		
		
}
