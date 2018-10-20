package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import model.Player;
import util.Character;

/**
 * QuestionController
 * makes the connection between view to player(model)
 */
public class PlayerController {

//	private static PlayerController instance;
	private  ArrayList<Player> playerArrayList;

	/**
	 * Constructor-1 player
	 * @return
	 */
	/*private PlayerController(String gamerNickName,Character character){
		Player p1=new Player( gamerNickName, character);
		playerArrayList=new ArrayList<Player>();
		
		GameController game=new GameController();
		game.setActivPlayers(playerArrayList);

	}
*/
	/**
	 * Constructor-2 players
	 * @return
	 */
	public PlayerController(String gamerNickName,Character character,String gamerNickName2,Character character2){
		Player p1=new Player( gamerNickName, character);
		Player p2=new Player( gamerNickName2, character2);
		playerArrayList=new ArrayList<Player>();
		
		random2Players( p1, p2);
		playerArrayList=inital2Players( p1, p2);
		GameController game=GameController.getGCInstance();
		ArrayList<Player> a=(ArrayList<Player>) playerArrayList.clone();
		game.setActivPlayers(a);
		game.setAllPlayers(a);
	
	}
	/**
	 * Constructor-3 players
	 * @return
	 */
	public PlayerController(String gamerNickName,Character character,String gamerNickName2,Character character2,
			String gamerNickName3,Character character3){
		Player p1=new Player( gamerNickName, character);
		Player p2=new Player( gamerNickName2, character2);
		Player p3=new Player( gamerNickName3, character3);
		
		playerArrayList=new ArrayList<Player>();
		
		random3Players( p1, p2,p3);
		playerArrayList=inital3Players( p1, p2,p3);
		
		GameController game=GameController.getGCInstance();
		game.setActivPlayers(playerArrayList);
		ArrayList<Player> a=(ArrayList<Player>) playerArrayList.clone();
		game.setAllPlayers(a);
	
		
	}
	/**
	 * Constructor-4 players
	 * @return
	 */
	public PlayerController(String gamerNickName,Character character,String gamerNickName2,Character character2,
			String gamerNickName3,Character character3,String gamerNickName4,Character character4){

		Player p1=new Player( gamerNickName, character);
		Player p2=new Player( gamerNickName2, character2);
		Player p3=new Player( gamerNickName3, character3);
		Player p4 =new Player( gamerNickName4, character4);
		playerArrayList=new ArrayList<Player>();
		random4Players( p1, p2,p3,p4);
		playerArrayList=inital4Players( p1, p2,p3,p4);
		GameController game=GameController.getGCInstance();
		game.setActivPlayers(playerArrayList);
		ArrayList<Player> a=(ArrayList<Player>) playerArrayList.clone();
		game.setAllPlayers(a);
		
	}
	/**
	 * Singleton
	 * @return
	 */
	/*public static PlayerController getInstance(){	
		if(instance==null){
			instance=new PlayerController("player1",Character.Simba,"player2",Character.Rafiki);
			return instance;
		}
		else
			return instance;
	}
*/
	/**
	 * sets the player's turn for 4 players
	 * */
	private void random4Players(Player p1,Player p2,Player p3,Player p4)
	{
		int place;

		Random rand=new Random();
		place=rand.nextInt(4)+1;
		p1.setPlayerTurnPlace(place);
		place=rand.nextInt(4)+1;
		while(place==p1.getPlayerTurnPlace())
		{
			place=rand.nextInt(4)+1;
		}
		p2.setPlayerTurnPlace(place);

		place=rand.nextInt(4)+1;
		while(place==p1.getPlayerTurnPlace() || place==p2.getPlayerTurnPlace())
		{
			place=rand.nextInt(4)+1;
		}
		p3.setPlayerTurnPlace(place);

		for(int i=1;i<5;i++)
		{
			if(i!=p1.getPlayerTurnPlace() && i!=p2.getPlayerTurnPlace() && i!=p3.getPlayerTurnPlace())

			{
				p4.setPlayerTurnPlace(i);
				break;
			}

		}
	}

