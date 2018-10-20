package util;

import java.io.File;

import java.io.IOException;

import javafx.scene.image.Image;

/**
 * Realimage implements the CharacterImage interface.
 */
public class Character implements CharacterImage{
		private  CharacterName name;
	   private RealImage ri;
	   private File f=null;
	    /**
	     * constructor
	     */
		public Character( CharacterName name1) {	
			this.name = name1;
			
		}
	
		
	    /**
	     * get & set methods
	     */
		
		public void setRI( ) {
		     this.ri=new RealImage(f);

		}
		
		public void setFile( ) {
			if (this.name == CharacterName.Simba) 
				f = new File("images/Simba.png");
			else if (this.name == CharacterName.Pumbaa)
				f = new File("images/Pumbaa.png");
			else if (this.name == CharacterName.Timon)
				f = new File("images/Timon.png");
			else if (this.name == CharacterName.Nala)
				f = new File("images/Nala.png");
			else if (this.name == CharacterName.Scar)
				f = new File("images/Scar.png");
			else if (this.name == CharacterName.Mufasa)
				f = new File("images/Mufasa.png");
			else if (this.name == CharacterName.Zazu)
				f = new File("images/Zazu.png");
			else if (this.name == CharacterName.Rafiki)
				f = new File("images/Rafiki.png");
			
		
			setRI();
		}
		
		
		
		
		
		public Image getImg( ) {
			if(f==null)
			{
				setFile();
			}
		     return ri.getImg();

		}

		public CharacterName getName( ) {
		     return name;

		}


		@Override
		public String toString() {
			return  name +"";
		}
		
		


}
