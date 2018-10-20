package view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import controller.AdminController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Admin;
import model.Answer;
import model.Question;
import util.Topic;

public class AddQuestView implements Initializable {
	@FXML
	private JFXTextArea text;
	@FXML
	private JFXTextField ans1;
	@FXML
	private JFXTextField ans2;
	@FXML
	private JFXTextField ans3;
	@FXML
	private JFXTextField ans4;
	@FXML
	private JFXRadioButton easy;
	@FXML
	private JFXRadioButton middle;
	@FXML
	private JFXRadioButton hard;
	@FXML
	private JFXButton save;
	@FXML
	private JFXButton back;
	@FXML
	private ImageView check1;
	@FXML
	private ImageView check2;
	@FXML
	private ImageView check3;
	@FXML
	private ImageView check4;
	@FXML
	private JFXRadioButton Requirements;
	@FXML
	private JFXRadioButton TDD;
	@FXML
	private JFXRadioButton Agile;
	@FXML
	private JFXRadioButton soacloud;
	@FXML
	private JFXRadioButton risk;
	@FXML
	private JFXRadioButton designPat;
	@FXML
	private JFXRadioButton testings;
	@FXML
	private JFXRadioButton achitu;
	@FXML
	private JFXRadioButton main;
	@FXML
	private JFXRadioButton config;
	
	private boolean ch1=false;
	private boolean ch2=false;
	private boolean ch3=false;
	private boolean ch4=false;
	
	
	
	public void initialize(URL location, ResourceBundle resources) {
	
	  ToggleGroup group = new ToggleGroup();
	  easy.setToggleGroup(group);
	  easy.setSelected(true);
		 hard.setToggleGroup(group);
		 middle.setToggleGroup(group);
	
	}
	
	public void checkV(MouseEvent e){
		if (e.getSource()==check1){
			 ch1=true;
		    checkB(check1);
		}
		else if (e.getSource()==check2){
			ch2=true;
		    checkB(check2);
		}
		else if (e.getSource()==check3){
			ch3=true;
		    checkB(check3);
		}
		else {
			 ch4=true;
			checkB(check4);
		}
	}
		


	private void checkB(ImageView iv) {
		File f = new File("images/YesCheck.png");	
	    Image img = new Image(f.toURI().toString());
		if(iv.getImage()==null)
			iv.setImage(img);
		else
			iv.setImage(null);
					
				
	}
	public void savequest() throws IOException{
		int diffculty=diff();
		ArrayList<Topic> top=topic();
		ArrayList<Answer> ans=answer();
		 if(validation()){
			
		if(AdminController.getInstance().addQuestion( text.getText().toString(), multyQuest(), ans, top, "null",diffculty)){
			GeneralView.notification("Question was added", "add");
			GeneralView.openPage(save,"tablequestview.fxml", this);
			
		}	
		}
	}
	public int diff(){
		int dif=0;
		if(easy.isSelected()){
			return 0;
		}
		if(middle.isSelected()){
			return 1;
		}
		if(hard.isSelected()){
			return 2;
		}
		return dif;
	}
	
		public ArrayList<Topic> topic(){
			ArrayList<Topic> top= new ArrayList<>();
		if(Requirements.isSelected()){
			top.add(Topic.Requirements);
		}
		if(TDD.isSelected()){
			top.add(Topic.TDD);
		}
		if(Agile.isSelected()){
			top.add(Topic.Agile);
		}
		if(soacloud.isSelected()){
			top.add(Topic.SOAandCloud);
		}
		if(risk.isSelected()){
			top.add(Topic.CostsandRisks);
		}
		if(designPat.isSelected()){
			top.add(Topic.DesignPatterns);
		}
		if(testings.isSelected()){
			top.add(Topic.SoftwareTesting);
		}
		if(achitu.isSelected()){
			top.add(Topic.SoftwareArchitecture);
		}
		if(main.isSelected()){
			top.add(Topic.Maintenance);
		}
		if(config.isSelected()){
			top.add(Topic.ConfigurationManagement);
		}
		return top;
		}
		
		public ArrayList<Answer> answer(){
			ArrayList<Answer> answerArr= new ArrayList<>();
		Answer answer1= new Answer(ans1.getText(), ch1);
		Answer answer2= new Answer(ans2.getText(), ch2);
		answerArr.add(answer1);
		answerArr.add(answer2);
		if(!ans3.getText().trim().isEmpty()&&ans4.getText().trim().isEmpty()){
		Answer answer3= new Answer(ans3.getText(),ch3);
		answerArr.add(answer3);
		
		}
		if(!ans4.getText().trim().isEmpty()&&ans3.getText().trim().isEmpty()){
		Answer answer4= new Answer(ans4.getText(),ch4);
		answerArr.add(answer4);
		}
		
		return answerArr;
	
		}
		
		 public boolean validation(){
			 if(text.getText().trim().isEmpty()){
				 GeneralView.notification("Don't forget to write the question ", "add");
				 return false;
			 }
				if(ans1.getText().trim().isEmpty()&&ans2.getText().trim().isEmpty()&&ans3.getText().trim().isEmpty()&&ans4.getText().trim().isEmpty()){
					GeneralView.notification("Must write at list two answers", "add");
					return false;
			}
				if(!ans1.getText().trim().isEmpty()&&ans2.getText().trim().isEmpty()&&ans3.getText().trim().isEmpty()&&ans4.getText().trim().isEmpty()){
					GeneralView.notification("Must write at list two answer", "add");
					return false;
				}
				if(ans1.getText().trim().isEmpty()&&!ans2.getText().trim().isEmpty()&&ans3.getText().trim().isEmpty()&&ans4.getText().trim().isEmpty()){
					GeneralView.notification("Must write at list two answer", "add");
					return false;
				}
				if(ans1.getText().trim().isEmpty()&&ans2.getText().trim().isEmpty()&&!ans3.getText().trim().isEmpty()&&ans4.getText().trim().isEmpty()){
					GeneralView.notification("Must write at list two answer", "add");
					return false;
				}
				if(ans1.getText().trim().isEmpty()&&ans2.getText().trim().isEmpty()&&ans3.getText().trim().isEmpty()&&!ans4.getText().trim().isEmpty()){
					GeneralView.notification("Must write at list two answer", "add");
					return false;
				}
				
			 if (!ch1&!ch2&!ch3&!ch4){
				 GeneralView.notification("Must one answer should be the right answer", "add");
				 return false;
			 }
			 if(!Requirements.isSelected()&&!TDD.isSelected()&&!Agile.isSelected()&&!soacloud.isSelected()
					 &&!risk.isSelected()&&!designPat.isSelected()&&!testings.isSelected()&&!achitu.isSelected()
					 &&!main.isSelected()&&!config.isSelected()){
				 GeneralView.notification("Must choose at list one topic", "add");
				 return false;
			 }
			 else{
				 return true;
			 }
		 }
			public boolean multyQuest(){
				if(ch1&!ch2&!ch3&!ch4){
					return false;
				}
				if(!ch1&ch2&!ch3&!ch4){
					return false;
				}
				if(!ch1&!ch2&ch3&!ch4){
					return false;
				}
				if(!ch1&!ch2&!ch3&ch4){
					return false;
				}
				else{
					return true;
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
			GeneralView.openPage(back,"tablequestview.fxml", this);
		}
		else{
			return;
		}

	}
}

