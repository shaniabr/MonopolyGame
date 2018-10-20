package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
public class Dice {
	/**
	 * 
	 * @author ronoc
	 * this class represents a Dice
	 *
	 */
	private Integer num;
	private ArrayList< BufferedImage> img;
	
	/**
	 * get/set functions
	 * */
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public ArrayList<BufferedImage> getImg() {
		return img;
	}
	public void setImg(ArrayList<BufferedImage> img) {
		this.img = img;
	}
	

	

}
