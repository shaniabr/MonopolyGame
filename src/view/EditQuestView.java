package view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import controller.AdminController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import model.Answer;
import model.Question;
import util.Topic;

public class EditQuestView implements I_ViewCo,Initializable{
	
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
	
	private Question question;

	private boolean ch1=false;
	private boolean ch2=false;
	private boolean ch3=false;
	private boolean ch4=false;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		  ToggleGroup group = new ToggleGroup();
		  easy.setToggleGroup(group);
			 hard.setToggleGroup(group);
			 middle.setToggleGroup(group);
	}
	@Override
	public void setData(Object data){
		if(data instanceof Question){
			question=(Question)data;
			initB();
		}
	}
	public void initB(){
		text.setText(question.getText());
		allans();
		getdiff();
		getalltop();
		getcheckans();
	}
	
	public void getcheckans(){
		ArrayList<Answer> ans= question.getAnswer();
		if(ans.size()==2){
		if(ans.get(0).getRightOrWrong()){
			ch1=true;
			checkB(check1);
		}
		if(ans.get(1).getRightOrWrong()){
			ch2=true;
			checkB(check2);
		}
		}
		if(ans.size()==3){
			if(ans.get(0).getRightOrWrong()){
				ch1=true;
				checkB(check1);
			}
			if(ans.get(1).getRightOrWrong()){
				ch2=true;
				checkB(check2);
			}
		
		if(ans.get(2).getRightOrWrong()){
			ch3=true;
			checkB(check3);
		}
		}
		if(ans.size()==4){
			if(ans.get(0).getRightOrWrong()){
				ch1=true;
				checkB(check1);
			}
			if(ans.get(1).getRightOrWrong()){
				ch2=true;
				checkB(check2);
			}
		
		if(ans.get(2).getRightOrWrong()){
			ch3=true;
			checkB(check3);
		}

		if(ans.get(3).getRightOrWrong()){
			ch4=true;
			checkB(check4);
		}
		}
		
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


	public void allans(){
		ArrayList<Answer> ans=question.getAnswer();
		if(ans.size()==2){
			ans1.setText(ans.get(0).getText());
			ans2.setText(ans.get(1).getText());
		}
		if(ans.size()==3){
			ans1.setText(ans.get(0).getText());
			ans2.setText(ans.get(1).getText());
			ans3.setText(ans.get(2).getText());
		}
		if(ans.size()==4){
			ans1.setText(ans.get(0).getText());
			ans2.setText(ans.get(1).getText());
			ans3.setText(ans.get(2).getText());
			ans4.setText(ans.get(3).getText());
		}
	}
	public void getdiff(){
		if(question.getDifficulty()==2){
			hard.setSelected(true);
		}
		if(question.getDifficulty()==1){
			middle.setSelected(true);
		}
		if(question.getDifficulty()==0){
			easy.setSelected(true);
		}
	}
	public void getalltop(){
		ArrayList<Topic>top= question.getTopic();
		for(Topic t:top){
		if(t.equals(Topic.Requirements)){
			Requirements.setSelected(true);
		}
		if(t.equals(Topic.TDD)){
			TDD.setSelected(true);
		}
		if(t.equals(Topic.Agile)){
			Agile.setSelected(true);
		}
		if(t.equals(Topic.SOAandCloud)){
			soacloud.setSelected(true);
		}
		if(t.equals(Topic.CostsandRisks)){
			risk.setSelected(true);
		}
		if(t.equals(Topic.DesignPatterns)){
			designPat.setSelected(true);
		}
		if(t.equals(Topic.SoftwareTesting)){
			testings.setSelected(true);
		}
		if(t.equals(Topic.SoftwareArchitecture)){
			achitu.setSelected(true);
		}
		if(t.equals(Topic.Maintenance)){
			main.setSelected(true);
		}
		if(t.equals(Topic.ConfigurationManagement)){
			config.setSelected(true);
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
			GeneralView.openPage(back,"tablequestview.fxml", this);
		}
		else{
			return;
		}

	}
	public void edit() throws IOException{
		int diffculty=diff();
		ArrayList<Topic> top=topic();
		ArrayList<Answer> ans=answer();
		 if(validation()){
		if(AdminController.getInstance().editQuestion(question.getNumOfQuest(), question.getText(), question.getMultyQuest(), question.getAnswer(),question.getTopic(), question.getTeam(), question.getDifficulty(), question.getNumOfQuest(), text.getText(), multyQuest(), ans, top, "null", diffculty)){
			GeneralView.notification("You just edit a question!", "add");
			GeneralView.openPage(save,"tablequestview.fxml", this);
			
		}	
		}
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
		public int diff(){
			int dif=question.getDifficulty();
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
			ArrayList<Topic> top= question.getTopic();
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

}
