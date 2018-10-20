package view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import controller.PlayerController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import util.Character;
import util.CharacterName;

public class PlayersDetailsView  implements Initializable, I_ViewCo  {
	

	@FXML
	ComboBox<CharacterName> comboP1;
	@FXML
	ComboBox<CharacterName> comboP2;
	@FXML
	ComboBox<CharacterName> comboP3;
	@FXML
	ComboBox<CharacterName> comboP4;
	@FXML
	Label labelP1;
	@FXML
	Label labelP2;
	@FXML
	Label labelP3;
	@FXML
	Label labelP4;
	@FXML
	JFXTextField NickNameP1;
	@FXML
	JFXTextField NickNameP2;
	@FXML
	JFXTextField NickNameP3;
	@FXML
	JFXTextField NickNameP4;
	@FXML
	Label check1;
	@FXML
	ImageView check1Img;
	@FXML
	Label check2;
	@FXML
	ImageView check2Img;
	@FXML
	Label check3;
	@FXML
	ImageView check3Img;
	@FXML
	Label check4;
	@FXML
	ImageView check4Img;
	@FXML
	ImageView img1;
	@FXML
	ImageView img2;
	@FXML
	ImageView img3;
	@FXML
	ImageView img4;
	@FXML
	JFXButton ok1;
	@FXML
	JFXButton ok2;
	@FXML
	JFXButton ok3;
	@FXML
	JFXButton ok4;
	@FXML
	JFXButton startGame;
	@FXML
	JFXButton back;
	
	private util.CharacterName[] cArr =util.CharacterName.values();
	private ObservableList<CharacterName> characterArray1= FXCollections.observableArrayList();
	private ObservableList<CharacterName> characterArray2= FXCollections.observableArrayList();
	private ObservableList<CharacterName> characterArray3= FXCollections.observableArrayList();
	private ObservableList<CharacterName> characterArray4= FXCollections.observableArrayList();


	private Integer numOfCharacters;
	//private ArrayList<Player> players = new ArrayList<Player>();
	private ArrayList<String> chosenNArray = new ArrayList<String>();
	private ArrayList<CharacterName> chosenCArray = new ArrayList<CharacterName>();
	private PlayerController pc;
	private CharacterName chosenC=null;
	private String chosenN=null;
	private MediaPlayer mediaPlayer;
	



	/**
	 * The method initialize the charcter arrays for every player
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		for (int i=0; i<cArr.length;i++) {
			characterArray1.add(cArr[i]);
			characterArray2.add(cArr[i]);
			characterArray3.add(cArr[i]);
			characterArray4.add(cArr[i]);
			
		}
		insertToCombo(comboP1,characterArray1);
	}

	/**
	 * The method insert to combo the relevant characters
	 */
	private void insertToCombo(ComboBox<CharacterName> combo, ObservableList<CharacterName> characterArray) {
	    combo.setItems(characterArray);	
	}

	/**
	 * The method shows details according to num of players chosen in the prev screen
	 */
	private void initC(CharacterName c, int num) {
		if (checkValidC(c)) {
		    initP(c, num);
		    chosenC=c;
		    if (num==1) {
		    	updateArray(c, characterArray1);
		    	NickNameP1.setDisable(false);
		    	ok1.setDisable(false);

		    }
		    else if (num==2) {
		    	updateArray(c, characterArray2);
		    	NickNameP2.setDisable(false);
		    	ok2.setDisable(false);

		    }
		    else if (num==3) {
		    	updateArray(c, characterArray3);
		    	NickNameP3.setDisable(false);
		    	ok3.setDisable(false);

		    }
		    else if (num==4) {
		    	NickNameP4.setDisable(false);
		    	ok4.setDisable(false);

		    }
		}
	}
	
