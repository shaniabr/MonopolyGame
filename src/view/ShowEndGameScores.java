package view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import controller.GameController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import model.Estate;
import model.Player;
import model.Question;

public class ShowEndGameScores implements Initializable, I_ViewCo {

	@FXML
	Label scores;
	@FXML
	Label third;
	@FXML
	Label four;
	@FXML
	Label winnerT;
	@FXML
	Label twoT;
	@FXML
	Label thirdT;
	@FXML
	Label fourT;
	@FXML
	Label winnerScore;
	@FXML
	Label twoScore;
	@FXML
	Label thirdScore;
	@FXML
	Label fourScore;
	@FXML
	ImageView winnerI;
	@FXML
	ImageView twoI;
	@FXML
	ImageView thirdI;
	@FXML
	ImageView fourI;
	@FXML
	Button ok;

	private ArrayList<Player> players;
	private Stage s=null;
	private MediaPlayer mediaPlayer;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	
	private void applauseSound() {
		try {
			String bip = "images/applause.mp3";
			Media hit = new Media(new File(bip).toURI().toString());
			mediaPlayer = new MediaPlayer(hit);
			mediaPlayer.play();
		} 	catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * The method sets the players in game
	 */
	public void setData(Object obj) {

		if (obj instanceof ArrayList<?>) {
			if(((ArrayList) obj).size()==1)
			{
				Object o1 = ((ArrayList) obj).get(0);
				if (o1 instanceof ArrayList<?>)
					this.players=(ArrayList<Player>) o1;
			}
			else

			{
				Object o1 = ((ArrayList) obj).get(0);
				Object o2 = ((ArrayList) obj).get(1);

				if (o1 instanceof Stage)
					this.s = (Stage) o1;
				if (o2 instanceof ArrayList<?>)
					this.players=(ArrayList<Player>) o2;
				}
			}




		init();

	}

	private void init() {
		winnerT.setText(players.get(0).getGamerNickName());
		winnerScore.setText(String.valueOf(players.get(0).getAccuScore()));
		winnerI.setImage(players.get(0).getCharacter().getImg());
		twoT.setText(players.get(1).getGamerNickName());
		twoScore.setText(String.valueOf(players.get(1).getAccuScore()));
		twoI.setImage(players.get(1).getCharacter().getImg());

		if (players.size()>2) {
			third.setVisible(true);
			thirdT.setText(players.get(2).getGamerNickName());
			thirdScore.setText(String.valueOf(players.get(2).getAccuScore()));
			thirdI.setImage(players.get(2).getCharacter().getImg());

		}
		if (players.size()>3) {
			four.setVisible(true);
			fourT.setText(players.get(3).getGamerNickName());
			fourScore.setText(String.valueOf(players.get(3).getAccuScore()));
			fourI.setImage(players.get(3).getCharacter().getImg());
		}
		applauseSound();

	}
	
	public void okButton() throws IOException{
		GeneralView.closeStage(ok);
		GameView.clipSound.close();
		if(s!=null)
			s.close();
		GeneralView.openPageDecorated("mainPage.fxml", this);
		GameController.getGCInstance().updateGC();

	}



}
