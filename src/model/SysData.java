
package model;

import model.*;
import util.*;
import util.Character;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import org.json.simple.DeserializationException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JsonArray;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import controller.GameController;
import util.*;


public class SysData {

	private static HashMap <Question, Boolean> questions;
	private static ArrayList < Estate> estates;
	private static ArrayList<Game> scoreBoard;
	
	private static SysData instance;
	
	private static int gameNumber=1;
	
	private SysData() throws java.text.ParseException{
		this.estates=getallesate();
		this.questions=getQuestionInfo();
		this.scoreBoard= getScoreboard();
		
	}
	
	
	public static SysData getInstance() throws java.text.ParseException {
		if (instance == null)
			instance = new SysData();
		return instance;
	}

	public static ArrayList<Estate> getEstates() {
		return estates;
	}
	public static void setEstates(ArrayList<Estate> estates) {
		SysData.estates = estates;
	}

	public static HashMap<Question, Boolean> getQuestions() {
		return questions;

	}
	public static void setQuestions(HashMap<Question, Boolean> questions) {
		SysData.questions = questions;
	}


	public static void changeQuestionStatus(Question ques){
		boolean b=questions.put(ques, false);

	}

	/* get all questions from json*/
	public static HashMap<Question, Boolean> getQuestionInfo() {
		questions = new HashMap<>();
		ArrayList<Answer>ans; 
		ArrayList<Topic>top; 
		try (FileReader reader = new FileReader(new File("resourses/questions.json"))) {
			JsonObject root = null;
			try {
				root = (JsonObject) Jsoner.deserialize(reader);
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JsonArray questionsarr = (JsonArray) root.get("questions");
			Iterator<Object> iterator = questionsarr.iterator();
			while (iterator.hasNext()) {
				JsonObject question = (JsonObject) iterator.next();
				int id= ((Number)question.get("id")).intValue();
				String text= (String) question.get("text");
				boolean multy= (Boolean) question.get("isMultipleChoice");
				String team=(String) question.get("team");
				int difficulty= ((Number) question.get("difficulty")).intValue();
				JsonArray answers = (JsonArray) question.get("answers");
				Iterator<Object> iteratorans = answers.iterator();
				ans=new ArrayList<>();
				while (iteratorans.hasNext()){
					JsonObject answer = (JsonObject) iteratorans.next();
					ans.add(new Answer((String)answer.get("text"), (Boolean) answer.get("isCorrect")));
				}
				JsonArray tags = (JsonArray) question.get("tags");
				Iterator<Object> iteratortag = tags.iterator();
				top=new ArrayList<>();
				while (iteratortag.hasNext()){
					Object ta= iteratortag.next();
					String top1=ta.toString();
					if(top1.equals(Topic.Requirements.toString())){
						top.add(Topic.Requirements);
					}	        	
					if(top1.equals(Topic.Agile.toString())){
						top.add(Topic.Agile);
					}

					if(top1.equals(Topic.ConfigurationManagement.toString())){
						top.add(Topic.ConfigurationManagement);
					}

					if(top1.equals(Topic.CostsandRisks.toString())){
						top.add(Topic.CostsandRisks);
					}

					if(top1.equals(Topic.DesignPatterns.toString())){
						top.add(Topic.DesignPatterns);
					}

					if(top1.equals(Topic.Maintenance.toString())){
						top.add(Topic.Maintenance);
					}

					if(top1.equals(Topic.SOAandCloud.toString())){
						top.add(Topic.SOAandCloud);
					}
					if(top1.equals(Topic.SoftwareArchitecture.toString())){
						top.add(Topic.SoftwareArchitecture);
					}
					if(top1.equals(Topic.SoftwareTesting.toString())){
						top.add(Topic.SoftwareTesting);
					}
					if(top1.equals(Topic.TDD.toString())){
						top.add(Topic.TDD);
					}
				}
				Question quest= new Question(id, text, multy, ans, top, team, difficulty);
				questions.put(quest,true);
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questions;

	}
	/*Method that add question to json*/
	public static boolean addQuestion(Question question) {
		questions.put(question, true);
		if (!writeQuestionsToFile()) {
			questions.remove(question);
			return false;
		}
		
		return true;
	}

	private static boolean writeQuestionsToFile() {
		JsonObject root = new JsonObject();
		JsonArray questionsArr = new JsonArray();
		for (Question q : questions.keySet()) {
			questionsArr.add(buildJsonObject(q));
		}
		
		root.put("questions", questionsArr);
		
		try (FileWriter writer = new FileWriter(new File("resourses/questions.json"))) {
			writer.write(Jsoner.prettyPrint(root.toJson()));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	/*Method that's removing question from json*/

	public static boolean removeQuestion(Question question){
		boolean old = questions.remove(question);
		if (!writeQuestionsToFile()) {
			questions.put(question, old);
			return false;
		}
		return true;
	}
	/*Method that's editing question from json*/
	public static boolean editQuestion(Question old, Question newQuest){
		for (Question q : questions.keySet()) {
			if (q.equals(old)) {
				q.setText(newQuest.getText());
				q.setTeam(newQuest.getTeam());
				q.setDifficulty(newQuest.getDifficulty());
				q.setAnswer(newQuest.getAnswer());
				q.setTopic(newQuest.getTopic());
				q.setNumOfQuest(newQuest.getNumOfQuest());
				q.setMultyQuest(newQuest.getMultyQuest());
				if (!writeQuestionsToFile()) {
					q.setText(old.getText());
					q.setTeam(old.getTeam());
					q.setDifficulty(old.getDifficulty());
					q.setAnswer(old.getAnswer());
					q.setTopic(old.getTopic());
					q.setNumOfQuest(old.getNumOfQuest());
					q.setMultyQuest(old.getMultyQuest());
					return false;
				}
				
				return true;
			}
		}
		
		return false;
	}
	
	/*Method that get scordboard from json*/

	public static ArrayList<Game> getScoreboard() throws java.text.ParseException{
		ArrayList<Player>p;
		ArrayList<Game> allscoreGame= new ArrayList<>();
		CharacterName charc = null;
		String nick;
		String s;
		int score;
		ArrayList<String>about=new ArrayList<>();
		try (FileReader reader = new FileReader(new File("resourses/playersScores.json"))){
			JsonObject root=null;
			try {
				root = (JsonObject) Jsoner.deserialize(reader);
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JsonObject jsonObject = (JsonObject) root;
			JsonArray msg = (JsonArray) jsonObject.get("games");
			Iterator<Object> iterator = msg.iterator();
			while (iterator.hasNext()) {
				JsonObject objl = (JsonObject) iterator.next();
				String date1= (String) objl.get("date");
				JsonArray ja = (JsonArray) objl.get("players");
				Iterator itr2 = ja.iterator();
				p= new ArrayList<>();
				while (itr2.hasNext()){
					JsonObject obj1 = (JsonObject) itr2.next();
					nick=(String)obj1.get("gamerNickName");
					score=((Number)obj1.get("accuScore")).intValue();
					s=(String)obj1.get("character");
					if(s.equals(util.CharacterName.Mufasa.toString())){
						charc=util.CharacterName.Mufasa;
					}
					if(s.equals(util.CharacterName.Nala.toString())){
						charc=util.CharacterName.Nala;
					}
					if(s.equals(util.CharacterName.Pumbaa.toString())){
						charc=util.CharacterName.Pumbaa;
					}
					if(s.equals(util.CharacterName.Rafiki.toString())){
						charc=util.CharacterName.Rafiki;
					}
					
					if(s.equals(util.CharacterName.Scar.toString())){
						charc=util.CharacterName.Scar;
					}
					if(s.equals(util.CharacterName.Simba.toString())){
						charc=util.CharacterName.Simba;
					}
					if(s.equals(util.CharacterName.Timon.toString())){
						charc=util.CharacterName.Timon;
					}
					if(s.equals(util.CharacterName.Zazu.toString())){
						charc=util.CharacterName.Zazu;
					}
					 
					p.add(new Player(nick, new Character(charc), score));
					

				}
				   Date date3=new SimpleDateFormat("dd-MM-yyyy").parse(date1);  
				Game game=new Game(p,  date3);
				allscoreGame.add(game);
			}
	
			
		}
		 catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allscoreGame;
	}
	

	/*Method that's adding games into score board*/
	public static Boolean addPlayersAndScores(Game newgame) throws java.text.ParseException{
		ArrayList<Game> oldScore= getScoreboard();
		oldScore.add(newgame);
		return writeScoreToFile(oldScore);
	
	}
/*Method that return all esates*/
	public static ArrayList<Estate> getallesate(){
		boolean check= false;
		estates = new ArrayList();
		try (FileReader reader = new FileReader(new File("resourses/estates.json"))){
			JsonObject root=null;
			try {
				root = (JsonObject) Jsoner.deserialize(reader);
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JsonObject jsonObject = (JsonObject) root;
			JsonArray msg = (JsonArray) jsonObject.get("estates");
			Iterator<Object> iterator = msg.iterator();
			while (iterator.hasNext()) {
				JsonObject objl = (JsonObject) iterator.next();
				int numOfEstate= ((Number)objl.get("numOfEstate")).intValue();
				String name= (String) objl.get("name");
				long currentPrice=  ((Number) objl.get("currentPrice")).intValue();
				int square= ((Number) objl.get("square")).intValue();
				long value= ((Number) objl.get("value")).intValue();
				
				Square squre= new Square(square, Square_type.estate);
				Estate estate= EstateFactory.getEstate((String.valueOf(numOfEstate)),name, currentPrice, squre,null,value);
					estates.add(estate);
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return estates;
	}

/*Methond that return question*/
	public static void returnsQuestions(){
		HashMap<Question, Boolean> temp=new HashMap<Question, Boolean>();
		for(Question q:questions.keySet()){
			{temp.put(q,true);
			}
		}
		setQuestions(temp);
		System.out.println(SysData.getQuestions());
	}
	

	private static JsonObject buildJsonObject(Question q) {
		JsonObject obj = new JsonObject();
		obj.put("id", q.getNumOfQuest());
		obj.put("text", q.getText());
		obj.put("difficulty", q.getDifficulty());
		obj.put("isMultipleChoice", q.getMultyQuest());
		obj.put("team", q.getTeam());
		
		JsonArray answers = new JsonArray();
		for(Answer answer : q.getAnswer()) {
			JsonObject answerObj = new JsonObject();
			answerObj.put("text", answer.getText());
			answerObj.put("isCorrect", answer.getRightOrWrong());
			answers.add(answerObj);
		}
		obj.put("answers", answers);
		JsonArray tags = new JsonArray();
		for(Topic tag : q.getTopic()){
			tags.add(tag.toString());
			
		}
		obj.put("tags", tags);
		return obj;
	}

	private static JsonObject buildJsonObjectScor(Game newgame){
		JsonObject obj = new JsonObject();
		JsonArray players = new JsonArray();
		JsonObject play;
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		String date = formatter.format(newgame.getDate());
		obj.put("gameNumber", newgame.getGameNumber());
		obj.put("date", date);
		for(Player p :newgame.getPlayer()){
			if(p!=null){
			play= new JsonObject();
			play.put("gamerNickName", p.getGamerNickName());
			play.put("accuScore", p.getAccuScore());
			play.put("character", p.getCharacter().toString());
			players.add(play);
			}
			obj.put("players", players);
	}
		return obj;	
	}
	
	private static boolean writeScoreToFile(ArrayList<Game> gamescore) {

		JsonObject root = new JsonObject();
		JsonArray scoreArr = new JsonArray();
		for (Game q : gamescore) {
			scoreArr.add(buildJsonObjectScor(q));
		}
		
		root.put("games", scoreArr);
		
		try (FileWriter writer = new FileWriter(new File("resourses/playersScores.json"))) {
			writer.write(Jsoner.prettyPrint(root.toJson()));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/** 
	 * 
	 */
	public static String getPassword(){
		String pass=null;
		try (FileReader reader = new FileReader(new File("resourses/login.json"))){
			JsonObject root=null;
			
			try {
				root = (JsonObject) Jsoner.deserialize(reader);
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JsonObject jsonObject = (JsonObject) root;
			 pass=(String) jsonObject.get("password");
			 return pass;
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return pass;
	}
	public static boolean changePass(String Pass){
		JsonObject obj = new JsonObject();
		obj.put("userName", "Admin");
		obj.put("password", Pass);
		try (FileWriter writer = new FileWriter(new File("resourses/login.json"))) {
			writer.write(Jsoner.prettyPrint(obj.toJson()));
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	public static String getUserName(){
		String userName=null;
		try (FileReader reader = new FileReader(new File("resourses/login.json"))){
			JsonObject root=null;
			try {
				root = (JsonObject) Jsoner.deserialize(reader);
			} catch (DeserializationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JsonObject jsonObject = (JsonObject) root;
			userName=(String) jsonObject.get("userName");
			 return userName;
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return userName;
	}
	public void updateEstaeOwner()
	{
		ArrayList<Estate> newArray=new ArrayList<Estate>();
		for(Estate e: estates)
		{

			if(e instanceof EstateWithOwner)
			{
				Estate x=EstateFactory.getEstate(e.getNumOfEstate(), e.getName(),e.getvalue(), e.getSquare(),null, e.getvalue());
				newArray.add(x);
			}
			else
				newArray.add(e);
		}
		
		estates=newArray;
	}
}

