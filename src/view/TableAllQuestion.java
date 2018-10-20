package view;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;

import controller.AdminController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Question;
import model.SysData;
import util.Topic;

public class TableAllQuestion implements Initializable {
	@FXML
	private Button add;
	@FXML
	private Button edit;
	@FXML
	private Button delet;
	@FXML
	private Button back;
	@FXML
	private TableView<Question> table;
	@FXML
	private TableColumn<Question,Integer > id;
	@FXML
	private TableColumn<Question,String> text;
	@FXML
	private TableColumn<Question,Integer> difficulty;
	@FXML
	private TableColumn<Question,ArrayList<Topic>> topic;
	HashMap<Question, Boolean> q;
	ArrayList<Topic> t= new ArrayList<>();
	
//	public ObservableList<Question> questData = FXCollections.observableArrayList(
//			new Question(1,"dsds",t.add(Topic.TDD), null, t, null, 1)
//			);


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		questtable();
	}
	public void questtable(){
	ObservableList<Question> questData = FXCollections.observableArrayList();
	 q=  AdminController.getInstance().getQuestionInfo();
	for(Question i:q.keySet()){
					questData.add(i);
				}
	id.setCellValueFactory(new PropertyValueFactory<Question,Integer >("numOfQuest"));
	text.setCellValueFactory(new PropertyValueFactory<Question,String >("text"));
	difficulty.setCellValueFactory(new PropertyValueFactory<Question,Integer >("difficulty"));
	topic.setCellValueFactory(new PropertyValueFactory<Question,ArrayList<Topic> >("topic"));
	table.setItems(questData);
	}
	@FXML
	public void gotoadd() throws IOException {
			GeneralView.openPage(add,"addquestionview.fxml", this);
		
	}
	public void deleteqesut() throws IOException {
		Question qesttion = table.getSelectionModel().getSelectedItem();
		if(qesttion!=null){
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Confirmation ");
			alert.setHeaderText("Are you sure you want to delete question?");
			alert.setContentText("Choose your option.");
			ButtonType buttonTypeOne = new ButtonType("Yes,i'm sure");
			ButtonType buttonTypeTwo = new ButtonType("No!");
			alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == buttonTypeOne){
			for(Question qest:q.keySet()){
				if(qesttion.getNumOfQuest()==qest.getNumOfQuest()){
					if(SysData.removeQuestion(qest)){
						GeneralView.notification("Question number: "+qesttion.getNumOfQuest()+" was deleted", "delet");
						questtable();
						return;
					}
					else{
						GeneralView.notification("Could't delete", "delet");
						return;
					}
				}
				}
				
			}
			else{
				return;
			}
		}
		else{
		GeneralView.notification("Could't delete.Please choose question", "Could't delete choose question");
		return;
	}
	
		
	
}
	public void back() throws IOException{
		GeneralView.openPage(back,"adminMenu.fxml", this);
	}

public void action(MouseEvent e) throws IOException {
	Question qesttion = table.getSelectionModel().getSelectedItem();
	if(qesttion!=null){
		if (e.getSource() == edit){
			GeneralView.openPage(edit, "editquestionview.fxml", this ,new EditQuestView(),qesttion);
		

		}
		
		
	}
else{
	GeneralView.notification("Please choose question to edit", "Please choose question to edit");
	return;
}
	}


}

