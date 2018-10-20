package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;


import model.*;
import controller.QuestionController;
import javafx.scene.control.Alert;
import util.*;
import util.Square_type;
import view.GameView;
import view.GeneralView;
import view.ShowEndGameScores;
import view.ShowQuesEstateFromOwnerView;
import view.ShowQuesEstateNoOwnerView;
import view.ShowQuesLuckView;
import view.ShowQuesSquareView;
import view.ShowQuesTopicView;

public class GameController {

	private int numOfStep;
	private ArrayList<Player> allPlayers;
	private ArrayList<Player> activPlayers;
	private ArrayList<Player> nonActivPlayers;
	private ArrayList<Square> square;
	private int turnID; //current turn[1-4] represents the locate of the player in active list-Added by Ron//
	private Date gameDate;
	private static GameController gameControllerInstance;
	private ScoreboardController scoreController=  ScoreboardController.getInstance();
	private ShowEndGameScores seg ;
	private ArrayList<Player> p = new ArrayList<Player>(); // for calculatepoints

	private Question currrentQues;
	private boolean buyEstate;
	private Estate currrentEstate;
	private boolean flag=false;
	private  GameView gameView;


	/**
	 * partial constructors
	 * */
	private GameController() {
		super();
		this.numOfStep=0;
		this.turnID = 1;
		this.allPlayers=new ArrayList<Player>();
		this.activPlayers=new ArrayList<Player>();
		this.nonActivPlayers=new ArrayList<Player>();
		this.square=BoardController.getInstance().initializeSquares();
		this.gameDate= new Date();

	}

	public static GameController getGCInstance() {
		if (gameControllerInstance == null)
			gameControllerInstance = new GameController();
		return gameControllerInstance;
	}


	//////////////////////*get and set*////////////////////////

	public  GameView getGameView() {
		return gameView;
	}

