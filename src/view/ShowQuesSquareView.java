package view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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
import model.Game;
import model.Player;
import model.Question;

public class ShowQuesSquareView implements I_ViewCo {
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
	private boolean firstWasRight;
	private boolean secondWasRight;
	private boolean thirdWasRight;
	private boolean forthWasRight;
	private int count=0;
	private Question q1; //Medium Question//
	private static Player currentplayer;	//current player//
	private Player player1;	//current player//
	private Player player2;	// player 2 in turn//
	private Player player3;	// player 3 in turn//
	private Player player4;	// player 4 in turn//
	private controller.QuestionController control=controller.QuestionController.getQCInstance();
	private controller.GameController game=controller.GameController.getGCInstance();

	ArrayList<Integer> answers = new ArrayList<>();
	ArrayList<String> right_answers = new ArrayList<>();
	private Integer numOfPlayers;
	private HashMap<Player,Boolean> ans;
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
	
	private void changeColorOfPlayerBox(Player p){
		int turn = 0;
		for (int i=0; i<game.getAllPlayers().size();i++) {
			if (game.getAllPlayers().get(i).equals(p))
				turn=game.getAllPlayers().get(i).getPlayerTurnPlace();
		}
		if (turn==1)
			question.setStyle("-fx-background-color:#fa8072;");
		else if (turn==2)
			question.setStyle("-fx-background-color:#b0e0e6;");
		else if (turn==3)
			question.setStyle("-fx-background-color:#d8bfd8;");
		else
			question.setStyle("-fx-background-color:#ffdead;");
	}
	/**
	 * initiate all labels 
	 * @throws IOException
	 */
	public void init() {
		ans=new HashMap<Player,Boolean>();
		currentplayer=player1;
		question.setText(currentplayer.getGamerNickName()+": "+q1.getText());
		changeColorOfPlayerBox(currentplayer);
		question.setVisible(true);
		answer1.setText(q1.getAnswer().get(0).getText());
		answer2.setText(q1.getAnswer().get(1).getText());
		question.setVisible(true);

		finish.setVisible(true);

		answer1.setVisible(true);
		answer2.setVisible(true);

		if(q1.getAnswer().size()>2)
		{
			answer3.setText(q1.getAnswer().get(2).getText());
			answer4.setText(q1.getAnswer().get(3).getText());
			answer3.setVisible(true);
			answer4.setVisible(true);
			three.setVisible(true);
			four.setVisible(true);
		}

		else
		{
			answer3.setVisible(false);
			answer4.setVisible(false);
			three.setVisible(false);
			four.setVisible(false);
		}
		one.setVisible(true);
		two.setVisible(true);

		question.setVisible(true);
		//right_answers=new ArrayList<Answer>();
		
		//saving the right answers in aim to show it to the players//
		for(Answer a:q1.getAnswer())
		{
			if(a.getRightOrWrong())
			{
				right_answers.add(a.getText());
			}
		}

	}

	/**
	 * initiate all labels (again the same question-next player)
	 * @throws IOException
	 */
	public void resetDefult() {
		if(count==0)
			currentplayer=player1;

		else
		{
			if(count==1)
				currentplayer=player2;
			if(count==2)
				currentplayer=player3;
			if(count==3)
				currentplayer=player4;

		}
		
		ArrayList<Integer> answersEmpty = new ArrayList<>();
		answers=answersEmpty;
		
		one.setSelected(false);
		two.setSelected(false);
		three.setSelected(false);
		four.setSelected(false);
		
		question.setText(currentplayer.getGamerNickName()+": "+ q1.getText());
		changeColorOfPlayerBox(currentplayer);

		question.setVisible(true);
		answer1.setText(q1.getAnswer().get(0).getText());
		answer2.setText(q1.getAnswer().get(1).getText());
		question.setVisible(true);

		finish.setVisible(true);

		answer1.setVisible(true);
		answer2.setVisible(true);

		if(q1.getAnswer().size()>2)
		{
			answer3.setText(q1.getAnswer().get(2).getText());
			answer4.setText(q1.getAnswer().get(3).getText());
			answer3.setVisible(true);
			answer4.setVisible(true);
			three.setVisible(true);
			four.setVisible(true);
		}

		else
		{
			answer3.setVisible(false);
			answer4.setVisible(false);
			three.setVisible(false);
			four.setVisible(false);
		}
		one.setVisible(true);
		two.setVisible(true);
		
		question.setVisible(true);

	}

