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

public class ShowQuesEstateNoOwnerView implements I_ViewCo {
	//FXML variables//
	@FXML
	private Button yes;
	@FXML
	private Button no;
	@FXML
	private Button finish;
	@FXML
	private Label question;
	@FXML
	private Label answer1;
	@FXML
	private Label answer2;
	@FXML
	private Label answer3;
	@FXML
	private Label answer4;
	@FXML
	private CheckBox one;
	@FXML
	private CheckBox two;
	@FXML
	private CheckBox three;
	@FXML
	private CheckBox four;
	@FXML
	private ImageView dialogBox;
	@FXML
	private Label rightOrWrongLabel;
	@FXML
	private AnchorPane compPane;


	//class's variables//
		private boolean flagAnswer;
		private Question q1; // Question//
		private Estate e1; // current Estate//
		private Player player;	//current player//
		private controller.QuestionController control=controller.QuestionController.getQCInstance();
		ArrayList<Integer> answers = new ArrayList<>();
		ArrayList<String> right_answers = new ArrayList<>();
	/**
	 * Get User Answers
	 * @return
	 */
	private ArrayList<Integer> answerIndex(){
		ArrayList<Integer>index = new ArrayList<>();
		if(one.isSelected())
			index.add(0);
		if(two.isSelected())
			index.add(1);
		if(three.isSelected())
			index.add(2);
		if(four.isSelected())
			index.add(3);

		return index;
	}
	/**
	 * Check the player pick at least One answer
	 * @throws IOException
	 */
	public boolean validateAnswer() throws IOException {
		if(finish.getText().equals("OK"))
		{

			rightOrWrongLabel.setVisible(false);
			answer1.setVisible(true);
			answer2.setVisible(true);
			if(q1.getAnswer().size()>2)
			{
				answer3.setVisible(true);
				answer4.setVisible(true);
				three.setVisible(true);		
				four.setVisible(true);

			}
			one.setVisible(true);
			two.setVisible(true);

			finish.setText("Submit");
			return false;

		}
		else if (!one.isSelected() && !two.isSelected() && !three.isSelected() && !four.isSelected()) {	
			rightOrWrongLabel.setText("Please select at least\n"+"one correct answer!");
			rightOrWrongLabel.setVisible(true);
			answer1.setVisible(false);
			answer2.setVisible(false);
			answer3.setVisible(false);
			answer4.setVisible(false);
			one.setVisible(false);
			two.setVisible(false);
			three.setVisible(false);
			finish.setText("OK");
			four.setVisible(false);

			return false;
		}
		return true;
	}

	/**
	 * Clear all answers but first option
	 * @throws IOException
	 */
	public void ClearAnswerButFirst() throws IOException {  
		if(!q1.getMultyQuest())
			if (one.isSelected()) {	
				two.setSelected(false);
				three.setSelected(false);
				four.setSelected(false);
			}
	}
	/**
	 * Clear all answers but second option
	 * @throws IOException
	 */
	public void ClearAnswerButSecond() throws IOException { 
		if(!q1.getMultyQuest())
			if (two.isSelected()) {	
				one.setSelected(false);
				three.setSelected(false);
				four.setSelected(false);
			}
	}
	/**
	 * Clear all answers but third option
	 * @throws IOException
	 */
	public void ClearAnswerButThird() throws IOException {  
		if(!q1.getMultyQuest())
			if (three.isSelected()) {	
				one.setSelected(false);
				two.setSelected(false);
				four.setSelected(false);
			}
	}

	/**
	 * Clear all answers but third option
	 * @throws IOException
	 */
	public void ClearAnswerButForth() throws IOException {  
		if(!q1.getMultyQuest())
			if (four.isSelected()) {	
				one.setSelected(false);
				two.setSelected(false);
				three.setSelected(false);
			}
	}
	/**
	 * initiate all labels 
	 * @throws IOException
	 */
	public void init() {
		question.setText("Do you want to buy the esate:"+"\n"+e1.getName()+"?"+"\n"+"The price is: "+e1.getvalue());
		question.setVisible(true);
		yes.setVisible(true);
		no.setVisible(true);
		finish.setVisible(false);

	}

