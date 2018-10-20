package model;

import java.util.ArrayList;
import util.*;

public class Question {

	private Integer numOfQuest;
	private String text;
	private Boolean multyQuest;
	private ArrayList<Answer>answer;
	private ArrayList<Topic> topic;
	private String team;
	private Integer difficulty;
	
	public Question(Integer numOfQuest, String text, Boolean multyQuest) {
	super();
	this.numOfQuest = numOfQuest;
	this.text = text;
	this.multyQuest = multyQuest;
}
	public Question(Integer numOfQuest, String text, Boolean multyQuest, ArrayList<Answer> answer,
			ArrayList<Topic> topic, String team, Integer difficulty) {
		super();
		this.numOfQuest = numOfQuest;
		this.text = text;
		this.multyQuest = multyQuest;
		this.answer = answer;
		this.topic = topic;
		this.team = team;
		this.difficulty = difficulty;
	}
//	public Question(Integer numOfQuest, String text,
//			ArrayList<Topic> topic, Integer difficulty) {
//		super();
//		this.numOfQuest = numOfQuest;
//		this.text = text;
//		this.topic = topic;
//		this.difficulty = difficulty;
//	}


	////////////////////*get and set*//////////////////
	public Integer getNumOfQuest() {
		return numOfQuest;
	}
	public void setNumOfQuest(Integer numOfQuest) {
		this.numOfQuest = numOfQuest;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Boolean getMultyQuest() {
		return multyQuest;
	}
	public void setMultyQuest(Boolean multyQuest) {
		this.multyQuest = multyQuest;
	}
	public ArrayList<Answer> getAnswer() {
		return answer;
	}
	public void setAnswer(ArrayList<Answer> answer) {
		this.answer = answer;
	}
	public ArrayList<Topic> getTopic() {
		return topic;
	}
	public void setTopic(ArrayList<Topic> topic) {
		this.topic = topic;
	}
	public String getTeam() {
		return team;
	}
	public void setTeam(String team) {
		this.team = team;
	}
	public Integer getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(Integer difficulty) {
		this.difficulty = difficulty;
	}


	@Override
	public String toString() {
		return "Question [numOfQuest=" + numOfQuest + ", text=" + text + ", multyQuest=" + multyQuest  + ", team=" + team + ", difficulty=" + difficulty 
				+"answer"+ answer+ "]";
	}


	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numOfQuest == null) ? 0 : numOfQuest.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (numOfQuest == null) {
			if (other.numOfQuest != null)
				return false;
		} else if (!numOfQuest.equals(other.numOfQuest))
			return false;
		return true;
	}




	/*check answer*/
	public Boolean checkAnswer(Answer answer){
		if(answer!=null)
			return answer.getRightOrWrong();

		return null;
	}
}
