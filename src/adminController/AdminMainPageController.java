package adminController;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import controller.AlertClass;
import controller.FxmlLoader;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminMainPageController implements Initializable{

    @FXML
    private BorderPane mainPane;

    @FXML
    private JFXButton accessBtn;

    @FXML
    private JFXButton btnLeave;

    @FXML
    void accesssBtnAction(ActionEvent event) {
      	setFontBlack();
    	setStyle(accessBtn);
    	  try {
  	        FxmlLoader obj = new FxmlLoader();
  	        Pane view = obj.getPage("/adminView/AccessPage.fxml");
  	        mainPane.setCenter(view);
  	    }catch (Exception e){
  	        AlertClass.showAlert(e.toString());
  	    }
    }

    @FXML
    void crossAction(ActionEvent event) {
    	Platform.exit();
        System.exit(0);
    }

    @FXML
    void leaveManagementAction(ActionEvent event) {
    	setFontBlack();
    	setStyle(btnLeave);
    	  try {
    	        FxmlLoader obj = new FxmlLoader();
    	        Pane view = obj.getPage("/adminView/LeaveManagementPage.fxml");
    	        mainPane.setCenter(view);
    	    }catch (Exception e){
    	        AlertClass.showAlert(e.toString());
    	    }
    }

    Alert alert=new Alert(AlertType.CONFIRMATION);
    @FXML
    void logoutAction(ActionEvent event) {
    	   alert.setContentText("Salir?");
      	    Optional<ButtonType> action=alert.showAndWait();
      	    if (action.get()==ButtonType.OK){
      	      try {
      	      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SignIn.fxml"));
      	      
      	      
      	      Parent root = (Parent) loader.load();
      	      Stage primaryStage = new Stage();
      	      Scene scene = new Scene(root);
      	      Image icon=new Image(getClass().getResourceAsStream("/images/logo.png"));
	          primaryStage.getIcons().add(icon);
      	      scene.setFill(Color.TRANSPARENT);
	          primaryStage.initStyle(StageStyle.TRANSPARENT);
      	      primaryStage.setScene(scene);
      	      primaryStage.show();
      	      ((Node) event.getSource()).getScene().getWindow().hide();
      	      }catch(Exception e) {
      	        AlertClass.showAlert(e.toString());
      	      }
      	    }

    }

    @FXML
    void minimizeAction(ActionEvent event) {
    	 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         stage.setIconified(true);
    }

    
	void setStyle(JFXButton btn) {
	    btn.setStyle(""
	        + "-fx-text-fill : white;"
	        + "-fx-border-color:white;"
	        + "-fx-border-width:0px 0px 3px 0px;"
	        + "-fx-background-color:#fc5e03;");
	    
	    
	  }
	  void setFontBlack() {
	    accessBtn.setStyle("-fx-text-fill : white;");
	    btnLeave.setStyle("-fx-text-fill : white;");
	    
	  }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	   	setFontBlack();
    	setStyle(accessBtn);
    	  try {
  	        FxmlLoader obj = new FxmlLoader();
  	        Pane view = obj.getPage("/adminView/AccessPage.fxml");
  	        mainPane.setCenter(view);
  	    }catch (Exception e){
  	        AlertClass.showAlert(e.toString());
  	    }
		
	}
	  
}
