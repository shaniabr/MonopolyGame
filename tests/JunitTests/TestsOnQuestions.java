package JunitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import controller.QuestionController;
import model.Question;
import util.Character;
import util.CharacterName;
import util.Topic;
import model.Answer;
import model.Player;

public class TestsOnQuestions {
	
	private static Question q;
	private static Question q3;
	private Player p1;
	private  Question q2;
	
	 String message = "TestsOnQuestions";	
	   MessageUtil messageUtil = new MessageUtil(message);
	   
	/**
	 * Create a New Question to be tested
	 * 4 of the optional answers
	 * a player
	 */
	@Before
	public  void setUpClass(){

		p1= new Player("Haim",new Character(CharacterName.Pumbaa));
		
		Answer a1=new Answer("yes",true);
		Answer a2=new Answer("no",false);
		Answer a3=new Answer("maybe",true);
		Answer a4=new Answer("There isn't such language",false);
		
		ArrayList<Answer> an=new ArrayList<Answer>();
		an.add(a1);
		an.add(a2);
		an.add(a3);
		an.add(a4);
		
		ArrayList<Topic> t=new ArrayList<Topic>();
		t.add(Topic.Agile);
		
		q=new Question(55,"JSON is language independent.",true,an,t,"AQUA",2);
		q3=new Question(56,"Hebrew is language independent.",false,an,t,"AQUA",2);
	}
	
	/**
	 * Test Case : checking if the method gives the right question's topic 
	 * Expect result : true- the right topic 
	 */
	/*	@Test
public void testTopicQuestion(){
		boolean result = false;
		
		
 		 q2 = QuestionController.getQCInstance().manageQ(Topic.Agile);
		
		if(q2.getTopic().equals(Topic.Agile))
			result = true;
		assertEquals("testing TopicQuestion", true, result);
		}
	
	/**
	 * Test Case : checking if the method gives the right question's Difficulty 
	 * Expect result : true- the right Difficulty
	 */
	/*@Test
	
public void testLevelQuestion(){
		boolean result = false;
 		
 		 q2 = QuestionController.getQCInstance().manageQ(2);
		
		if(q2.getDifficulty().equals(2))
			result = true;
		assertEquals("testing Level Question", true, result);
		}
	
*/
	/**
	 * Test Case : Checking multiply question-giving only 1 answer
	 * Expected Result : false - the player gave only 1 possible answers to a multiply question.
	 */
 	@Test
	public void testcheckMultiplyQuestionAnswerWrong(){
 		boolean result = false;
 		ArrayList<Integer> answerIndex=new ArrayList<Integer>();
 		answerIndex.add(1);
 		result=QuestionController.getQCInstance().checkAnswer(q,answerIndex ,p1);
		assertEquals("testing checkQuestionAnswer", false, result);
		}
 	
	/**
	 * Test Case : Checking multiply question-giving the right answers
	 * Expected Result : true - the player gave the 2 correct answers to a multiply question.
	 */
 	@Test
	public void testcheckMultiplyQuestionAnswerRight(){
 		boolean result = false;
 		ArrayList<Integer> answerIndex=new ArrayList<Integer>();
 		answerIndex.add(0);
 		answerIndex.add(2);
 		result=QuestionController.getQCInstance().checkAnswer(q,answerIndex ,p1);
		assertEquals("testing checkQuestionAnswer", true, result);
		}
 	
 	/**
	 * Test Case :  Checking non-multiply question-giving 2 answers
	 * Expected Result : false - the player gave the 2 correct answers to a non multiply question.
	 */
 	@Test
	public void testcheckNonMultiplyQuestionAnswerRight(){
 		boolean result = false;
 		ArrayList<Integer> answerIndex=new ArrayList<Integer>();
 		answerIndex.add(0);
 		answerIndex.add(2);
 		result=QuestionController.getQCInstance().checkAnswer(q3,answerIndex ,p1);
		assertEquals("testing checkQuestionAnswer2", false, result);
		}
 	
 	 @Test
	   public void testPrintMessage() {	
	      System.out.println("Inside testPrintMessageForQuestions()");    
	      assertEquals(message, messageUtil.printMessage());     
	   }
}
