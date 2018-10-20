package model;
/**
 * factory class for estate
 * @author ronoc
 *
 */
public class EstateFactory{
	
	
	public static Estate getEstate(String numOfEstate, String name, long currentPrice, Square square, Player owner,long value){
		 if(owner!=null)
		 { EstateWithOwner x2=new EstateWithOwner( numOfEstate,  name,  currentPrice,  square,owner, value);
		 return x2;
		 }
		else if(owner==null)
		{
			EstateWithNoOwner x=new EstateWithNoOwner( numOfEstate,  name,  currentPrice,  square, value);
			return x;
		}
		else
			return null;
	}

}
