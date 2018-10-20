package JunitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import model.Estate;
import model.EstateWithNoOwner;
import model.Player;
import model.Square;

import org.junit.Before;
import org.junit.Test;
import util.Character;
import util.Constants;
import controller.GameController;
import controller.PlayerController;
import util.Square_type;
public class TestsOnGame {

	private Player p1;
	private Player p2;
	private static GameController game=GameController.getGCInstance();
	
	 String message = "TestsOnGame";	
	   MessageUtil messageUtil = new MessageUtil(message);
	   
	 /**
     * Pre-Condition - Create a new Player - userName and Password to be tested.
     */
	@Before
	public  void setUpClass(){
	 p1= new Player("Haim",new Character(util.CharacterName.Pumbaa));
	 p2=new Player("Haim",new Character(util.CharacterName.Scar));
	 
		ArrayList<Player> activPlayers=new ArrayList<Player>();	

     activPlayers.add(p1);
     activPlayers.add(p2);
     
     game.setActivPlayers(activPlayers);
	}
	
	
	
	/**
	 * Test Case - roll 2 dices in game
	 * Expected Result : PlayerWasAddedException
	 * @throws DicesWereNoRolledException
	 * @throws DicesWereRolledException
	 */
	@Test 
	public void testRollingDices() {
		boolean result=false;
		int r=game.rollDice();
		if(r>=1 && r<=6)
			result =true;
		assertEquals("testing isRollingValid", true, result);
}
	/**
	 * Test Case - changing the llocation of a player
	 * Expected Result : PlayerWasMovedException
	 * @throws PlayerWasUnMovedException
	 * @throws PlayerWasMovedException
	 */
	@Test
	public void testPlayerMove(){
		//suppose to succeed
		
		boolean result=false;
		game.move(8, p1);
		if(p1.getSquare().getSquareID()==9)
			result=true;
		assertEquals("testing isMovingValid", true, result);
		}
	
	/**
	 * Test Case : Check if a player that has less then -100,000 nis goes bankruptcy
	 * Expected Result : true - the player went Bankruptcy
	 */
	@Test
	public void testBankruptcy(){
		//suppose to succeed
		p1.setBudget(-999999);
		boolean result = game.Bankruptcy(p1);
		assertEquals("testing Bankruptcy", true, result);
		}


	/**
	 * Test Case : Check if the game is ending
	 * Expected Result : false - there are 2 active players
	 */
	
	@Test
	public void testEndGame(){
		//suppose to un-succeed
		boolean result = game.endgame();
		assertEquals("testing EndGame", false, result);
		}
	
	/**
	 * Test Case : Check if the player is getting +200 NIS (his location:START)
	 * Expected Result : true - the player is in the start square
	 */
	/*
	@Test
	public void testManageStart1() throws ExceptionInInitializerError{
		//suppose to succeed
		boolean result = false;
		
		game.manageStart(p1);
		if(p1.getBudget()==Constants.Initial_amount+200)
			result = true;
		assertEquals("testing ManageStart", true, result);
		}

	/**
	 * Test Case : Check if the player is getting +200 NIS (his location:START)
	 * Expected Result : false - the player isn't  in the start square
	 */
	/*
	@Test
	public void testManageStart2(){
		//suppose to un-succeed
		boolean result = false;
		
		Square s=new Square(2, Square_type.estate);
		p2.setSquare(s);
		game.manageStart(p2);
		if(p2.getBudget()==Constants.Initial_amount+200)
			result=true;
		assertEquals("testing ManageStart", false, result);
		}

	/**
	 * Test Case : Check if the player sent to jail when he reached the limit of invalidation
	 * Expected Result : true - the player goes to jail
	 */
	/*
	@Test
	public void testManageTurn(){
		//suppose to succeed
		boolean result = false;
		p1.setNumofinvalidation(Constants.Disq_for_jail);
		game.manageTurn();
		if(p1.isPrison())
			result=true;
		assertEquals("testing ManageTurnNumOfV", false, result);
		}
	
	/**
	 * Test Case : Check if the player sent to jail when he reached the limit of invalidation
	 * Expected Result : true - the player goes to jail
	 */
	/*
	@Test
	public void testGetEstateSquare(){
		//suppose to succeed
		boolean result = false;
		Estate e=game.getSquareEstate(p1);
		if(e.equals(null))
			result=true;
		assertEquals("testing GetEstateSquare", true, result);
		}
	
	/**
	 * Test Case : Check if we put an estate with an AVERAGE price, we also get it's degree 
	 * Expected Result : true - AVERAGE degree
	 */
	@Test
	public void testEstateDegreeAverage(){
		//suppose to succeed
		boolean result = false;
		Square s=new Square(2, Square_type.estate);
		EstateWithNoOwner e=new EstateWithNoOwner("111", "Ron", 1000000, s,  1000000);
		
		if(game.manaeEatateDegree(e).equals("AVERAGE"))
			result=true;
		assertEquals("testing testEstateDegree", true, result);
		}
	
	/**
	 * Test Case : Check if we put an estate with an CHEAP price, we also get it's degree 
	 * Expected Result : false - AVERAGE degree
	 */
	@Test
	public void testEstateDegreeCheap(){
		//suppose to succeed
		boolean result = false;
		Square s=new Square(2, Square_type.estate);
		EstateWithNoOwner e=new EstateWithNoOwner("111", "Ron", 50000, s, 50000);
		
		if(game.manaeEatateDegree(e).equals("AVERAGE"))
			result=true;
		assertEquals("testing testEstateDegree", false, result);
		}
	
	/**
	 * Test Case : Check if we put an estate with an EXPENSIVE price, we also get it's degree 
	 * Expected Result : true - EXPENSIVE degree
	 */
	@Test
	public void testEstateDegreeExpansive(){
		//suppose to succeed
		boolean result = false;
		Square s=new Square(2, Square_type.estate);
		EstateWithNoOwner e=new EstateWithNoOwner("111", "Ron", 50000000, s, 50000000);
		
		if(game.manaeEatateDegree(e).equals("EXPENSIVE"))
			result=true;
		assertEquals("testing testEstateDegree", true, result);
		}
	
	 @Test
	   public void testPrintMessage() {	
	      System.out.println("Inside testPrintMessageForGame()");    
	      assertEquals(message, messageUtil.printMessage());     
	   }
}
