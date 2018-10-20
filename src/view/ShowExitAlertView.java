package view;

import java.io.IOException;

import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;


import controller.GameController;
import controller.ScoreboardController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Game;

public class ShowExitAlertView implements Initializable, I_ViewCo {
	
	@FXML
	private Button yes;
	@FXML
	private Button no;
	@FXML
	public Label textAlert= new Label() ;
	
	private Stage s;
	


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
	}
	
	public void setData(Object obj) {
		if (obj instanceof Stage) {
			s = (Stage) obj;
		}
	}
	
	public void leaveGame(Stage s) throws IOException {
		s.close();
	}
	
		
	public void alertAction(MouseEvent e) throws IOException, ParseException {
		if (e.getSource()==no)  {
		    GeneralView.closeStage(no);	
		}
		else { 
		    GeneralView.closeStage(yes);	
			leaveGame(s);
		}	
	}

}