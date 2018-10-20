package view;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import com.jfoenix.controls.JFXButton;

import controller.EstatesController;
import controller.GameController;
import controller.ScoreboardController;
import model.Player;



/*
 * GAme view class
 * 
 */
public class GameView implements Initializable, I_ViewCo {
	
	@FXML
	private Group player1G;	
	@FXML
	private Group player2G;		
	@FXML
	private Group player3G;	
	@FXML
	private Group player4G;	
	@FXML
	private ImageView player1I;	
	@FXML
	private ImageView player2I;		
	@FXML
	private ImageView player3I;		
	@FXML
	private ImageView player4I;	
	@FXML
	private JFXButton roll1;
	@FXML
	private JFXButton roll2;
	@FXML
	private JFXButton roll3;
	@FXML
	private JFXButton roll4;
	@FXML
	private JFXButton assetsP1;
	@FXML
	private JFXButton assetsP2;
	@FXML
	private JFXButton assetsP3;
	@FXML
	private JFXButton assetsP4;
	@FXML
	private JFXButton backToMain;
	@FXML
	private Group groupP1;
	@FXML
	private Group groupP2;
	@FXML
	private Group groupP3;
	@FXML
	private Group groupP4;
	@FXML
	private ImageView soundOn;
	@FXML
	private ImageView soundOff;
	@FXML
	private ImageView imgP1;
	@FXML
	private ImageView imgP2;
	@FXML
	private ImageView imgP3;
	@FXML
	private ImageView imgP4;
	@FXML
	private ImageView dice1;
	@FXML
	private ImageView dice1s;
	@FXML
	private ImageView dice2;
	@FXML
	private ImageView dice2s;
	@FXML
	private ImageView dice3;
	@FXML
	private ImageView dice3s;
	@FXML
	private ImageView dice4;
	@FXML
	private ImageView dice4s;
	@FXML
	private ImageView dice5;
	@FXML
	private ImageView dice5s;
	@FXML
	private ImageView dice6;
	@FXML
	private ImageView dice6s;
	@FXML
	private Label label1;
	@FXML
	private Label label2;
	@FXML
	private Label label3;
	@FXML
	private Label label4;
	@FXML
	private Label nickP1;
	@FXML
	private Label nickP2;
	@FXML
	private Label nickP3;
	@FXML
	private Label nickP4;
	@FXML
	private Label disP1;
	@FXML
	private Label disP2;
	@FXML
	private Label disP3;
	@FXML
	private Label disP4;
	@FXML
	private Label moneyP1;
	@FXML
	private Label moneyP2;
	@FXML
	private Label moneyP3;
	@FXML
	private Label moneyP4;

	@FXML
	public ImageView asset2;
	@FXML
	public ImageView asset4;
	@FXML
	public ImageView asset5;
	@FXML
	public ImageView asset6;
	@FXML
	public ImageView asset7;
	@FXML
	public ImageView asset9;
	@FXML
	public ImageView asset10;
	@FXML
	public ImageView asset11;
	@FXML
	public ImageView asset12;
	@FXML
	public ImageView asset14;
	@FXML
	public ImageView asset15;
	@FXML
	public ImageView asset17;
	@FXML
	public ImageView asset19;
	@FXML
	public ImageView asset20;
	@FXML
	public ImageView asset22;
	@FXML
	public ImageView asset24;
	@FXML
	public ImageView asset25;
	@FXML
	public ImageView asset27;
	@FXML
	public ImageView asset28;
	@FXML
	public ImageView asset30;
	@FXML
	public ImageView asset32;
	@FXML
	public ImageView asset33;
	@FXML
	public ImageView asset35;
	@FXML
	public ImageView asset38;
	@FXML
	public ImageView asset40;
	
	
	    
	private controller.GameController game = GameController.getGCInstance();// get instance of control
	static Clip clipSound=null;//for game music	
	private Integer numOfCharacters;
	private ArrayList<Player> players ;
	private int position;
	private Group pTurn;// show the current Roll Button
	private MediaPlayer mediaPlayer;
	private Player p;// current player turn  
	private boolean jail=true;
	private ShowPlayerEstatesView spev;
	private ShowBackAlertView sbav;
	private Stage pageStage; // for closing the stage  when game is finished auto
	private boolean start=true;// if the game just started



	
	


