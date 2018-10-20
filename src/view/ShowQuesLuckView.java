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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Answer;
import model.Player;
import model.Question;

public class ShowQuesLuckView implements I_ViewCo {
	//FXML variables//
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
	private boolean secondQues=false;
	private int count=0;
	private Question q1; //Medium Question//
	private Question q2;	//Hard Question//
	private Player player;	//current player//
	private controller.QuestionController control=controller.QuestionController.getQCInstance();
	ArrayList<Integer> answers = new ArrayList<>();
	private Boolean[] ans;
	ArrayList<String> right_answers = new ArrayList<>(); //right answers question 1
	ArrayList<String> right_answers2 = new ArrayList<>();  //right answers question 2
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
		boolean firstTimeQues2=false;
		if(finish.getText().equals("OK"))
		{
			if(secondQues)
			{
				rightOrWrongLabel.setVisible(false);
				answer1.setVisible(true);
				answer2.setVisible(true);
				if(q2.getAnswer().size()>2)
				{
					answer3.setVisible(true);
					answer4.setVisible(true);
					three.setVisible(true);		
					four.setVisible(true);
				}

				one.setVisible(true);
				two.setVisible(true);
				question.setVisible(true);
				finish.setText("Submit");
			}
			else
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
				question.setVisible(true);
				finish.setText("Submit");
			}
			return false;

		}
		else if (!one.isSelected() && !two.isSelected() && !three.isSelected() && !four.isSelected()) {	
			if(secondQues && !firstTimeQues2)
			{
				rightOrWrongLabel.setText("Get ready for question 2");
				rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"The answer is:");
				rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+right_answers);
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
				question.setVisible(false);
				return false;
			}
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
		if(question.equals(q1.getText()))    //q1 is the current question//
		{
			if(!q1.getMultyQuest())
				if (one.isSelected()) {	
					two.setSelected(false);
					three.setSelected(false);
					four.setSelected(false);
				}
		}

		if(question.equals(q2.getText()))    //q2 is the current question//
		{
			if(!q2.getMultyQuest())
				if (one.isSelected()) {	
					two.setSelected(false);
					three.setSelected(false);
					four.setSelected(false);
				}
		}

	}
	/**
	 * Clear all answers but second option
	 * @throws IOException
	 */
	public void ClearAnswerButSecond() throws IOException {
		if(question.equals(q1.getText()))    //q1 is the current question//
		{
			if(!q1.getMultyQuest())
				if (two.isSelected()) {	
					one.setSelected(false);
					three.setSelected(false);
					four.setSelected(false);
				}
		}

		if(question.equals(q2.getText()))    //q2 is the current question//
		{
			if(!q2.getMultyQuest())
				if (two.isSelected()) {	
					one.setSelected(false);
					three.setSelected(false);
					four.setSelected(false);
				}
		}
	}
	/**
	 * Clear all answers but third option
	 * @throws IOException
	 */
	public void ClearAnswerButThird() throws IOException { 
		if(question.equals(q1.getText()))    //q1 is the current question//
			if(!q1.getMultyQuest())
				if (three.isSelected()) {	
					one.setSelected(false);
					two.setSelected(false);
					four.setSelected(false);
				}

		if(question.equals(q2.getText()))    //q2 is the current question//
			if(!q2.getMultyQuest())
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
		if(question.equals(q1.getText()))    //q1 is the current question//
			if(!q1.getMultyQuest())
				if (four.isSelected()) {	
					one.setSelected(false);
					two.setSelected(false);
					three.setSelected(false);
				}

		if(question.equals(q2.getText()))    //q2 is the current question//
			if(!q2.getMultyQuest())
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

		//q=control.getCurrrentQues();
		ans=new Boolean[2];
		question.setText(q1.getText());
		question.setVisible(true);
		answer1.setText(q1.getAnswer().get(0).getText());
		answer2.setText(q1.getAnswer().get(1).getText());

		if(q1.getAnswer().size()>2)
		{
			answer3.setText(q1.getAnswer().get(2).getText());
			answer4.setText(q1.getAnswer().get(3).getText());

			answer3.setVisible(true);
			answer4.setVisible(true);
			three.setVisible(true);
			four.setVisible(true);

		}
		else	//2 ans or less//
		{
			answer3.setVisible(false);
			answer4.setVisible(false);
			three.setVisible(false);
			four.setVisible(false);
		}

		question.setVisible(true);
		finish.setVisible(true);


		answer1.setVisible(true);
		answer2.setVisible(true);

		one.setVisible(true);
		two.setVisible(true);


		//saving the right answers in aim to show it to the players- ques1//
				for(Answer a:q1.getAnswer())
				{
					if(a.getRightOrWrong())
					{
						right_answers.add(a.getText());
					}
				}
				
				//saving the right answers in aim to show it to the players- ques2//
				for(Answer a1:q2.getAnswer())
				{
					if(a1.getRightOrWrong())
					{
						right_answers2.add(a1.getText());
					}
				}

	}

	/**
	 * Main function-manage the section
	 * @throws IOException
	 */
	public void action(ActionEvent e) throws IOException, InterruptedException {
		int pointsPerQuestion=GameController.getGCInstance().randomForLuck();
		

		if (e.getSource() == finish &&  !secondQues)
		{
			if(validateAnswer())
			{
				answers = answerIndex();
				
				if(control.checkIfAnswerIsRight(q1, answers,player))
				{
					ans[0]=true;
					//System.out.println("answer1: "+ans[0]);
					flagAnswer=true;

				}
				else		//mistake//
				{
					ans[0]=(false);
					//System.out.println("answer1: "+ans[0]);
					flagAnswer=false;
				}

				secondQues=true;
				answer1.setText(q2.getAnswer().get(0).getText());
				answer2.setText(q2.getAnswer().get(1).getText());

				//more then 2 ans
				if(q2.getAnswer().size()>2)
				{

					answer3.setText(q2.getAnswer().get(2).getText());
					answer4.setText(q2.getAnswer().get(3).getText());
					three.setVisible(true);
					four.setVisible(true);
				}
				else	//2 ans or less//
				{
					answer3.setVisible(false);
					answer4.setVisible(false);
					three.setVisible(false);
					four.setVisible(false);
				}

				question.setText(q2.getText());
				question.setVisible(true);
				count++;
				answers = new ArrayList<Integer>();
				one.setSelected(false);
				two.setSelected(false);
				three.setSelected(false);
				four.setSelected(false);

			}


		}

		if (e.getSource() == finish && secondQues)
		{
			if(validateAnswer())
			{

				answers = answerIndex();
				count++;
				if(count>=2)	//the button was pressed twice at least//
				{
					if(control.checkIfAnswerIsRight(q2, answers,player))
					{
						ans[1]=(true);
						System.out.println("answer2: "+ans[1]);
						if(flagAnswer)
						{
						rightOrWrongLabel.setText("You were Right on both questions!\n"+"You earned "+pointsPerQuestion+"$");
						rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"The answer is:");
						rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+right_answers2);
						}
						else //mistake in the first-medium//
						{
							rightOrWrongLabel.setText("You were right only on the second question!\n"+"You've lost 50,000$ and you have a disqualification");
						rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"The answer is:");
						rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+right_answers2);
						}
					}
					else		//mistake in the second//
					{ans[1]=(false);
					System.out.println("answer2: "+ans[1]);
						if(flagAnswer) //was right at first//
						{
							rightOrWrongLabel.setText("You were right only on the first question!\n"+"You've lost 25,000$");
							rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"The answer is:");
							rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+right_answers2);
						}
						else //both of them wrong//
						{
							rightOrWrongLabel.setText("You were Wrong on both questions!\n"+"You've lost 75,000$ and you have a disqualification");	
							rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"The answer is:");
							rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+right_answers2);
						}
					}
					
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

					 /*end turn
					 GameController.getGCInstance().manageEndTurn(player);*/
					//System.out.println("from show: "+ans[0]+"  "+ans[1]);
					GameController.getGCInstance().updateluck(ans, player, pointsPerQuestion);
					
					//System.out.println("second="+secondQues);
					//Wait few seconds and close automatically the question window
					Timeline timeline = new Timeline(new KeyFrame(
							Duration.millis(3500),
							ae -> {
								closeWindow();	        	
							}));
					timeline.play();
				}
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


	@Override
	public void setData(Object question) {
		if (question instanceof ArrayList<?>) {
			Object o1 = ((ArrayList) question).get(0);
			Object o2 = ((ArrayList) question).get(1);
			Object o3 = ((ArrayList) question).get(2);

			if (o1 instanceof Question)
				this.q1 = (Question) o1;
			if (o2 instanceof Question)
				this.q2=(Question) o2;
			if (o3 instanceof Player)
				this.player = (Player) o3;

		}

		init();
	}



}