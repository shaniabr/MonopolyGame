package controller;

import java.util.ArrayList;
import java.util.HashMap;

import util.*;
import java.util.Random;

import javafx.scene.control.Alert;
import model.*;
/**
 * QuestionController
 * makes the connection between view to question(model)
 */
public class QuestionController {


	private static QuestionController questionControllerInstance;
	private Question currrentQues;


	private QuestionController() {}

	public static QuestionController getQCInstance() {
		if (questionControllerInstance == null)
			questionControllerInstance = new QuestionController();
		return questionControllerInstance;
	}
	
	/**
	 * getting current question 
	 **/
	public Question getCurrrentQues() {
		return currrentQues;
	}
	/**
	 * setting current question 
	 **/
	public void setCurrrentQues(Question currrentQues) {
		this.currrentQues = currrentQues;
	}
	
	
	/**
	 * Check the answers the player made and return right or wrong 
	 * @param question
	 * @param answerIndex
	 * @return true- if the answers rigth 
	 */
	public Boolean checkAnswer(Question question, ArrayList<Integer> answerIndex,Player p) {

		if (!question.getMultyQuest()) { // one answer
			if (answerIndex.size()!=1) {	
				return false;
			}

			if (question.getAnswer().get(answerIndex.get(0)).getRightOrWrong()) { // answer is right
				return true;
			}
			else {  
				// wrong answer	  				
				return false;
			}
		}
		//Multiple Question
		else// (question.getMultyQuest())
			if(answerIndex.size()>=2)
			{
				for(Answer a:question.getAnswer()){
					if(a.getRightOrWrong() && !(answerIndex.contains(question.getAnswer().indexOf(a)))){
						return false;}
					else if(!a.getRightOrWrong() && (answerIndex.contains(question.getAnswer().indexOf(a)))){
						return false;
					}
				}
				return true;
			}
		return false;
	}


	/**
	 * the method check if player's answer is right and update his score and invalidation
	 * @param question
	 * @param answerIndex
	 * @param player
	 * @return true- if the answers right 
	 */

	public Boolean checkIfAnswerIsRight(Question question, ArrayList<Integer> answerIndex, Player player) {

		Player p=null;
		for (Player p1:GameController.getGCInstance().getActivPlayers())
		{
			if(p1.equals(player))
				p=p1;
		}
		Boolean check= checkAnswer(question, answerIndex,  p);
		Square s=p.getSquare();


		//if(s.getType().equals(Square_type.estate)){
		//	GameController.getGCInstance().updateBuyEstate(p,check);
	//	}

		return check;
	}


	/**
	 * manage questions get object- int or topic. return question
	 * @param obj
	 * @return question for ask
	 */
	public Question manageQ(Object obj){

		if(obj!=null && !SysData.getQuestions().isEmpty()){
			ArrayList<Question> questions=new ArrayList<Question>();

			//	while(questions.isEmpty()){
			//All the questions with same difficulty like object// 
			if(obj.getClass().equals(String.class)){

				String i= (String)obj;
				Integer objDiff;

				//for search Difficulty	//			
				if (i.equals("HARD")|| i.equals("EXPENSIVE"))
					objDiff=2;
				else if((i.equals("AVERAGE")|| i.equals("MEDIUM")))
					objDiff=1;
				else // i is CHEAP
					objDiff=0;

				for(Question q:SysData.getQuestions().keySet()){
					if(SysData.getQuestions().get(q) && q.getDifficulty()==objDiff)
						questions.add(q);
				}
			}
			//All the questions with same Topic like object// 
			else if(obj.getClass().equals(Topic.class)){


				Topic t= (Topic)obj;
				for(Question q:SysData.getQuestions().keySet()){
					if(SysData.getQuestions().get(q) && q.getTopic().contains(t))
						questions.add(q);
				}	
			}

			Question questionForAsk;

			// if all the questions in same difficulty or topic were asked//
			if(questions.isEmpty()){

				SysData.returnsQuestions();
				questionForAsk=manageQ(obj);
			}
			//random question//
			else{

				int num=new Random().nextInt(questions.size());
				SysData.changeQuestionStatus(questions.get(num));
				questionForAsk= questions.get(num);
			}
			System.out.println("q="+questionForAsk.getText());
			System.out.println("answer: "+questionForAsk.getAnswer());
			return questionForAsk;
		}
		return null;
	}
	
	
}



