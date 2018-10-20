package model;

import java.util.HashMap;
import util.*;

import java.awt.Point;

public class Square {
	
	private int squareID; 
	private Square_type type;
	
	
	public Square(int squareID, Square_type type) {
		super();
		this.squareID = squareID;
		this.type = type;
	}
	public int getSquareID() {
		return squareID;
	}
	public void setSquareID(int squareID) {
		this.squareID = squareID;
	}
	
	public Square_type getType() {
		return type;
	}
	public void setType(Square_type type) {
		this.type = type;
	}
	public static HashMap<Integer, Square> getInstance() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + squareID;
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
		Square other = (Square) obj;
		if (squareID != other.squareID)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Square [squareID=" + squareID + ", type=" + type + "]";
	}
}