	public  void setGameView(GameView gameV) {
		gameView = gameV;
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

	public Estate getCurrrentEstate() {
		return currrentEstate;
	}

	public void setCurrrentEstate(Estate currrentEstate) {
		this.currrentEstate = currrentEstate;
	}

	public boolean getIsBuyEstate() {
		return buyEstate;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public void setBuyEstate(boolean buyEstate) {
		this.buyEstate = buyEstate;
	}

	private ArrayList<Square> getSquares() {
		return square;
	}
	private void setSquares(ArrayList<Square> square) {
		this.square = square;
	}
	public int getNumOfStep() {
		return numOfStep;
	}
	private void setNumOfStep(int numOfStep) {
		this.numOfStep = numOfStep;
	}
	public ArrayList<Player> getActivPlayers() {
		return activPlayers;
	}
	public void setActivPlayers(ArrayList<Player> activPlayers) {
		this.activPlayers = activPlayers;
	}




	public ArrayList<Player> getAllPlayers() {
		return allPlayers;
	}

	public void setAllPlayers(ArrayList<Player> allPlayers) {
		this.allPlayers = allPlayers;
	}

	public ArrayList<Player> getNonActivPlayers() {
		return nonActivPlayers;
	}
	public void setNonActivPlayers(ArrayList<Player> nonActivPlayers) {
		this.nonActivPlayers = nonActivPlayers;
	}

	public int getTurnID() {
		return turnID;
	}
	private void setTurnID(int turnID) {
		this.turnID = turnID;
	}

	public Date getGameDate() {
		return gameDate;
	}

	///////////////////*Methods*//////////////////////


	/**
	 *  Returns the random number of two dices
	 *  **/
	public int rollDice(){
		int randomNum = new Random().nextInt(6) + 1;
		return (randomNum);

	}

	/**
	 * check if the player is bankruptcy- if yes return true, else return false
	 * @param player
	 * @return true if player is Bankruptcy
	 */
	public Boolean Bankruptcy(Player player){
		if(player!=null){
			/*player's budget is under -100,000*/
			if(player.getBudget()<=Constants.Bankruptcy){
				return true;
			}
			/* player's budget is 0 (or less) and he doesn't have estate*/ 
			if(player.getBudget()<=0){
				int numOFEstate=0;
				for(Estate e:SysData.getEstates()){
					if(e instanceof EstateWithOwner){
						EstateWithOwner e1= (EstateWithOwner)e;
						if(e1.getOwner().equals(player))
							numOFEstate++;
					}
				}
				if(numOFEstate==0)
					return true;
			}
			return false;
		}
		return null;
	}

	/**
	 * Checks if the game is over- Only one player left- if yes return true
	 * @return true if the game is over
	 */
	public Boolean endgame(){
		if(getActivPlayers().size()==1){
			return true;}
		return false;
	}




	private void openScores(ArrayList<Player> p) {
		try {
			ArrayList <Object> data=new ArrayList <Object>();
			data.add(gameView.getPageStage());
			data.add(p);
			GeneralView.openPageDecorated("endGameScores.fxml", new GeneralView(),seg,data );
		} catch (IOException e) {

			e.printStackTrace();
		}


	}

	/**
	 * player turn (After pressing the roll button)
	 * **/
	public void manageTurn(){

		Player thisPlayer=getCurrentPlayer();
		manageSquares(thisPlayer);

	}
	/**
	 * the method manage the end of player's turn 
	 * @param thisPlayer
	 */
	public void endTurn(Player thisPlayer){


		//If a player has 3 invalidation- he go to jail
		for(Player p:activPlayers)
		{
			if(p.getNumofinvalidation()>=Constants.Disq_for_jail)
				goToJailInvalidation(p);

			if(!p.equals(thisPlayer)){
				if(Bankruptcy(p)){
					getNonActivPlayers().add(p);
					getActivPlayers().remove(p);
				}
			}
		}

		numOfStep++;


		/*check if the this player is bankruptcy*/  
		if(Bankruptcy(thisPlayer)){
			getNonActivPlayers().add(thisPlayer);
			getActivPlayers().remove(thisPlayer);
		}
		else turnID++;

		if(turnID>activPlayers.size()){
			turnID=1;
		}


		//Only if the player is in jail
		while(getCurrentPlayer().isPrison()){
			getCurrentPlayer().setPrison(false);
			turnID++;	
			if(turnID>activPlayers.size()){
				turnID=1;	
			}
		}	

		if(endgame()||getNumOfStep()>=Constants.Moves_for_game) 
		{	
			ArrayList <Player> x=new ArrayList <Player>();
			turnID=0;
			x= CalculatePoints();
		
			try {
				if (scoreController.addPlayersAndScores(new Game (x, gameDate)))
					openScores(x);
			} catch (ParseException e) {
				e.printStackTrace();
			}


			/*
			GameController gc=new GameController();
			gc=gameControllerInstance;

			gameControllerInstance=null;*/
		}

		//	gameView.updatePlayerDetails();
		gameView.showRoll(getCurrentPlayer());

	}

	/**
	 * this method updates game controller
	 */
	public void updateGC(){

		try {
			SysData.getInstance().updateEstaeOwner();
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		gameControllerInstance=new GameController();

	}

	/**
	 * this method computes the total scores for the players
	 * @return ArrayList with all the players
	 */
	public ArrayList<Player> CalculatePoints(){
		ArrayList<Player> playersScore=new ArrayList<Player>();

		//for active players//
		for(Player p:activPlayers)
		{
			double total=p.getBudget();
			for(Estate e:SysData.getEstates())
			{
				if(e instanceof EstateWithOwner){
					EstateWithOwner e1= (EstateWithOwner)e;
					if(e1.getOwner().equals(p) )
					{
						total+=e.getvalue();
					}
				}
			}
			p.setAccuScore(total);
			Player player=new Player(p.getGamerNickName(),p.getCharacter(),p.getAccuScore());
			playersScore.add(player);
		}

		//for non-active players//
		for(Player p2:nonActivPlayers)
		{
			double total=p2.getBudget();

			p2.setAccuScore(total);
			Player player=new Player(p2.getGamerNickName(),p2.getCharacter(),p2.getAccuScore());
			playersScore.add(player);
		}
		Collections.sort(playersScore, new PlayerScoreComparator());
		return playersScore;
	}

	/**
	 * The method changes players position according to dice number
	 * @param dice
	 * @param p
	 */
	public void move(int dice,Player p){
		int currentPosition = p.getSquare().getSquareID();
		int newPosition;
		if (currentPosition==40)
			newPosition = dice;
		else
			newPosition = currentPosition+dice;
		Square s= square.get(newPosition-1);
		p.setSquare(s);
	}	


	/**
	 * The method Classifies the square and deals with it's content
	 * @param p
	 */
	public  void manageSquares(Player p){

		Square s=p.getSquare();


		if(s.getType().equals(Square_type.question)){
			manageQuestionSquareTopic(p);
			//manageQuestionSquare(p);

		}
		else	if(s.getType().equals(Square_type.lucky)){
			manageLuck(p);

		}
		else	if(s.getType().equals(Square_type.start)){
			manageStart(p);

		}
		else	if(s.getType().equals(Square_type.estate)){
			//update the value of the class
			setCurrrentEstate(getSquareEstate(p));
			manageEstate(p);

		}

		else	if(s.getType().equals(Square_type.goToJail)){
			//goToJail(p);
			manageJailAnswer(p);

		}
		else
		{	
			endTurn(p);
		}
	}

	/**
	 * The method returns the current player
	 * @return player
	 */
	public Player getCurrentPlayer(){
		if(turnID==0)
			return null;
		return activPlayers.get(turnID-1);

	}


	/**
	 * The method manages the process of question square
	 * @param p
	 * @param topic1
	 */
	public void manageQuestionSquare(Player p,Topic topic1){

		Question q1=QuestionController.getQCInstance().manageQ(topic1);


		ArrayList<Object> param = new ArrayList<>();
		param.add(q1);
		param.add(activPlayers.size());

		param.add(p);


		//all players after current
		for (int i=activPlayers.indexOf(p)+1; i<activPlayers.size();i++){
			param.add(activPlayers.get(i));
		}

		//all players before current
		for(int i=0;i<activPlayers.indexOf(p);i++)
		{
			param.add(activPlayers.get(i));
		}

		try {
			GeneralView.openPage("showQuesSquare.fxml",new GeneralView(),new ShowQuesSquareView() ,param);
		} catch (IOException e) {

			e.printStackTrace();
		}	
	}

	/**
	 * 
	 * @param p
	 * @param a
	 * @param answer
	 */
	public void updateQuestionSquare(HashMap<Player, Boolean> ansPlayers, Player p){


		Boolean currentPlayerWasRight=null;
		Boolean allWrong=true;
		Boolean allRight=true;

		//asking the current player//
		currentPlayerWasRight=ansPlayers.get(p);

		//asking the other players//
		for(Player p1:ansPlayers.keySet())
		{if(!p1.equals(p))
		{
			boolean b=ansPlayers.get(p1);
			if(b==true)
				allWrong=false;
			else
				allRight=false;
		}
		}

		//current player was right//
		if(currentPlayerWasRight)
		{

			for(Player pl:ansPlayers.keySet())
			{
				ansPlayers.get(pl);
			}

			if(allWrong)
			{
				//gets money and others get invalidation//
				p.setBudget(p.getBudget()+250000);

				for(Player p1:activPlayers )
				{
					if(!p1.equals(p))
					{
						int j=activPlayers.indexOf(p1); //index player//
						activPlayers.get(j).setNumofinvalidation(p1.getNumofinvalidation()+1);
					}
				}
			}
			else if(allRight)
			{
				//others get money//
				////	p.setBudget(p.getBudget()+250000);

				for(Player p1:activPlayers )
				{
					if(!p1.equals(p))
					{
						int k=activPlayers.indexOf(p1); //index player//
						activPlayers.get(k).setBudget(activPlayers.get(k).getBudget()+10000);
					}
				}
			}
			else
			{
				//gets money and others get invalidation//
				p.setBudget(p.getBudget()+50000);
				for(Player p1:activPlayers )
				{
					if(!p1.equals(p))
					{
						int k=activPlayers.indexOf(p1); //index player//
						if(ansPlayers.get(p1)==true)
						{	
							activPlayers.get(k).setBudget(activPlayers.get(k).getBudget()+75000);
						}
						else
						{
							activPlayers.get(k).setNumofinvalidation(activPlayers.get(k).getNumofinvalidation()+1);
						}
					}
				}
			}
		}

		//current player was wrong//
		else
		{
			//gets a fine+invalidation//
			p.setBudget(p.getBudget()-50000);
			p.setNumofinvalidation(p.getNumofinvalidation()+1);

			//asking the other players//
			for(Player pl:ansPlayers.keySet())
			{
				if(!pl.equals(p))
					//gets an invalidation in case he was wrong//
					if(ansPlayers.get(pl)==false)
					{
						int i=activPlayers.indexOf(pl); //index player//
						activPlayers.get(i).setNumofinvalidation(pl.getNumofinvalidation()+1);
					}
			}
		}

		endTurn(p);
		for(Player p1:activPlayers)
			gameView.updatePlayerDetails(p1,false);
	}


	/**
	 *  The method manages the process of question square
	 *  **/	
	private void manageQuestionSquareTopic(Player p){
		ArrayList<Object> param = new ArrayList<>();
		param.add(p);
		try {
			GeneralView.openPage("showQuesTopic.fxml",new GeneralView(),new ShowQuesTopicView() ,param);
		} catch (IOException e) {
			e.printStackTrace();
		}	

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("INFORMATION");
		alert.setHeaderText(null);
		alert.setContentText("All The players are going to asked a question. Please choose your topic");
		alert.show();
	}

	/**
	 *  The method manages the process of luck square
	 *  **/	
	private void manageLuck(Player p){

		Question ques1=QuestionController.getQCInstance().manageQ("HARD");
		Question ques2=QuestionController.getQCInstance().manageQ("AVERAGE");

		ArrayList<Object> param = new ArrayList<>();
		param.add(ques2);
		param.add(ques1);
		param.add(p);

		try {
			GeneralView.openPage("showQuesLuck.fxml",new GeneralView(),new ShowQuesLuckView() ,param);
		} catch (IOException e) {
			e.printStackTrace();
		}	

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("INFORMATION");
		alert.setHeaderText(null);
		alert.setContentText("You landed on a luck square. You are going to be asked 2 questions:1 on"
				+ " a medium level and 1 on a high level");
		alert.show();

	}

	/**
	 * the method update the player score after he answer the questions
	 * @param ans
	 * @param p
	 * @param fine
	 */
	public void updateluck(Boolean[] ans, Player p, int fine){


		boolean medAnswer=ans[0];
		boolean hardAnswer=ans[1];

		//if was right both of questions//
		if(hardAnswer==true && medAnswer==true)
		{
			p.setBudget(p.getBudget()+fine);
		}
		else
		{
			if(hardAnswer==false)  //if was wrong in the hard one-gets a fine//
			{
				p.setBudget(p.getBudget()-25000);
			}
			if(medAnswer==false)  //if was wrong in the medium one-gets a fine//
			{
				p.setBudget(p.getBudget()-50000);
				p.setNumofinvalidation(p.getNumofinvalidation()+1);
			}
		}

		endTurn(p);
		gameView.updatePlayerDetails(p,false);
	}
	/**
	 * random for luck square
	 * @return How much money for win- int
	 */
	public int randomForLuck(){
		Random rand=new Random();
		int fine=rand.nextInt(25000000)+10000;
		return fine;
	}


	/**
	 *  The method adding 200 NIS to the budget of a player who landed on the "Start" dice
	 *  
	 *  **/
	public void manageStart(Player p){

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("INFORMATION");
		alert.setHeaderText(null);
		alert.setContentText("You landed on a 'Start' square. You got 200 NIS!");
		alert.show();
		p.setBudget(p.getBudget()+200);

		endTurn(p);
		gameView.updatePlayerDetails(p,false);
	}

	/**
	 *  The method making the player skipping 1 turn (only sets the prison field to false) 
	 *  **/
	public void manageJail(Player p){
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("INFORMATION");
		alert.setHeaderText(null);
		alert.setContentText("You are in jail! Please wait 1 turn");
		alert.show();
	}

	/**
	 *  The method manages the process of entering/skipping jail
	 *  **/
	public void manageJailAnswer(Player p){

		ArrayList<Object> param = new ArrayList<>();
		param.add(p);

		try {
			GeneralView.openPage("showJailQuestion.fxml",new GeneralView(),new view.ShowJailQuestionView() ,param);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}

	/**
	 *  The method manages the process of getting to jail
	 *  **/
	private void goToJailInvalidation(Player p){

		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("INFORMATION");
		alert.setHeaderText(null);
		alert.setContentText(p.getGamerNickName()+" You are in jail! Please wait 1 turn");
		alert.show();

		//for(Player p1:activPlayers)
		p.setSquare(square.get(30));
		gameView.enteringJail(p);
		p.setPrison(true);
		p.setNumofinvalidation(0);
		gameView.updatePlayerDetails(p,false);


	}

	/**
	 *  The method manages the process of entering jail
	 *  **/
	public void manageSkipJail(Player p){

		p.setBudget(p.getBudget()-100000);

		endTurn(p);
		gameView.updatePlayerDetails(p,false);
	}

	/**
	 *  The method manages the process of skipping jail
	 *  **/
	public void manageEnterJail(Player p){

		if(p.getNumofinvalidation()==3)
			p.setNumofinvalidation(0);
		goToJail(p);

		endTurn(p);
	}

	/**
	 *  The method deals with "go to jail"'s square
	 * @param p
	 */
	public void goToJail(Player p){
		int s_id;
		Alert alert = new Alert(Alert.AlertType.WARNING);
		alert.setTitle("INFORMATION");
		alert.setHeaderText(null);
		alert.setContentText("Ups.. Sounds like you are going to jail");
		alert.show();

		//calling to the view
		gameView.enteringJail(p);

		s_id=p.getSquare().getSquareID();
		Square jailSquare=getSquares().get(s_id+19);	//jail location//
		p.setSquare(jailSquare);
		p.setPrison(true);


	}

	/**
	 * The method deals with "estate"'s square
	 * @param p
	 */
	public void manageEstate(Player p){
		Square s=p.getSquare();

		for(Estate e: SysData.getEstates())
		{
			if(e.getSquare().equals(s)){
				//estate is free//
				if(e instanceof EstateWithNoOwner)
					buyEstate(p);
				//a player owns the estate //
				else // if(e instanceof EstateWithOwner)
				{
					EstateWithOwner e2=(EstateWithOwner) e;
					if(activPlayers.contains(e2.getOwner()))
						dealWithOwner(p);
					else
					{
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setTitle("INFORMATION");
						alert.setHeaderText(null);
						alert.setContentText("The owner of the estate is non-active."+"\n"+" You can't buy/rent this estate!");
						alert.show();
						endTurn(p);
					}
				}
			}
		}
	}



	/**
	 * The method returns current "estate"
	 * @param p
	 * @return
	 */
	public Estate getSquareEstate(Player p){
		Square s=p.getSquare();
		for(Estate e: SysData.getEstates())
		{
			if(e.getSquare().equals(s))	
			{
				return e;
			}
		}
		return null;
	}


	/**
	 * The method Checks if the player is interested in buying a estate
	 * @param p
	 */
	public void buyEstate(Player p){
		Estate estate=getSquareEstate(p);
		String degree=manaeEatateDegree(estate);

		//player was right//
		Question q=QuestionController.getQCInstance().manageQ(degree);
		ArrayList<Object> param = new ArrayList<>();
		param.add(q);
		param.add(estate);
		param.add(p);


		try {
			GeneralView.openPage("showQuesEstateNoOwner.fxml",new GeneralView(),new ShowQuesEstateNoOwnerView() ,param);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * The method deals with process of estate with owner
	 * @param p
	 */
	public void dealWithOwner(Player p){

		EstateWithOwner estate=(EstateWithOwner)getSquareEstate(p);

		if(!p.equals(estate.getOwner())){
			ArrayList<Object> param = new ArrayList<>();
			param.add(estate);
			param.add(p);

			//showing the frame
			try {
				GeneralView.openPage("showQuesEstateFromOwner.fxml",new GeneralView(),new ShowQuesEstateFromOwnerView() ,param);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else{
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("INFORMATION");
			alert.setHeaderText(null);
			alert.setContentText(p.getGamerNickName()+" this is your estate");
			alert.show();

			endTurn(p);
		}
	}


	public void updateBuyEstate(Player p,boolean checkAnswer){

		EstateWithNoOwner estate= (EstateWithNoOwner) getSquareEstate(p);
		double pBudget=p.getBudget();
		String degree=manaeEatateDegree(estate);

		if(checkAnswer)
		{
			if(degree.equals("EXPENSIVE"))
			{
				p.setBudget(pBudget-estate.getcurrentPrice()*0.75);
				estate.withOwner(p);
				//updating estate's price//
				estate.setcurrentPrice(new Double(estate.getcurrentPrice()*0.75).longValue());
			}
			if(degree.equals("AVERAGE"))
			{
				p.setBudget(pBudget-estate.getcurrentPrice()*0.85);
				estate.withOwner(p);
				//updating estate's price//
				estate.setcurrentPrice(new Double(estate.getcurrentPrice()*0.85).longValue());
			}
			if(degree.equals("CHEAP"))
			{
				p.setBudget(pBudget-estate.getcurrentPrice()*0.95);
				estate.withOwner(p);
				//updating estate's price//
				estate.setcurrentPrice(new Double(estate.getcurrentPrice()*0.95).longValue());
			}
		}
		//player was wrong//
		else
		{
			//full price//
			p.setBudget(pBudget-estate.getcurrentPrice());
			estate.withOwner(p);
			//increasing number of invalidation//
			p.setNumofinvalidation(p.getNumofinvalidation()+1);
			estate.setcurrentPrice(new Double(estate.getcurrentPrice()).longValue());
		}

		endTurn(p);
		gameView.updatePlayerDetails(p,true);


	}


	/**
	 * The method deals with process of renting an estate
	 * @param estate
	 * @param p
	 */
	public void buyEstateFromOwner(Estate estate,Player p){

		EstateWithOwner estate2=(EstateWithOwner)estate;
		Player previousOwner=estate2.getOwner();
		double pOwnerBudget=previousOwner.getBudget();
		double pBudget=p.getBudget();
		double currentPrice=estate2.getcurrentPrice()*1.5;

		//finding the player in the player list//
		for(Player pl:GameController.getGCInstance().getActivPlayers())
		{
			if(pl.equals(previousOwner))
			{
				//updating the previous owner's budget//
				pl.setBudget(pOwnerBudget+currentPrice);
			}
		}

		//updating the new owner's budget//
		p.setBudget(pBudget-currentPrice);
		//updating estate's price//
		estate2.setcurrentPrice(new Double(currentPrice).longValue());
		//updating the new estate's owner//
		estate2.setOwner(p);

		endTurn(p);
		gameView.updatePlayerDetails(previousOwner,false);
		gameView.updatePlayerDetails(p,true);

	}



	/**
	 * The method deals with process of renting an estate
	 * @param estate
	 * @param p
	 */
	public void rentEstate(Estate estate,Player p){

		EstateWithOwner estate2=(EstateWithOwner)estate;
		Player previousOwner=estate2.getOwner();
		double pOwnerBudget=previousOwner.getBudget();
		double pBudget=p.getBudget();

		//finding the player in the player list//
		for(Player pl:GameController.getGCInstance().getActivPlayers())
		{
			if(pl.equals(previousOwner))
			{
				//updating the previous owner's budget//
				pl.setBudget(pOwnerBudget+estate2.getcurrentPrice()*0.15);
			}
		}

		//updating the new owner's budget//
		p.setBudget(pBudget-estate2.getcurrentPrice()*0.15);

		endTurn(p);
		gameView.updatePlayerDetails(previousOwner,false);
		gameView.updatePlayerDetails(p,false);

	}


	/**
	 * The method returns the estate's degree
	 * @param estate
	 * @return string
	 */
	public String manaeEatateDegree(Estate estate){

		if(estate.getcurrentPrice()>=2000000)
			return "EXPENSIVE";
		if(estate.getcurrentPrice()>=250000 && estate.getcurrentPrice()<2000000)
			return "AVERAGE";
		if(estate.getcurrentPrice()>=50000 && estate.getcurrentPrice()<250000){

			return "CHEAP";	}		
		return "CHEAP";	
	}


	public class PlayerScoreComparator implements Comparator<Player> {
		@Override
		public int compare(Player a, Player b) {
			return a.getAccuScore() < b.getAccuScore() ? 1 : a.getAccuScore() == b.getAccuScore() ? 0 : -1;
		}	    

	}



}
