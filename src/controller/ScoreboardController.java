package controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import java.util.HashMap;

import model.Answer;
import model.Estate;
import model.Game;

import model.Player;
import model.SysData;

public class ScoreboardController {
	
	private static ScoreboardController instance;

	
	public static ScoreboardController getInstance(){
		
		if(instance==null){
			instance=new ScoreboardController();
			return instance;
		}
		else
			return instance;
	}


	public static ArrayList<Game> getPlayersAndScores() throws ParseException{
		return SysData.getScoreboard();
	}
	

	public Boolean addPlayersAndScores(Game newgame) throws ParseException{
		return SysData.addPlayersAndScores(newgame);
	
	//	return SysData.getInstance().addPlayersAndScores(playersHash);
	}
			
		

}
