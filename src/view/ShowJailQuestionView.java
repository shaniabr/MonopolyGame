package view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import controller.GameController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Answer;
import model.Estate;
import model.Player;
import model.Question;

public class ShowJailQuestionView implements  I_ViewCo {
	//FXML variables//
	@FXML
	private Button yes;
	@FXML
	private Button no;
	@FXML
	private Label question;
	@FXML
	private ImageView dialogBox;
	@FXML
	private AnchorPane compPane;

	//class's variables//

	private Player player;
	private controller.GameController control=controller.GameController.getGCInstance();

	/**
	 * initiate all labels 
	 * @throws IOException
	 */
	public void init() {
		question.setVisible(true);
		yes.setVisible(true);
		no.setVisible(true);
		
		
	}

	/**
	 * Main function-manage the section
	 * @throws IOException
	 */
	public void action(ActionEvent e) throws IOException, InterruptedException {
		double pointsPerQuestion=player.getBudget();

		//wants to pay & skip jail//
		if (e.getSource() == yes){
			yes.setVisible(false);
			no.setVisible(false);

			//calling the method from quesControll
			control.manageSkipJail(player);
		}
		
		//doesn't want to pay//
		if (e.getSource() == no){
			yes.setVisible(false);
			no.setVisible(false);
			//calling the method from quesControll
			control.manageEnterJail(player);
		    	        
		}
		
		
				 //Wait few seconds and close automatically the question window
			      Timeline timeline = new Timeline(new KeyFrame(
			    	        Duration.millis(0007),
			    	        ae -> {
			    	        	closeWindow();	        	
							}));
			    	        timeline.play();
			    	        
			}

	/**
	 * The method close the window automatically
	 */
	private void closeWindow() {
		Stage stage = (Stage) yes.getScene().getWindow();
		stage.close();
	}
	
	public void setData(Object question) {
		if (question instanceof ArrayList<?>) {
			Object o1 = ((ArrayList) question).get(0);

			if (o1 instanceof Player)
				this.player = (Player) o1;
			

		}

		init();
	}
}