	/**
	 * Main function-manage the section
	 * @throws IOException
	 */
	public void action(ActionEvent e) throws IOException, InterruptedException {
		double pointsPerQuestion=player.getBudget();
		double point=1;
		
		String diff=GameController.getGCInstance().manaeEatateDegree(e1);
		if(diff=="EXPENSIVE")
			point=0.75;
		if(diff=="AVERAGE")
			point= 0.85;
		if(diff=="CHEAP")
			point=0.95;
			
			
		//wants to buy//
		if (e.getSource() == yes)
		{
			yes.setVisible(false);
			no.setVisible(false);
			finish.setVisible(true);
			
			answer1.setText(q1.getAnswer().get(0).getText());
			answer2.setText(q1.getAnswer().get(1).getText());
			
			answer1.setVisible(true);
			answer2.setVisible(true);

			one.setVisible(true);
			two.setVisible(true);
			
			if(q1.getAnswer().size()>2)
			{
				answer3.setText(q1.getAnswer().get(2).getText());
				answer4.setText(q1.getAnswer().get(3).getText());
				answer3.setVisible(true);
				answer4.setVisible(true);
				three.setVisible(true);
				four.setVisible(true);

			}
			
			question.setText(q1.getText());
			
			//saving the right answers in aim to show it to the players//
			for(Answer a:q1.getAnswer())
			{
				if(a.getRightOrWrong())
				{
					right_answers.add(a.getText());
				}
			}
	
		}

		//doesn't want to buy//
		if (e.getSource() == no){
			GameController.getGCInstance().endTurn(player);
			//Wait few seconds and close automatically the question window
			Timeline timeline = new Timeline(new KeyFrame(
					Duration.millis(100),
					ae -> {
						closeWindow();	        	
					}));
			timeline.play();

		}

		if (e.getSource() == finish){
			if(validateAnswer())
			{
				answers = answerIndex();
				if(control.checkIfAnswerIsRight(q1, answers,player))
				{

					flagAnswer=true;


					rightOrWrongLabel.setText("You're Right!\n"+"The estate price is: "+e1.getvalue()*point);
					rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"The answer is:");
					rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+right_answers);
					rightOrWrongLabel.setVisible(true) ;

					answer1.setVisible(false);
					answer2.setVisible(false);
					answer3.setVisible(false);
					answer4.setVisible(false);
					one.setVisible(false);
					two.setVisible(false);
					three.setVisible(false);
					four.setVisible(false);
					question.setVisible(false);
					finish.setVisible(false);
					GameController.getGCInstance().updateBuyEstate(player,true);

				}
				else		//mistake//
				{
					rightOrWrongLabel.setText("You're Wrong!\n"+"The estate price is: "+e1.getvalue());
					rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"The answer is:");
					rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+right_answers);
					
					rightOrWrongLabel.setVisible(true) ;
					finish.setVisible(false);
					answer1.setVisible(false);
					answer2.setVisible(false);
					answer3.setVisible(false);
					answer4.setVisible(false);
					one.setVisible(false);
					two.setVisible(false);
					three.setVisible(false);
					four.setVisible(false);
					question.setVisible(false);
					GameController.getGCInstance().updateBuyEstate(player,false);
				}
				//Wait few seconds and close automatically the question window
				
				Timeline timeline = new Timeline(new KeyFrame(
						Duration.millis(4000),
						ae -> {
							closeWindow();	        	
						}));
				timeline.play();

			}
		}
	}

	/**
	 * The method close the window automatically
	 */
	private void closeWindow() {
		Stage stage = (Stage) finish.getScene().getWindow();
		stage.close();
	}


	public void setData(Object question) {
		if (question instanceof ArrayList<?>) {
			Object o1 = ((ArrayList) question).get(0);
			Object o2 = ((ArrayList) question).get(1);
			Object o3 = ((ArrayList) question).get(2);

			if (o1 instanceof Question)
				this.q1 = (Question) o1;
			if (o2 instanceof Estate)
				this.e1=(Estate) o2;
			if (o3 instanceof Player)
				this.player = (Player) o3;

		}

		init();
	}

}