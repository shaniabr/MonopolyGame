package view;

/**
 * Controller interface for data transfers between controllers.
 */
public interface I_ViewCo {
	/**
	 * A method to set data inside a controller.
	 * @param data to be set in the controller.
	 */
	public default void setData(Object data){};

}
