package application;
	
import java.sql.Connection;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;


public class Main extends Application {
	  Alert alert=new Alert(AlertType.CONFIRMATION);
	  public static Connection conn=null;

	@Override
	public void start(Stage primaryStage) {
		  try {
	    	  conn=db.DbConnection.connector();
	          Parent root =  FXMLLoader.load(getClass().getResource("/view/SignIn.fxml"));
	          Scene scene = new Scene(root);
	          Image icon=new Image(getClass().getResourceAsStream("/images/logo.png"));
	          primaryStage.getIcons().add(icon);
	          scene.getStylesheets().add(getClass().getResource("/application/application.css").toExternalForm());
	          scene.setFill(Color.TRANSPARENT);
	          primaryStage.initStyle(StageStyle.TRANSPARENT);
	          primaryStage.setScene(scene);
	      
	          primaryStage.show();
	      } catch(Exception e) {
	          alert.setContentText("Error Loading Main Class");
	          alert.show();
	      }
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
