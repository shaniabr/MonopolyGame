package model;

public class Answer {

	/**
	 * 
	 * @author ronoc
	 * this class represents an Answer in the game(a part of a question)
	 *
	 */
	private String text;
	private Boolean rightOrWrong;
	
	/**
	 * constructors
	 * */
	public Answer(String text, Boolean rightOrWrong) {
		super();
		this.text = text;
		this.rightOrWrong = rightOrWrong;
	}
	/**
	 * get/set functions
	 * */
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Boolean getRightOrWrong() {
		return rightOrWrong;
	}
	public void setRightOrWrong(Boolean rightOrWrong) {
		this.rightOrWrong = rightOrWrong;
	}

	/**
	 * others
	 * */
	@Override
	public String toString() {
		return "Answer [text=" + text + ", rightOrWrong=" + rightOrWrong + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((rightOrWrong == null) ? 0 : rightOrWrong.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		Answer other = (Answer) obj;
		if (rightOrWrong == null) {
			if (other.rightOrWrong != null)
				return false;
		} else if (!rightOrWrong.equals(other.rightOrWrong))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

}
