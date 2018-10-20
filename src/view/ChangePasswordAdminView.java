package view;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import controller.AdminController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;

public class ChangePasswordAdminView implements Initializable {
	@FXML
	private JFXPasswordField oldpassword;
	@FXML
	private JFXPasswordField password;
	@FXML
	private Button back;
	@FXML
	private Button save;
	@FXML
	private JFXTextField userName;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	public void save() throws IOException{
		if(userName.getText().trim().isEmpty()){
			GeneralView.notification("User name Field is empty", "save");
			
		}
		if(oldpassword.getText().trim().isEmpty()){
			GeneralView.notification("Old password Field is empty", "save");
			
		}
		
		if(password.getText().trim().isEmpty()){
			GeneralView.notification("New password Field is empty", "save");
	
		}
		else{
			String admin= userName.getText();
			String Oldpass=oldpassword.getText();
			String newPass=password.getText();
			if(AdminController.getInstance().changepass(admin, Oldpass, newPass)){
				GeneralView.notification("Password has changed", "save");
				GeneralView.openPage(save,"adminMenu.fxml", this);
			}
		}
	}
	public void back() throws IOException{
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Confirmation Dialog with Custom Actions");
		alert.setHeaderText("Are you sure you want to go back?");
		alert.setContentText("Choose your option.");

		javafx.scene.control.ButtonType buttonTypeOne = new javafx.scene.control.ButtonType("Yes,i'm sure");
		javafx.scene.control.ButtonType buttonTypeTwo = new javafx.scene.control.ButtonType("No!");
		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
		Optional<javafx.scene.control.ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne){
			GeneralView.openPage(back,"adminMenu.fxml", this);
		}
		else{
			return;
		}

	}
	

}
