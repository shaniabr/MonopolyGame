package util;

import java.io.File;

import java.io.IOException;

import javafx.scene.image.Image;

/**
 * Realimage implements the CharacterImage interface.
 */
public class RealImage implements CharacterImage{
		private Image img= null;
		private File f = null;
		
	    /**
	     * constructor
	     */
		public RealImage( File f1) {	
			this.f = f1;
			setImg( );
		}
	
		
	    /**
	     * get & set methods
	     */
		
		

		public void setImg( ) {
			//System.out.println(f.toURI().toString());
		     img = new Image(f.toURI().toString());
		}
		
		public Image getImg( ) {
		     return img;

		}

		public File getFile( ) {
		     return f;

		}
		

}
