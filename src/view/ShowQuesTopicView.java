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
import util.Topic;

public class ShowQuesTopicView implements  I_ViewCo {
	//FXML variables//
	@FXML
	private Button Requirements;
	@FXML
	private Button Agile;
	@FXML
	private Button TDD;
	@FXML
	private Button ConfigurationManagement;
	@FXML
	private Button SoftwareArchitecture;
	@FXML
	private Button SOAandCloud;
	@FXML
	private Button DesignPatterns;
	@FXML
	private Button SoftwareTesting;
	@FXML
	private Button Maintenance;
	@FXML
	private Button CostsandRisks;
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
	
	}

	/**
	 * Main function-manage the section
	 * @throws IOException
	 */
	public void action(ActionEvent e) throws IOException, InterruptedException {
	
	
		
		//wants to buy//
		if (e.getSource() == Requirements){
			//calling the method from quesControll
			control.manageQuestionSquare( player, Topic.Requirements);
		}
		
		if (e.getSource() == Agile){
			//calling the method from quesControll
			control.manageQuestionSquare( player, Topic.Agile);
		}
		if (e.getSource() == TDD){
			//calling the method from quesControll
			control.manageQuestionSquare( player, Topic.TDD);
		}
		if (e.getSource() == ConfigurationManagement){
			//calling the method from quesControll
			control.manageQuestionSquare( player, Topic.ConfigurationManagement);
		}
		if (e.getSource() == SoftwareArchitecture){
			//calling the method from quesControll
			control.manageQuestionSquare( player, Topic.SoftwareArchitecture);
		}
		if (e.getSource() == SOAandCloud){
			//calling the method from quesControll
			control.manageQuestionSquare( player, Topic.SOAandCloud);
		}
		if (e.getSource() == DesignPatterns){
			//calling the method from quesControll
			control.manageQuestionSquare( player, Topic.DesignPatterns);
		}
		if (e.getSource() == SoftwareTesting){
			//calling the method from quesControll
			control.manageQuestionSquare( player, Topic.SoftwareTesting);
		}
		if (e.getSource() == Maintenance){
			//calling the method from quesControll
			control.manageQuestionSquare( player, Topic.Maintenance);
		}
		if (e.getSource() == CostsandRisks){
			//calling the method from quesControll
			control.manageQuestionSquare( player, Topic.CostsandRisks);
		}
		

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
		Stage stage = (Stage) Agile.getScene().getWindow();
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