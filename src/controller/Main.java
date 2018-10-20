package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import model.Answer;
import model.Estate;
import model.Game;
import model.Player;
import model.Question;
import model.SysData;
import util.Character;
import util.Topic;
import view.OpenMainPageView;

public class Main {

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
            	try {
					SysData.getInstance();
					
				} catch (ParseException e) {
					e.printStackTrace();
				}  
            	view.OpenScreenView.startScreen();

            }
        });

	}

}
