package util;

import java.io.File;

import java.io.IOException;

import javafx.scene.image.Image;


public interface CharacterImage {

	/**
	 * A method to get the image.
	 */
		public default Image getImg( ) {
			return null;}
		/**
		 * A method to get the image.
		 */
			public default CharacterName getName( ) {
				return null;}

}
