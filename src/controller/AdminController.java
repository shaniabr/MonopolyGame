package controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import model.Answer;
import model.Question;
import model.SysData;
import util.Topic;

public class AdminController {

	private static AdminController instance;
	private SysData data;
	private String username;
	private String password;
	 private static int counter = 30;
	private int numOfQuest;

	private AdminController(String useName,String password){
		this.username=useName;
		this.password=password;
	}

	private AdminController() throws ParseException{
		//data=SysData.getInstance();

	}
	
	
	/**
	 * Singleton
	 * @return
	 * @throws ParseException 
	 */
	public static AdminController getInstance() {	
		if(instance==null){
			try {
				instance=new AdminController();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return instance;
		}
		else
			return instance;
	}
	/**
	 * Check the admin user name and password for logIn to the system
	 * @param useName
	 * @param password
	 * @return
	 */
	public boolean adminLogin(String useName,String password){
		String pass=SysData.getPassword();
		String userName=SysData.getUserName();
		if(useName.equals(userName) && password.equals(pass))
			return true;
		return false;	
	}
	
	public boolean changepass(String admin, String Oldpass,String newPass){
		String password=SysData.getPassword();
		String userName=SysData.getUserName();
		if(admin.equals(userName)){
			if(password.equals(Oldpass)){
				if(SysData.changePass(newPass)){
					return true;
				}
				else{
					return false;
				}
			}
			else{
			return false;
			
			}
		}
		else{
		return false;
		}
	}
	public HashMap<Question, Boolean> getQuestionInfo(){
		return data.getQuestionInfo();
				
		}
		
		public boolean addQuestion(String text,Boolean multyQuest,  ArrayList<Answer> ans, ArrayList<Topic> top, String team, int difficulty){
			numOfQuest=++counter;
			Question q= new Question(numOfQuest, text, multyQuest, ans, top, team, difficulty);
			return SysData.addQuestion(q);
					
			
		}
		public boolean  removeQuestion(Question q){
			return data.removeQuestion(q);
		
		}
		public Boolean editQuestion(Integer numOfQuestold,String textold,Boolean multyQuestold,  ArrayList<Answer> ansold, ArrayList<Topic> topold, String teamold, int difficultyold,Integer numOfQuestnew,String textnew,Boolean multyQuestnew,  ArrayList<Answer> ansnew, ArrayList<Topic> topnew, String teamnew, int difficultynew){
			Question old= new Question(numOfQuestold, textold, multyQuestold, ansold, topold, teamold, difficultyold);
			Question newq= new Question(numOfQuestnew, textnew, multyQuestnew, ansnew, topnew, teamnew, difficultynew);
			return SysData.editQuestion(old, newq);
		}
}
