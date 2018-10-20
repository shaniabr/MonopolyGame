package model;

public class Admin {
	private String uName;
	private String password;
	
	public Admin(String uName, String password) {
		this.uName = uName;
		this.password = password;
	}

	/**
	 * @return the uName
	 */
	public String getuName() {
		return uName;
	}

	/**
	 * @param uName the uName to set
	 */
	public void setuName(String uName) {
		this.uName = uName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Admin [uName=" + uName + ", password=" + password + "]";
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}

