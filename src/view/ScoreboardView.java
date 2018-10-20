package view;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;


import controller.ScoreboardController;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import model.Game;
import model.Player;

public class ScoreboardView  implements Initializable {

	@FXML
	private ObservableList<Player> listP = FXCollections.observableArrayList();
	@FXML
	private TableView<Player> scoreTable;
	@FXML
	private Button back;
	@FXML
	private DatePicker gameDate;
	@FXML
	private TableColumn<Player, String> character;
	@FXML
	private TableColumn<Player, String> nickName;
	@FXML
	private TableColumn<Player, String> score;
	
	
	private ScoreboardController sc = ScoreboardController.getInstance();// get instance of control
	ArrayList<Player> arrP = new ArrayList<Player>();
	ArrayList<Game> arr = new ArrayList<Game>();
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	SimpleDateFormat sm = new SimpleDateFormat("dd-MM-yyyy");

	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
			try {
				arr.addAll(sc.getPlayersAndScores());
			} catch (ParseException e) {
				
				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		
	}
	public void dateChanged() {
	    LocalDate date = gameDate.getValue();
		scoreTable.getItems().clear();
		arrP.clear();


	    for (int i=0; i<arr.size();i++) {
	    	if (sm.format(arr.get(i).getDate()).equals(date.format(formatter))) {
	    		for (int j=0; j<arr.get(i).getPlayer().size();j++) {
	    			arrP.add(arr.get(i).getPlayer().get(j));
	    		}
			}
		}
		
		listP.addAll(arrP);
		character.setCellValueFactory(celldata-> new SimpleStringProperty(String.valueOf(celldata.getValue().getCharacter().getName())));
		nickName.setCellValueFactory(celldata-> new SimpleStringProperty(String.valueOf(celldata.getValue().getGamerNickName())));
		score.setCellValueFactory(celldata-> new SimpleStringProperty(String.valueOf(celldata.getValue().getAccuScore())));
		
		scoreTable.getSortOrder();
		scoreTable.setItems(listP);
	}
	
	public void back() {
		try {
			GeneralView.openPage(back,"mainPage.fxml", this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
	
	