	/**
	 * sets the player's turn for 4 players
	 * */
	private void random3Players(Player p1,Player p2,Player p3)
	{
		int place;

		Random rand=new Random();
		place=rand.nextInt(3)+1;
		p1.setPlayerTurnPlace(place);
		place=rand.nextInt(3)+1;
		while(place==p1.getPlayerTurnPlace())
		{
			place=rand.nextInt(3)+1;
		}
		p2.setPlayerTurnPlace(place);

		place=rand.nextInt(3)+1;
		while(place==p1.getPlayerTurnPlace() || place==p2.getPlayerTurnPlace())
		{
			place=rand.nextInt(3)+1;
		}
		p3.setPlayerTurnPlace(place);

	}

	/**
	 * sets the player's turn for 4 players
	 * */
	private void random2Players(Player p1,Player p2)
	{
		int place;

		Random rand=new Random();
		place=rand.nextInt(2)+1;
		p1.setPlayerTurnPlace(place);
		place=rand.nextInt(2)+1;
		while(place==p1.getPlayerTurnPlace())
		{
			place=rand.nextInt(2)+1;
		}
		p2.setPlayerTurnPlace(place);

	}

	/**
	 * initial 2 players in array by ASC order
	 * */
	private ArrayList<Player> inital2Players(Player p1,Player p2)
	{
		ArrayList<Player> playerList=new ArrayList<Player>();
		if(p1.getPlayerTurnPlace()<p2.getPlayerTurnPlace())
		{
			playerList.add(p1);
			playerList.add(p2);
		}
		else
		{
			playerList.add(p2);
			playerList.add(p1);
		}
		return playerList;

	}

	/**
	 * initial 3 players in array by ASC order
	 * */
	private ArrayList<Player> inital3Players(Player p1,Player p2,Player p3)
	{
		ArrayList<Player> playerList=new ArrayList<Player>();
		for(int i=1;i<4;i++)
		{
			if(i==p1.getPlayerTurnPlace())
				playerList.add(p1);
			
			if(i==p2.getPlayerTurnPlace())
				playerList.add(p2);
			
			if(i==p3.getPlayerTurnPlace())
				playerList.add(p3);

		}

		return playerList;
	}


	/**
	 * initial 4 players in array by ASC order
	 * */
	private ArrayList<Player> inital4Players(Player p1,Player p2,Player p3,Player p4)
	{
		ArrayList<Player> playerList=new ArrayList<Player>();
		for(int i=1;i<5;i++)
		{
			if(i==p1.getPlayerTurnPlace())
				playerList.add(p1);
			
			if(i==p2.getPlayerTurnPlace())
				playerList.add(p2);
			
			if(i==p3.getPlayerTurnPlace())
				playerList.add(p3);
			
			if(i==p4.getPlayerTurnPlace())
				playerList.add(p4);
		}

		return playerList;

	}
	

		/** The method initalPlayers to the array players**/
	/*	public void initalPlayers(){
			for(int i=0;i<4;i++)
			{
				Player p=new Player("Haim"+i,util.CharacterName);	
			}
		}*/
	

	//check for Ron//
	/*public static void main(String[] args) {

		
	PlayerController p=new PlayerController("Haim",Character.Pumbaa,"Mozes",Character.Mufasa);
	System.out.println(p.playerArrayList);
	
	PlayerController p2=new PlayerController("Haim",Character.Pumbaa,"Mozes",Character.Mufasa,"Ron",Character.Scar);
	System.out.println(p2.playerArrayList);
	
	PlayerController p3=new PlayerController("Haim",Character.Pumbaa,"Mozes",Character.Mufasa,"Ron",Character.Scar,"Shani",Character.Timon);
	System.out.println(p3.playerArrayList);
	System.out.println(GameController.getGCInstance().getActivPlayers());
	}*/

}
