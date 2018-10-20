package model;

import java.util.ArrayList;
import java.util.Date;

public class Game {

	private int gameNumber;
	private ArrayList<Player> player;
	private Date date;
	
	
	public Game( ArrayList<Player> player, Date date) {
		super();
		int i=0;
		 gameNumber=++i;
		this.player = player;
		this.date = date;
	}
	public int getGameNumber() {
		return gameNumber;
	}
	public void setGameNumber(int gameNumber) {
		this.gameNumber = gameNumber;
	}
	public ArrayList<Player> getPlayer() {
		return player;
	}
	public void setPlayer(ArrayList<Player> player) {
		this.player = player;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
