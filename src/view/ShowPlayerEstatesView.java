package view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import controller.EstatesController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import model.EstateWithOwner;
import model.Player;

public class ShowPlayerEstatesView implements Initializable, I_ViewCo {
	@FXML
	public JFXButton ok;
	@FXML
	public Label estatesOf= new Label() ;
	@FXML
	public TextArea assetsOfPlayer= new TextArea();

	private Player p;
	private ArrayList<String> estates = new ArrayList<String>();
	private EstatesController ec =EstatesController.getInstance();

	@Override
	public void initialize(URL location, ResourceBundle resources) {


	}

	public void setData(Object obj) {
		if (obj instanceof Player) {
			p = (Player) obj;
		}
		init(p);
	}

	private void init(Player p) {
		for (int i=0; i<ec.getEstates().size(); i++ ) {
			if (ec.getEstates().get(i) instanceof EstateWithOwner)
			{
				EstateWithOwner e1=(EstateWithOwner)ec.getEstates().get(i);
					if((e1.getOwner()).equals(p) ) {
						estates.add(ec.getEstates().get(i).getName());
					}
			}
		}
		
		estatesOf.setText("Assets of "+ p.getGamerNickName());
		assetsOfPlayer.setText(print());			
	}

	public void okButton() throws IOException{
		GeneralView.closeStage(ok);
	}

	public String print(){
		StringBuilder builder = new StringBuilder();
		for (String value : estates) {
			builder.append("               "+value+"\n");

		}
		String text = builder.toString();
		return text ;
	}

}
