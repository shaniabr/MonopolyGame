package model;

public class EstateWithOwner implements Estate {
	/**
	 * 
	 * @author ronoc
	 * this class represents a player in the game
	 *
	 */
	private String numOfEstate;
	private String name;
	private long  currentPrice;
	private long value;
	private Square square;
	private Player owner;
	
	/**
	 * constructor
	 * */
	public EstateWithOwner(String numOfEstate, String name, long currentPrice, Square square, Player owner,long value) {
		super();
		this.numOfEstate = numOfEstate;
		this.name = name;
		this.currentPrice = currentPrice;
		this.square = square;
		this.owner = owner;
		this.value = value;
	}
	/**
	 * get/set functions
	 * */
	
	public String getNumOfEstate() {
		return numOfEstate;
	}
	public void setNumOfEstate(String numOfEstate) {
		this.numOfEstate = numOfEstate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getvalue() {
		return value;
	}
	
	public void setvalue(long value) {
		this.value = value;
	}
	
	public double getcurrentPrice() {
		return currentPrice;
	}
	
	public void setcurrentPrice(long d) {
		this.currentPrice = d;
	}
	
	public Square getSquare() {
		return square;
	}
	public void setSquare(Square square) {
		this.square = square;
	}
	public Player getOwner() {
		return owner;
	}
	public void setOwner(Player owner) {
		this.owner = owner;
	}

	/**
	 * others
	 * */
	@Override
	public String toString() {
		return "Estate [numOfEstate=" + numOfEstate + ", name=" + name + ", currentPrice=" + currentPrice + ", value="
				+ value + ", square=" + square + ", owner=" + owner + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numOfEstate == null) ? 0 : numOfEstate.hashCode());
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
		EstateWithOwner other = (EstateWithOwner) obj;
		if (numOfEstate == null) {
			if (other.numOfEstate != null)
				return false;
		} else if (!numOfEstate.equals(other.numOfEstate))
			return false;
		return true;
	}
	
	


}