	private static GameView gameInstance; //Added by Ron
	/**
	 * get instanse-added by Ron
	 * @return
	 */
	public static GameView getInstance() {
		if (gameInstance == null)
			gameInstance = new GameView();
		return gameInstance;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		game.setGameView(this); 
		
		players = game.getActivPlayers();// gets list of players in game

			
	}
	/**
	 * handle turn- when clicked on "roll" button
	 */
	
	public void handleTurn(MouseEvent e) {

			if (e.getSource()==roll1) {
				if (start)
					pageStage = (Stage) roll1.getScene().getWindow();
				roll1.setDisable(true);
				start=false;
			}
			else if (e.getSource()==roll2)
				roll2.setDisable(true);
			else if (e.getSource()==roll3)
				roll3.setDisable(true);
			else
				roll4.setDisable(true);
			
			Player p= game.getCurrentPlayer();
			rollDices(p);

	}
	
	public Stage getPageStage() {
		return pageStage;
	}
	
	/**
	 * initials the roll button according to turn
	 */
	
	private void initRollB() {
		roll1.setVisible(false);
		roll1.setDisable(false);
		label1.setStyle("-fx-background-color:white;");
		roll2.setVisible(false);
		roll2.setDisable(false);
		label2.setStyle("-fx-background-color:white;");
		roll3.setVisible(false);
		roll3.setDisable(false);
		label3.setStyle("-fx-background-color:white;");
		roll4.setVisible(false);
		roll4.setDisable(false);
		label4.setStyle("-fx-background-color:white;");



	}

	/**
	 * initials the board on opening
	 */
	private void init(ArrayList<Player> players) {
		dicesNumHide();
		initPlayers(players);
		soundOff.setVisible(false);
		soundOn.setVisible(true);
		gameSound();
	}
	
	
	/**
	 * initials the players according to num of players from prev screen
	 */
	private void initPlayers(ArrayList<Player> players) {
		nickP1.setText(players.get(0).getGamerNickName());
		nickP2.setText(players.get(1).getGamerNickName());
		imgP1.setImage(players.get(0).getCharacter().getImg());
		imgP2.setImage(players.get(1).getCharacter().getImg());
		player1I.setImage(players.get(0).getCharacter().getImg());
		player2I.setImage(players.get(1).getCharacter().getImg());
		
		if (numOfCharacters>=3) {
			groupP3.setVisible(true);
			player3G.setVisible(true);
			nickP3.setText(players.get(2).getGamerNickName());
			imgP3.setImage(players.get(2).getCharacter().getImg());
			player3I.setImage(players.get(2).getCharacter().getImg());

		}
		if (numOfCharacters==4) {
			groupP4.setVisible(true);
			player4G.setVisible(true);
			nickP4.setText(players.get(3).getGamerNickName());
			imgP4.setImage(players.get(3).getCharacter().getImg());
			player4I.setImage(players.get(3).getCharacter().getImg());


		}
	}


	/**
	 * hide dices image
	 */
	private void dicesNumHide() {
		dice1.setVisible(false);
		dice1s.setVisible(false);
		dice2.setVisible(false);
		dice2s.setVisible(false);
		dice3.setVisible(false);
		dice3s.setVisible(false);
		dice4.setVisible(false);
		dice4s.setVisible(false);
		dice5.setVisible(false);
		dice5s.setVisible(false);
		dice6.setVisible(false);
		dice6s.setVisible(false);		
	}

	/**
	 * The method moves the player according to his turn 
	 */
	private void move(Player p, int diceNum, Group pTurn) {
		position=p.getSquare().getSquareID();
		if (diceNum!=0) {
			
			if (position>=1&& position<=10) {
			    moveHorizontally(p,pTurn, -1, diceNum);
			 }
			if (position==11) {
				if (pTurn==player1G)
					moveVertically1(p,pTurn, -1, diceNum);
				else if (pTurn==player2G)
					moveVertically2(p,pTurn, -1, diceNum);
				else if (pTurn==player3G)
					moveVertically3(p,pTurn, -1, diceNum);
				else if (pTurn==player4G)
					moveVertically4(p,pTurn, -1, diceNum);
		    }
			
		    if (position>11&& position<20) {
		    	moveVertically(p,pTurn, -1, diceNum);
		    }
		    
		    if (position==20) {
			    moveToQPos(p,pTurn, diceNum);
			 }
		    if (position>=21&& position<31) {
		    	moveHorizontally(p,pTurn, 1, diceNum);
		    }
		    
		    if (position==31) {
		    	if (pTurn==player1G)
					moveVertically2(p,pTurn, 1, diceNum);
				else if (pTurn==player2G)
					moveVertically1(p,pTurn, 1, diceNum);
				else if (pTurn==player3G)
					moveVertically4(p,pTurn, 1, diceNum);
				else if (pTurn==player4G)
					moveVertically3(p,pTurn, 1, diceNum);
			 }
		    
		    if (position>31 && position<40) {
		    	moveVertically(p,pTurn, 1, diceNum);
		    }
		    if (position==40) {
			    moveToStartPos(p,pTurn, diceNum);
			 }
		    game.move(1, p);
		}
		    else if (jail)  {
		    		game.manageTurn();
		    		
		    	pTurn.setVisible(true);
		    	

		    		
		    }
	}
	