	/**
	 * The method removes from arrays the characters that have been chosen before
	 */
	private void updateArray(CharacterName c, ObservableList<CharacterName> characterArray) {
		if (characterArray==characterArray1){
			characterArray2.remove(c);
			characterArray3.remove(c);
			characterArray4.remove(c);		
		}
		else if (characterArray==characterArray2){
			characterArray3.remove(c);
			characterArray4.remove(c);
		}
		else if (characterArray==characterArray3){
			characterArray4.remove(c);
		}
			
	}
				
	/**
	 * The method checks if a character was chosen 
	 */
	private boolean checkValidC(CharacterName c) {
		if (c==null) {
			return false;
		}
		return true;
	}

	/**
	 * The method shows details according to num of players and validation of nickNames
	 */
	private void initN(String s, int num) {
	    if ( checkValidN(s,num)) {
	    	updateYesCheck(num);
		    chosenN=s;
		    if (num==1) {
		    	check1.setDisable(false);
		    	comboP2.setDisable(false);
		    	insertToCombo(comboP2, characterArray2);
		    	comboP1.setDisable(true);
		    	NickNameP1.setDisable(true);
		    	ok1.setDisable(true);
		    }
		    else if (num==2) {
		    	check2.setDisable(false);
		    	comboP3.setDisable(false);
		    	insertToCombo(comboP3,characterArray3);
		    	comboP2.setDisable(true);
		    	NickNameP2.setDisable(true);
		    	ok2.setDisable(true);
		    }
		    else if (num==3) {
		    	check3.setDisable(false);
		    	comboP4.setDisable(false);
		    	insertToCombo(comboP4,characterArray4);
		    	comboP3.setDisable(true);
		    	NickNameP3.setDisable(true);
		    	ok3.setDisable(true);
		    }
		    else if (num==4) {
		    	check4.setDisable(false);
		    	comboP4.setDisable(true);
		    	NickNameP4.setDisable(true);
		    	ok4.setDisable(true);
		    }
		    chosenCArray.add(chosenC);
		    chosenNArray.add(chosenN);
	    }
	    else {
			updateNoCheck(num);
		    if (num==1)
		    	check1.setDisable(false);
		    else if (num==2)
		    	check2.setDisable(false);
		    else if (num==3)
		    	check3.setDisable(false);
		    else if (num==4)
		    	check4.setDisable(false);

	    }

	  
	}
	


	/**
	 * The method checks if nickName is valid - not null and not taken 
	 */
	private Boolean checkValidN(String s, int num) {
		if (s.equals("Please enter Nickname")||s.equals("")) {
			GeneralView.notification(" Please enter nickName " , "Error");
			return false;
		}
		for (int i=0; i<chosenNArray.size(); i++) {
			if (chosenNArray.get(i).equals(s)) {
				GeneralView.notification(" The nickName "+s+" is already taken" , "Error");
				return false;
			}		
		}
		return true;

	}


	private void updateYesCheck(int num) {
		File f = new File("images/YesCheck.png");	
	    Image img = new Image(f.toURI().toString());
	    if (num==1) 
	    	check1Img.setImage(img);
	    else if (num==2)
	    	check2Img.setImage(img);
	    else if (num==3)
	    	check3Img.setImage(img);
	    else if (num==4)
	    	check4Img.setImage(img);
	    
	    showStartGameButton(numOfCharacters, img);
	}
	

	/**
	 * The method shows "startGame" button if all details of all listed players are valid
	 */
	private void showStartGameButton(int num, Image img) {
		if((num==1 && check2Img.getImage()!=null && check2Img.getImage().equals(img))||
		(num==2 && check2Img.getImage()!=null && check2Img.getImage().equals(img))||
		(num==3 && check3Img.getImage()!=null && check3Img.getImage().equals(img))||
		(num==4 && check4Img.getImage()!=null && check4Img.getImage().equals(img))) {
			//GeneralView.notification(" "+num+" players have joined the game. Press on 'Start Game' button'" , " ");
			successSound();
			startGame.setVisible(true);
		}
	}

