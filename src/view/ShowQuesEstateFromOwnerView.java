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

public class ShowQuesEstateFromOwnerView implements  I_ViewCo {
	//FXML variables//
	@FXML
	private Button yes;
	@FXML
	private Button no;
	@FXML
	private Label question;
	@FXML
	private Label question1;
	@FXML
	private ImageView dialogBox;
	@FXML
	private AnchorPane compPane;

	//class's variables//

	private Estate estate;
	private Player player;
//	private controller.QuestionController control=controller.QuestionController.getQCInstance();

	/**
	 * initiate all labels 
	 * @throws IOException
	 */
	public void init() {
		question1.setText(estate.getName()+"?"+"\n"+"The price for buy is: "+estate.getcurrentPrice()*1.5
		+"\n"+"The price for rent is: "+estate.getcurrentPrice()*0.15);
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
		estate=GameController.getGCInstance().getCurrrentEstate();
		
		//wants to buy//
		if (e.getSource() == yes){
			yes.setVisible(false);
			no.setVisible(false);

			question.setText("Congratulations! You are the owner of "+estate.getName()+"/n"+"Your new buget is: "
			+(player.getBudget()-estate.getcurrentPrice()*1.5));
			question.setVisible(true);
			
			//calling the method from quesControll
			GameController.getGCInstance().buyEstateFromOwner(estate,player);
		}
		
		//doesn't want to buy//
		if (e.getSource() == no){
			yes.setVisible(false);
			no.setVisible(false);

			question.setText("Your new buget is: "
			+(player.getBudget()-estate.getcurrentPrice()*0.15));
			question.setVisible(true);
			
			//calling the method from quesControll
			GameController.getGCInstance().rentEstate(estate,player);
		    	        
		}
				 //Wait few seconds and close automatically the question window
			      Timeline timeline = new Timeline(new KeyFrame(
			    	        Duration.millis(1500),
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
			Object o2 = ((ArrayList) question).get(1);

			if (o1 instanceof Estate)
				this.estate=(Estate) o1;
			if (o2 instanceof Player)
				this.player = (Player) o2;

		}

		init();
	}
}