	/**
	 * The method deals with the process of getting into jail-added by Ron
	 */
	public void enteringJail(Player p) {
		Group pTurn= getPlayerTurn(p);
		moveToJail(pTurn);
	



	}

	


	/**
	 * The method moves the player pin to jail square
	 */
	private void moveToJail( Group pTurn) {
		pTurn.setVisible(false);
		TranslateTransition transition= new TranslateTransition();
		transition.setNode(pTurn);
		transition.setToY(-535);
		transition.setToX(0);
		transition.play();
		transition.setOnFinished(e -> {
			pTurn.setVisible(true);
		});
		
	}
	
	private void moveToQPos(Player p, Group pTurn, int steps) {
		TranslateTransition transition= new TranslateTransition();
		transition.setNode(pTurn);
		transition.setToY(-535);
		transition.setToX(-535);
		transition.play();
		transition.setOnFinished(e -> {
			if (steps==1) {
				//rollDices.setVisible(true);
				dicesNumHide();
			}
				move(p, steps-1,pTurn);
		});
		
	}
	private void moveToStartPos(Player p, Group pTurn, int steps) {
		TranslateTransition transition= new TranslateTransition();
		transition.setNode(pTurn);
		transition.setToY(0);
		transition.setToX(0);
		transition.play();
		transition.setOnFinished(e -> {
			if (steps==1) {
				//rollDices.setVisible(true);
				dicesNumHide();
			}
				move(p, steps-1,pTurn);
		});
		
	}
	/**
	 * The method moves the player vertically on board
	 */
	private void moveVertically(Player p,Group pTurn,int sign, int steps ) {
		TranslateTransition transition = new TranslateTransition();
		transition.setNode(pTurn);
		transition.setByY(sign *50);
		transition.play();
		transition.setOnFinished(e -> {
			if (steps==1) {
				//rollDices.setVisible(true);
				dicesNumHide();
			}
				move(p, steps-1,pTurn);
		});
	}
	
	private void moveVertically1(Player p,Group pTurn,int sign, int steps ) {
		TranslateTransition transition = new TranslateTransition();
		transition.setNode(pTurn);
		transition.setByY(sign *51);
		transition.play();
		transition.setOnFinished(e -> {
			if (steps==1) {
				//rollDices.setVisible(true);
				dicesNumHide();
			}
				move(p, steps-1,pTurn);
		});
	}
	
	private void moveVertically2(Player p,Group pTurn,int sign, int steps ) {
		TranslateTransition transition = new TranslateTransition();
		transition.setNode(pTurn);
		transition.setByY(sign *85);
		transition.play();
		transition.setOnFinished(e -> {
			if (steps==1) {
				//rollDices.setVisible(true);
				dicesNumHide();
			}
				move(p, steps-1,pTurn);
		});
	}
	
	private void moveVertically3(Player p,Group pTurn,int sign, int steps ) {
		TranslateTransition transition = new TranslateTransition();
		transition.setNode(pTurn);
		transition.setByY(sign *80);
		transition.play();
		transition.setOnFinished(e -> {
			if (steps==1) {
				//rollDices.setVisible(true);
				dicesNumHide();
			}
				move(p, steps-1,pTurn);
		});
	}
	
	private void moveVertically4(Player p,Group pTurn,int sign, int steps ) {
		TranslateTransition transition = new TranslateTransition();
		transition.setNode(pTurn);
		transition.setByY(sign *57);
		transition.play();
		transition.setOnFinished(e -> {
			if (steps==1) {
				//rollDices.setVisible(true);
				dicesNumHide();
			}
				move(p, steps-1,pTurn);
		});
	}
	
	
	
