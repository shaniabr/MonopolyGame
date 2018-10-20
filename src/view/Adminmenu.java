package view;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;

public class Adminmenu implements Initializable {

	@FXML
	private Button manage;
	@FXML
	private Button chpas;
	@FXML
	private Button back;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	public void gotomanage() throws IOException{
		GeneralView.openPage(manage,"tablequestview.fxml", this);
	}
	public void gotopasschange() throws IOException{
		GeneralView.openPage(chpas,"adminChangePassView.fxml", this);
	}
	public void back() throws IOException{

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation ");
		alert.setHeaderText("Are you sure you want to go back?");
		alert.setContentText("Choose your option.");
		ButtonType buttonTypeOne = new ButtonType("Yes,i'm sure");
		ButtonType buttonTypeTwo = new ButtonType("No!");
		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne){
		GeneralView.openPage(back,"adminlogin.fxml", this);
		return;
		}
		else{
			return;
		}

	}
}
