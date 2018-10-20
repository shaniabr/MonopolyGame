package view;

import java.io.IOException;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
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
import model.Player;

public class ShowBackAlertView implements Initializable, I_ViewCo {
	
	@FXML
	private Button yes;
	@FXML
	private Button no;
	@FXML
	public Label textAlert= new Label() ;
	
	private Stage s;
	private controller.GameController game = GameController.getGCInstance();// get instance of control
	private ScoreboardController sbc=ScoreboardController.getInstance();
	private ShowEndGameScores seg;

	private ArrayList<Player> players;// get players in end game

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
		players= game.CalculatePoints();

		if ((e.getSource()==no) && (textAlert.getText().equals("Are you sure you want to exit the game?"))) {
		    GeneralView.closeStage(no);	
		}
		else if((e.getSource()==no) && (!textAlert.getText().equals("Are you sure you want to exit the game?"))) {
			GeneralView.closeStage(no);
			ArrayList <Object> data=new ArrayList <Object>();
			data.add(s);
			data.add(players);
			openScores(data);
		}

		else if ((e.getSource()==yes) && (textAlert.getText().equals("Are you sure you want to exit the game?"))) {
			textAlert.setText("Do you want to save game results?");
		}
		else if ((e.getSource()==yes) && (!textAlert.getText().equals("Are you sure you want to exit the game?"))) {
			 if (sbc.addPlayersAndScores(new Game (players ,game.getGameDate())))
			 	System.out.println("history was added successfully");
			 else
			 System.out.println("couldn't add history");
			 
			GeneralView.closeStage(yes);
			ArrayList <Object> data=new ArrayList <Object>();
			data.add(s);
			data.add(players);
			openScores(data);
		}	
	}

	private void openScores(ArrayList<Object> data) throws IOException {
		GeneralView.openPageDecorated("endGameScores.fxml", this,seg,data );

		
	}

}