	/**
	 * The method moves the player horizontally on board
	 */
	private void moveHorizontally(Player p,Group pTurn,int sign, int steps ) {
		TranslateTransition transition = new TranslateTransition();
		transition.setNode(pTurn);
		transition.setByX(sign *54);
		transition.play();
		transition.setOnFinished(e -> {
			if (steps==1) {
				//rollDices.setVisible(true);
				dicesNumHide();
			}
				move(p, steps-1,pTurn);
		});
	}
	
	
	private void rollSound() {
		try {
			URL url = this.getClass().getClassLoader().getResource("view/images/FreeDiceRoll.wav");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
			Clip clipDice = AudioSystem.getClip();
			clipDice.open(audioIn);
			clipDice.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * The method get the dices result and calls "move" method 
	 */
	public void rollDices (Player p) {
		rollSound();
		int diceRoll1= game.rollDice();
		int diceRoll2= game.rollDice();
		int randomNum= diceRoll1+diceRoll2;

		pTurn= getPlayerTurn(p);
	
		move(p,randomNum,pTurn);
		dicesShow(diceRoll1,diceRoll2);
			
//		});
	}
	
	/**
	 * The method shows the relevant "roll" button according to  turn
	 */
	public void showRoll(Player p) {
		initRollB();
		if(p==null)
			System.out.println("Game End");
		else	if (p.getPlayerTurnPlace()==1) {
			label1.setStyle("-fx-background-color:#fa8072;");
			roll1.setVisible(true);
		}
		else if (p.getPlayerTurnPlace()==2) {
			label2.setStyle("-fx-background-color:#b0e0e6;");
			roll2.setVisible(true);

		}
		else if (p.getPlayerTurnPlace()==3) {
			label3.setStyle("-fx-background-color:#d8bfd8;");
			roll3.setVisible(true);
		}
		else {
			label4.setStyle("-fx-background-color: #ffdead;");
			roll4.setVisible(true);

		}		
	}

	/**
	 * The method returns the players pin according to his turn
	 */
	private Group getPlayerTurn(Player p) {		
		if ((p.getPlayerTurnPlace()==1)) {
			return player1G;	
		}
		else if ((p.getPlayerTurnPlace()==2)) {
			return player2G;

		}
		else if ((p.getPlayerTurnPlace()==3)) {
			return player3G;
		}
		else  {
			return player4G;
		}
		
	}
	
	/**
	 * The method shows the dice num according to result
	 */
	private void dicesShow(int diceRoll1, int diceRoll2) {
		if (diceRoll1==1)
			dice1.setVisible(true);
		if (diceRoll2==1)
			dice1s.setVisible(true);
		if (diceRoll1==2)
			dice2.setVisible(true);
		if (diceRoll2==2)
			dice2s.setVisible(true);
		if (diceRoll1==3)
			dice3.setVisible(true);
		if (diceRoll2==3)
			dice3s.setVisible(true);
		if (diceRoll1==4)
			dice4.setVisible(true);
		if (diceRoll2==4)
			dice4s.setVisible(true);
		if (diceRoll1==5)
			dice5.setVisible(true);
		if (diceRoll2==5)
			dice5s.setVisible(true);
		if (diceRoll1==6)
			dice6.setVisible(true);
		if (diceRoll2==6)
			dice6s.setVisible(true);
		// TODO Auto-generated method stub
		
	}

	public void gameSound() {
		try {
			URL url = this.getClass().getClassLoader().getResource("view/images/GameSound.mid");
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
		    clipSound = AudioSystem.getClip();
			clipSound.open(audioIn);
			clipSound.start();
			clipSound.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void soundOnButton() {
		muteSound();
		soundOn.setVisible(false);
		soundOff.setVisible(true);
		return;
	}

	public void muteSound() {
		clipSound.stop();
		
	}
	public void soundOffButton() {
			clipSound.start();
			clipSound.loop(Clip.LOOP_CONTINUOUSLY);
			soundOff.setVisible(false);
			soundOn.setVisible(true);
			return;
		
	}
	
	/**
	 * The method sets the num of players from prev screen
	 */
	public void setData(Object obj) {
		if (obj instanceof Integer) {
			numOfCharacters = (Integer) obj;
			init(players);
		}
	}
	
	public void back() throws IOException {
		Stage s = (Stage) backToMain.getScene().getWindow();
		GeneralView.openPageAlert( "YesNoAlert.fxml", this,sbav, s);

		}
	

	
	public void updatePlayerDetails(Player p, boolean buyAsset){
			updateMoney(p,p.getPlayerTurnPlace());
			updateDis(p,p.getPlayerTurnPlace());
			if (buyAsset)
				updateAssets(p,p.getPlayerTurnPlace());
		}


	private void updateDis(Player p, int turn) {
		String numDis= p.getNumofinvalidation().toString();
		if (turn==1)
			disP1.setText(numDis);
		else if (turn==2)
			disP2.setText(numDis);
		else if (turn==3)
			disP3.setText(numDis);
		else
			disP4.setText(numDis);
	}


	private void updateAssets(Player p, int turn) {
			updateHouseImg(turn,p.getSquare().getSquareID());

		
	}
	
	
	
	public void showAssetsOn(MouseEvent e) {
		int turn;
		
		if (e.getSource()== assetsP1) {
			turn=1;
		}
		else if (e.getSource()== assetsP2) {
			turn=2;
		}
		else if (e.getSource()== assetsP3) {
			turn=3;
		}
		else {
			turn=4;
			
		}
		
		for (int i=0; i<game.getAllPlayers().size();i++) {
			if (game.getAllPlayers().get(i).getPlayerTurnPlace().equals(turn)) {
				p=game.getAllPlayers().get(i);
			}
		}
		
		try {
			GeneralView.openPageDecorated("estatesOfPlayer.fxml", this, spev,p);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		


}
	

	private void updateHouseImg(int turn, int squareID) {
		File f;
	    if (turn==1) 
			 f = new File("images/redHouse.png");
	    else  if (turn==2) 
			 f = new File("images/blueHouse.png");
	    else if (turn==3) 
			 f = new File("images/purpleHouse.png");
	    else   
			 f = new File("images/orangeHouse.png");

	    Image img = new Image(f.toURI().toString());
	    if (squareID==2)
	    	asset2.setImage(img);
	    else if (squareID==4)
	    	asset4.setImage(img);
	    else if (squareID==5)
	    	asset5.setImage(img);
	    else if (squareID==6)
	    	asset6.setImage(img);
	    else if (squareID==7)
	    	asset7.setImage(img);
	    else if (squareID==9)
	    	asset9.setImage(img);
	    else if (squareID==10)
	    	asset10.setImage(img);
	    else if (squareID==12)
	    	asset12.setImage(img);
	    else if (squareID==14)
	    	asset14.setImage(img);
	    else if (squareID==15)
	    	asset15.setImage(img);
	    else if (squareID==17)
	    	asset17.setImage(img);
	    else if (squareID==19)
	    	asset19.setImage(img);
	    else if (squareID==20)
	    	asset20.setImage(img);
	    else if (squareID==22)
	    	asset22.setImage(img);
	    else if (squareID==24)
	    	asset24.setImage(img);
	    else if (squareID==25)
	    	asset25.setImage(img);
	    else if (squareID==27)
	    	asset27.setImage(img);
	    else if (squareID==28)
	    	asset28.setImage(img);
	    else if (squareID==30)
	    	asset30.setImage(img);
	    else if (squareID==32)
	    	asset32.setImage(img);
	    else if (squareID==33)
	    	asset33.setImage(img);
	    else if (squareID==35)
	    	asset35.setImage(img);
	    else if (squareID==38)
	    	asset38.setImage(img);
	    else 
	    	asset40.setImage(img);
	}


	private void updateMoney(Player p, int turn) {
		String money= String.valueOf(p.getBudget());
		if (turn==1)
			moneyP1.setText(money);
		else if (turn==2)
			moneyP2.setText(money);
		else if (turn==3)
			moneyP3.setText(money);
		else
			moneyP4.setText(money);	
		
		moneySound();
	}


	private void moneySound() {
	try {
		String bip = "src/view/images/moneyChange.mp3";
		Media hit = new Media(new File(bip).toURI().toString());
		mediaPlayer = new MediaPlayer(hit);
		mediaPlayer.play();
	} 	catch (Exception e) {
		e.printStackTrace();
	}		
	}	
	

}