	/**
	 * Main function-manage the section
	 * @throws IOException
	 */
	public void action(ActionEvent e) throws IOException, InterruptedException {

		
		double pointsPerQuestion=currentplayer.getBudget();

		if (e.getSource() == finish){
			if(validateAnswer())
			{
				answers = answerIndex();
				if(control.checkIfAnswerIsRight(q1, answers,currentplayer))
				{
					ans.put(currentplayer, true);
					
					if(count==0)
						firstWasRight=true;
					if(count==1)
						secondWasRight=true;
					if(count==2)
						thirdWasRight=true;
					if(count==3)
						forthWasRight=true;

					//all players gave their answers
					if(count+1==numOfPlayers)
					{
						if(numOfPlayers==2)
						{
							if(firstWasRight && secondWasRight)
							{
								rightOrWrongLabel.setText("All players were right!");
							}
							if(!firstWasRight && secondWasRight)
							{
								rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"player 1-wrong, player2-right!");
							}
						}
						if(numOfPlayers==3)
						{
							
							
							if(!firstWasRight) //player1 wrong
							{
								if(!secondWasRight)
								{
									rightOrWrongLabel.setText("player2-wrong");
								}
								if(!thirdWasRight)
								{
									rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"player3-wrong");
								}
								rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"player1-wrong");
							}
							else //player1 right
							{
								if(firstWasRight && secondWasRight && thirdWasRight)
								{
									rightOrWrongLabel.setText("All players were right!");
								}
								
								if( !secondWasRight && !thirdWasRight) //others wrong
								{
									rightOrWrongLabel.setText("player1 right, others wrong");
								}
								
								if( secondWasRight && !thirdWasRight) //2 right , 3 wrong
								{
									rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"player1, player 2-right, player3 wrong");
								}
								
								if( !secondWasRight && thirdWasRight)	//2 wrong , 3 right
								{
									rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"player1, player3-right, player 2-wrong");
								}
							}
							
						}
						if(numOfPlayers==4)
						{
							if(!firstWasRight) //player1 wrong
							{
								if(!secondWasRight)
								{
									rightOrWrongLabel.setText("player2-wrong");
								}
								if(!thirdWasRight)
								{
									rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"player3-wrong");
								}
								if(!forthWasRight)
								{
									rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"player4-wrong");
								}
								rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"player1-wrong");
							}
							else //player1 right
							{
								if( secondWasRight && thirdWasRight && forthWasRight) //others wrong
								{
									rightOrWrongLabel.setText("All players were right");
								}
								
								if( !secondWasRight && !thirdWasRight && !forthWasRight) //others wrong
								{
									rightOrWrongLabel.setText("player1-right, others wrong");
								}
								
								//player1 right, 1 right 2 wrong
								
								if( secondWasRight && !thirdWasRight && !forthWasRight) //2 right , 3 wrong, 4 wrong
								{
									rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"player1, player2-right others wrong");
								}
								
								if( !secondWasRight && thirdWasRight && !forthWasRight) //2 wrong , 3 right, 4 wrong
								{
									rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"player1, player3-right others wrong");
								}
								
								
								if( !secondWasRight && !thirdWasRight && forthWasRight) //2 wrong , 3 wrong, 4 right
								{
									rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"player1, player4-right, player2, player3-wrong");
								}
								
								//player1-right, one wrong, two right
								/*
								if( secondWasRight && !thirdWasRight && !forthWasRight) //2 right , 3 wrong, 4 wrong
								{
									rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"player1, player2, player3-right, player4-wrong");
								}*/
								
								if( secondWasRight && !thirdWasRight && forthWasRight) //2 right , 3 wrong, 4 right
								{
									rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"player1, player2, player4-right, player3-wrong");
								}
								
					
								if( !secondWasRight && thirdWasRight && forthWasRight) //2 wrong , 3 right, 4 right
								{
									rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"player1, player3, player4-right, player2-wrong");
								}
							}
	
						}
						
						//rightOrWrongLabel.setText("You're Right!\n"+"Your score: "+pointsPerQuestion);
						rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"The answer is:");
						rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+right_answers);
						rightOrWrongLabel.setVisible(true) ;
						
					}
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


				}
				else		//mistake//
				{
					ans.put(currentplayer, false);
					if(count==0)
						firstWasRight=false;
					if(count==1)
						secondWasRight=false;
					if(count==2)
						thirdWasRight=false;
					if(count==3)
						forthWasRight=false;
					
					
					if(count+1==numOfPlayers)
					{
						if(numOfPlayers==2)
						{
							if(firstWasRight && !secondWasRight)
							{
								rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"player 1-right, player2-wrong");
							}
							if(!firstWasRight && !secondWasRight)
							{
								rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"player 1-wrong, player2-wrong");
							}
						}
						if(numOfPlayers==3)
						{
							
							if(!firstWasRight) //player1 wrong
							{
								if(!secondWasRight)
								{
									rightOrWrongLabel.setText("player2-wrong");
								}
								if(!thirdWasRight)
								{
									rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"player3-wrong");
								}
								rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"player1-wrong");
							}
							else //player1 right
							{
								if( !secondWasRight && !thirdWasRight) //others wrong
								{
									rightOrWrongLabel.setText("player1-right, others wrong");
								}
								
								if( secondWasRight && !thirdWasRight) //2 right , 3 wrong
								{
									rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"player1,player2-right, player3-wrong");
								}
								
								if( !secondWasRight && thirdWasRight)	//2 wrong , 3 right
								{
									rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"player1, player3-right, player2-wrong");
								}
							}
							
						}
						if(numOfPlayers==4)
						{
							if(!firstWasRight) //player1 wrong
							{
								if(!secondWasRight)
								{
									rightOrWrongLabel.setText("player2-wrong");
								}
								if(!thirdWasRight)
								{
									rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"player3-wrong");
								}
								if(!forthWasRight)
								{
									rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"player4-wrong");
								}
								rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"player1-wrong");
							}
							else //player1 right
							{
								if( !secondWasRight && !thirdWasRight && !forthWasRight) //others wrong
								{
									rightOrWrongLabel.setText("player1-right, others wrong");
								}
								
								//player1 right, 1 right 2 wrong
								
								if( secondWasRight && !thirdWasRight && !forthWasRight) //2 right , 3 wrong, 4 wrong
								{
									rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"player1, player2-right, player3, player4-wrong");
								}
								
								if( !secondWasRight && thirdWasRight && !forthWasRight) //2 wrong , 3 right, 4 wrong
								{
									rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"player1, player3-right, player2, player4-wrong");
								}
								
								
								if( !secondWasRight && !thirdWasRight && forthWasRight) //2 wrong , 3 wrong, 4 right
								{
									rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"player1, player4-right, player2, player3-wrong");
								}
								
								//player1-right, one wrong, two right
								/*
								if( secondWasRight && thirdWasRight && !forthWasRight) //2 right , 3 right, 4 wrong
								{
									rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"player1, player2, player3-right, player4 wrong");
								}*/
								
								if( secondWasRight && !thirdWasRight && forthWasRight) //2 right , 3 wrong, 4 right
								{
									rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"player1, player2, player4-right, player3 wrong");
								}
								
					
								if( !secondWasRight && thirdWasRight && forthWasRight) //2 wrong , 3 right, 4 right
								{
									rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"player1, player3, player4-right, player2-wrong");
								}
								if( secondWasRight && thirdWasRight && !forthWasRight) //2 right , 3 right, 4 wrong
								{
									rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"player1, player2, player3-right, player4-wrong");
								}
							}
	
						}

						//rightOrWrongLabel.setText("You're Right!\n"+"Your score: "+pointsPerQuestion);
						rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+"The answer is:");
						rightOrWrongLabel.setText(rightOrWrongLabel.getText()+"\n"+right_answers);
						rightOrWrongLabel.setVisible(true) ;
						System.out.println("adi "+right_answers);
					
					}
					
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
				}
				count++;
				if(count==numOfPlayers)
				{		
					
			GameController.getGCInstance().updateQuestionSquare(ans, player1);
					//Wait few seconds and close automatically the question window
					Timeline timeline = new Timeline(new KeyFrame(
							Duration.millis(3500),
							ae -> {
								closeWindow();	        	
							}));
					timeline.play();
				}
				else{
					resetDefult();
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
		Object o5=null;
		Object o6=null;
		if (question instanceof ArrayList<?>) {
			Object o1 = ((ArrayList) question).get(0);
			Object o2 = ((ArrayList) question).get(1);
			Object o3 = ((ArrayList) question).get(2);
			Object o4 = ((ArrayList) question).get(3);
			if(((ArrayList) question).size()>4)
			{
				 o5 = ((ArrayList) question).get(4);
				if(((ArrayList) question).size()>5)
				{
					 o6 = ((ArrayList) question).get(5);
				}
			}

			if (o1 instanceof Question)
				this.q1 = (Question) o1;
			if (o2 instanceof Integer)
				this.numOfPlayers = (Integer) o2;
			if (o3 instanceof Player)
				this.player1=(Player) o3;
			if (o4 instanceof Player)
				this.player2 = (Player) o4;
			if(numOfPlayers>2)
			{
				if (o5 instanceof Player)
					this.player3 = (Player) o5;
				if(numOfPlayers>3)
				{
					if (o6 instanceof Player)
						this.player4 = (Player) o6;
				}
			}

		}
		
		init();

	}

}