	private void updateNoCheck(int num) {
		File f = new File("images/NoCheck.png");	
	    Image img = new Image(f.toURI().toString());
	    if (num==1)
	    	check1Img.setImage(img);
	    else if (num==2)
	    	check2Img.setImage(img);
	    else if (num==3)
	    	check3Img.setImage(img);
	    else if (num==4)
	    	check4Img.setImage(img);

	}


	public void comboChanged(ActionEvent event) {
		if (event.getSource() == comboP1){
			initC(comboP1.getValue(),1);
		}
		if (event.getSource() == comboP2){
			initC(comboP2.getValue(),2);
		}
		if (event.getSource() == comboP3){
			initC(comboP3.getValue(),3);
		}
		if (event.getSource() == comboP4){
			initC(comboP4.getValue(),4);
		}

	}
	
	public void nickNameChanged(ActionEvent event) {
		if (event.getSource() == ok1){
			initN(NickNameP1.getText(),1);
		}
		if (event.getSource() == ok2){
			initN(NickNameP2.getText(),2);
		}
		if (event.getSource() == ok3){
			initN(NickNameP3.getText(),3);
		}
		if (event.getSource() == ok4){
			initN(NickNameP4.getText(),4);
		}

	}


	/**
	 * The method creates new playerController with all listed players details
	 */
	public void addtoArray() {	
		if (numOfCharacters==1||numOfCharacters==2)
			pc= new PlayerController(chosenNArray.get(0),new Character(chosenCArray.get(0)),chosenNArray.get(1),new Character(chosenCArray.get(1)));
		else if (numOfCharacters==3)
			pc= new PlayerController(chosenNArray.get(0),new Character(chosenCArray.get(0)),chosenNArray.get(1),new Character(chosenCArray.get(1)),chosenNArray.get(2),new Character(chosenCArray.get(2)));
		else {
			pc= new PlayerController(chosenNArray.get(0),new Character(chosenCArray.get(0)),chosenNArray.get(1),new Character(chosenCArray.get(1)),chosenNArray.get(2),new Character(chosenCArray.get(2)),
					chosenNArray.get(3),new Character(chosenCArray.get(3)));
		}
		openGameBoard();

	}
	
	public void successSound() {
	try {
			String bip = "images/successSound.mp3";
			Media hit = new Media(new File(bip).toURI().toString());
			 mediaPlayer = new MediaPlayer(hit);
			mediaPlayer.play();
	} 	catch (Exception e) {
		e.printStackTrace();
	}
}

	/**
	 * The method opens the game board 
	 */
	private void openGameBoard() {
			try {
				GeneralView.openPage(startGame, "Game.fxml", this ,new GameView(),numOfCharacters);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}


	private void initP(CharacterName c1, int num) {
		Character c=new Character(c1);
		
	     Image img= c.getImg();
	     if (num==1)
	    	 img1.setImage(img);
	     else if (num==2)
	    	 img2.setImage(img);
	     else if (num==3)
	    	 img3.setImage(img);
	     else if (num==4)
	    	 img4.setImage(img);
	}



	/**
	 * The method initailize the opening buttons according to num of players
	 */
	public void initB() {
	
		if (numOfCharacters>=3) {
			comboP3.setVisible(true);
			labelP3.setVisible(true);
			NickNameP3.setVisible(true);
			check3.setVisible(true);
			ok3.setVisible(true);

		}
		 if (numOfCharacters==4) {
			comboP4.setVisible(true);
			labelP4.setVisible(true);
			NickNameP4.setVisible(true);
			check4.setVisible(true);
			ok4.setVisible(true);

		}
		
	}
	public void back() {
		try {
			GeneralView.openPage(back,"choosePlayersNum.fxml", this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * The method sets the number of players from prev screen 
	 */
	@Override
	public void setData(Object obj) {
		if (obj instanceof Integer) {
			numOfCharacters = (Integer) obj;
			initB();
		}
	}
	

}
