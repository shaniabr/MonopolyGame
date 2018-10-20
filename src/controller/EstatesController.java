package controller;

import java.util.ArrayList;
import model.Estate;
import model.SysData;

public class EstatesController {
	
	private static EstatesController instance;
	
	
	
	public static EstatesController getInstance(){
		
		if(instance==null){
			instance=new EstatesController();
			return instance;
		}
		else
			return instance;
	}


	public ArrayList<Estate> getEstates(){
		return SysData.getEstates();
	}

		
		

}
