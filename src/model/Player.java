package model;
import util.*;
import util.Square_type;
import util.Character;
import util.Constants;
public class Player {
	/**
	 * 
	 * @author ronoc
	 * this class represents a player in the game
	 *
	 */
	private Integer playerTurnPlace; //[1-4]//
	private String gamerNickName;
	private double budget;
	private double accuScore;
	private boolean prison;
	private Character character;
	private Integer numofinvalidation;
	private Square square;

	
	/**
	 * constructors
	 * */
	
	public Player(Integer gamerNum, String gamerNickName, Integer budget, double accuScore, boolean prison,
			Character character, Integer numofinvalidation, Square square) {
		super();
		this.playerTurnPlace=gamerNum;
		this.gamerNickName = gamerNickName;
		this.budget = budget;
		this.accuScore = accuScore;
		this.prison = prison;
		this.character = character;
		this.numofinvalidation = 0;
		this.square = square;
	}
	public Player( String gamerNickName,Character character){
		super();
		this.gamerNickName = gamerNickName;
		this.budget = Constants.Initial_amount;
		this.character = character;
		this.numofinvalidation = 0;
		this.square=new Square(1, Square_type.start);
		
	}
	
	public Player( String gamerNickName,Character character, double accuScore){
		super();
		this.gamerNickName = gamerNickName;
		this.character = character;
		this.accuScore=accuScore;
	}
	
	
	/**
	 * get/set functions
	 * */
	
	
	public String getGamerNickName() {
		return gamerNickName;
	}
	public void setGamerNickName(String gamerNickName) {
		this.gamerNickName = gamerNickName;
	}
	public double getBudget() {
		return budget;
	}
	public void setBudget(double d) {
		this.budget = d;
	}
	public double getAccuScore() {
		return accuScore;
	}
	public void setAccuScore(double total) {
		this.accuScore = total;
	}
	public boolean isPrison() {
		return prison;
	}
	public void setPrison(boolean prison) {
		this.prison = prison;
	}
	public Character getCharacter() {
		return character;
	}
	public void setCharacter(Character character) {
		this.character = character;
	}
	public Integer getNumofinvalidation() {
		return numofinvalidation;
	}
	public void setNumofinvalidation(Integer numofinvalidation) {
		this.numofinvalidation = numofinvalidation;
	}
	public Square getSquare() {
		return square;
	}
	public void setSquare(Square position) {
		this.square = position;
	}

	public Integer getPlayerTurnPlace() {
		return playerTurnPlace;
	}
	public void setPlayerTurnPlace(Integer playerTurnPlace) {
		this.playerTurnPlace = playerTurnPlace;
	}
	
	/**
	 * others
	 * */
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((playerTurnPlace == null) ? 0 : playerTurnPlace.hashCode());
		return result;
	}
	@Override
	public String toString() {
		return "Player [playerTurnPlace=" + playerTurnPlace + ", gamerNickName=" + gamerNickName + ", budget=" + budget
				+ ", accuScore=" + accuScore + ", prison=" + prison + ", character=" + character
				+ ", numofinvalidation=" + numofinvalidation + ", square=" + square + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (playerTurnPlace == null) {
			if (other.playerTurnPlace != null)
				return false;
		} else if (!playerTurnPlace.equals(other.playerTurnPlace))
			return false;
		return true;
	}
	
	

}
