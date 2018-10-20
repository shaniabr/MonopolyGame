package model;

public interface  Estate {
	/**
	 * 
	 * @author ronoc
	 * this class represents an Estate in the game
	 **/

	
	
	/**
	 * constructor
	 * */
	
	/**
	 * get/set functions
	 * */
	
	public String getNumOfEstate();
	public void setNumOfEstate(String numOfEstate) ;
	public String getName();
	
	public void setName(String name) ;
	public long getvalue() ;
	public void setvalue(long value) ;
	public double getcurrentPrice() ;
	public void setcurrentPrice(long d) ;
	public Square getSquare() ;
	public void setSquare(Square square) ;
	/*
	public Player getOwner() {
		return owner;
	}
	public void setOwner(Player owner) {
		this.owner = owner;
	}*/

	/**
	 * others
	 * */
	@Override
	public String toString() ;
	


}
