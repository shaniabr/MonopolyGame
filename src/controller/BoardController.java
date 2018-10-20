package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.Player;
import model.Square;
import util.Character;
import util.Square_type;
/*
 * BoardController-represents the squares in the board.
 */
public class BoardController {

	private static BoardController instance;
	
	private BoardController() {
		super();
	
	}
	/**
	 * Singleton
	 * @return
	 */
	public static BoardController getInstance(){	
		if(instance==null){
			instance=new BoardController();
			return instance;
		}
		else
			return instance;
	}
	
	
	public ArrayList<Square> initializeSquares(){
		ArrayList<Square> board= new ArrayList<Square>();
		board.add(new Square(1, Square_type.start));
		board.add(new Square(2, Square_type.estate));
		board.add(new Square(3, Square_type.lucky));
		board.add(new Square(4, Square_type.estate));
		board.add(new Square(5, Square_type.estate));
		board.add(new Square(6, Square_type.estate));
		board.add(new Square(7, Square_type.estate));
		board.add(new Square(8, Square_type.lucky));
		board.add(new Square(9, Square_type.estate));
		board.add(new Square(10, Square_type.estate));
		board.add(new Square(11, Square_type.goToJail));
		board.add(new Square(12, Square_type.estate));
		board.add(new Square(13, Square_type.question));
		board.add(new Square(14, Square_type.estate));
		board.add(new Square(15, Square_type.estate));
		board.add(new Square(16, Square_type.question));
		board.add(new Square(17, Square_type.estate));
		board.add(new Square(18, Square_type.lucky));
		board.add(new Square(19, Square_type.estate));
		board.add(new Square(20, Square_type.estate));
		board.add(new Square(21, Square_type.question));
		board.add(new Square(22, Square_type.estate));
		board.add(new Square(23, Square_type.question));
		board.add(new Square(24, Square_type.estate));
		board.add(new Square(25, Square_type.estate));
		board.add(new Square(26, Square_type.lucky));
		board.add(new Square(27, Square_type.estate));
		board.add(new Square(28, Square_type.estate));
		board.add(new Square(29, Square_type.question));
		board.add(new Square(30, Square_type.estate));
		board.add(new Square(31, Square_type.jail));
		board.add(new Square(32, Square_type.estate));
		board.add(new Square(33, Square_type.estate));
		board.add(new Square(34, Square_type.lucky));
		board.add(new Square(35, Square_type.estate));
		board.add(new Square(36, Square_type.question));
		board.add(new Square(37, Square_type.lucky));
		board.add(new Square(38, Square_type.estate));
		board.add(new Square(39, Square_type.question));
		board.add(new Square(40, Square_type.estate));
		
		
		
		return board;
	}
	
}


/*
for(int i=1;i<41;i++)
{
	Square s=new Square(i,Square_type.question);
	g.getSquares().put(i, s);
}
*/