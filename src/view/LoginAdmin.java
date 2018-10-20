package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import controller.AdminController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class LoginAdmin implements Initializable{
	@FXML
	private Button login;
	@FXML
	private JFXPasswordField password;
	@FXML
	private Button back;
	@FXML
	private JFXTextField userName;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	public void intologinpage() throws IOException{
		String usen = userName.getText();
		String pass=password.getText();
		if(AdminController.getInstance().adminLogin(usen, pass)){
			GeneralView.openPage(login,"adminMenu.fxml", this);	
		}
		else{
			GeneralView.notification("Incorrect Password or Username!","Warning");
		}
	}
	public void back() throws IOException{
		GeneralView.openPage(back,"mainPage.fxml", this);
	}
	
}
