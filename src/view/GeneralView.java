package view;

import java.io.IOException;
import java.util.ArrayList;

import org.controlsfx.control.Notifications;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.Player;

/**
 * General view class for common methods
 */
public class GeneralView {
	public static void openPage (Button button,String page, Object c) throws IOException {
		Stage stage = (Stage) button.getScene().getWindow();
		Parent root= null;
		try {
			root = FXMLLoader.load(c.getClass().getResource(page));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		
		
	}

	public static void openPage (Button button,String page, Object c, I_ViewCo vc,Object data) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(c.getClass().getResource(page));
		AnchorPane pageView = (AnchorPane) loader.load();
		Scene scene = new Scene(pageView);
		Stage st=  (Stage) button.getScene().getWindow();
		vc= loader.getController();	
		vc.setData(data);
		st.setScene(scene);
		st.show();
	}
	
	public static void openPageDecorated (String page, Object c) throws IOException {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(c.getClass().getResource(page));
			AnchorPane pageView;
			pageView = (AnchorPane) loader.load();
			Scene scene = new Scene(pageView);
			Stage st = new Stage();
			st.setScene(scene);
			st.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void openPageDecorated (String page, Object c, I_ViewCo vc,Object data) throws IOException {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(c.getClass().getResource(page));
			AnchorPane pageView;
			pageView = (AnchorPane) loader.load();
			Scene scene = new Scene(pageView);
			Stage st = new Stage();
			vc= loader.getController();	
			vc.setData(data);
			st.setScene(scene);
			st.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void openPageDecorated (String page, Object c, I_ViewCo vc,ArrayList<Player> data) throws IOException {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(c.getClass().getResource(page));
			AnchorPane pageView;
			pageView = (AnchorPane) loader.load();
			Scene scene = new Scene(pageView);
			Stage st = new Stage();
			vc= loader.getController();	
			vc.setData(data);
			st.setScene(scene);
			st.show();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void openPageAlert (String page, Object c,I_ViewCo vc , Object data) throws IOException {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(c.getClass().getResource(page));
			AnchorPane pageView;
			pageView = (AnchorPane) loader.load();
			Scene scene = new Scene(pageView);
			Stage st = new Stage();
			vc= loader.getController();	
			vc.setData(data);
			st.initStyle(StageStyle.UNDECORATED);
			st.setScene(scene);
			Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
			st.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() -1000);
			st.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - 400);
			st.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void closeStage (Button button) throws IOException {
			Stage stage = (Stage) button.getScene().getWindow();
		    stage.close();
		
	}
	
	public static void openPage (String page, Object c) throws IOException {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(c.getClass().getResource(page));
			AnchorPane pageView;
			pageView = (AnchorPane) loader.load();
			Scene scene = new Scene(pageView);
			Stage st = new Stage();
			st.initStyle(StageStyle.UNDECORATED);
			st.setScene(scene);
			st.setHeight(369);
			st.setWidth(302);
			Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
			st.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() -900);
			st.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - 500);
			st.show();
			
			
			 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

	
	public static void openPage (String page, Object c,I_ViewCo currentController , ArrayList<Object> data) throws IOException {
		try {
			FXMLLoader loader = new FXMLLoader();
			System.out.println(page.toString());
			loader.setLocation(c.getClass().getResource(page));
			
			AnchorPane pageView;
			pageView = (AnchorPane) loader.load();
			Scene scene = new Scene(pageView);
			Stage st = new Stage();
			st.initStyle(StageStyle.UNDECORATED);
			st.setScene(scene);
			st.setHeight(369);
			st.setWidth(302);
			
			Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
			st.setX(primaryScreenBounds.getMinX() + primaryScreenBounds.getWidth() -900);
			st.setY(primaryScreenBounds.getMinY() + primaryScreenBounds.getHeight() - 500);
			
			currentController= loader.getController();		
			
			//adding scene in order to get it later in single player mode...
			((ArrayList<Object>)data).add(scene);
			currentController.setData(data);
			st.setScene(scene);
			st.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public static void notification(String s,String title ) {
		Notifications notificationBuilder = Notifications.create()
			//.title(title)
			.text(s)
			.hideAfter(Duration.seconds(5))
			.position(Pos.CENTER);
			notificationBuilder.graphic(new Rectangle(600, 400, Color.RED)); 

		if (title.equals("Error"))
			notificationBuilder.showError();
		else if (title.equals("Confirm"))
			notificationBuilder.showConfirm();
		else if (title.equals("Warning"))
			notificationBuilder.showWarning();
		else 
			notificationBuilder.showInformation();

	}
}
