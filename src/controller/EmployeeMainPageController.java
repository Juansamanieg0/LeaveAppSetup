package controller;


import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

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
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EmployeeMainPageController implements Initializable{

    @FXML
    private BorderPane mainPane;

    @FXML
    private JFXButton btnLeave;


    @FXML
    private JFXButton btnDashboard;
    
    @FXML
    private Label lbl_name;
    
    @FXML
    void crossAction(ActionEvent event) {
    	Platform.exit();
        System.exit(0);
    }

    @FXML
    void dashboardAction(ActionEvent event) {
    	setFontBlack();
    	setStyle(btnDashboard);
    	  try {
  	        FxmlLoader obj = new FxmlLoader();
  	        Pane view = obj.getPage("/view/dashboardPage.fxml");
  	        mainPane.setCenter(view);
  	    }catch (Exception e){
  	        AlertClass.showAlert(e.toString());
  	    }
    }

    @FXML
    void leaveManagementAction(ActionEvent event) {
    	setFontBlack();
    	setStyle(btnLeave);
    	   try {
   	        FxmlLoader obj = new FxmlLoader();
   	        Pane view = obj.getPage("/view/leavePage.fxml");
   	        mainPane.setCenter(view);
   	    }catch (Exception e){
   	        AlertClass.showAlert(e.toString());
   	    }
    }

    Alert alert =new Alert(AlertType.CONFIRMATION);
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
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lbl_name.setText(SignInController.user_fname+" "+SignInController.user_lname);
		setFontBlack();
    	setStyle(btnDashboard);
	    try {
	        FxmlLoader obj = new FxmlLoader();
	        Pane view = obj.getPage("/view/dashboardPage.fxml");
	        mainPane.setCenter(view);
	    }catch (Exception e){
	        AlertClass.showAlert(e.toString());
	    }
		
	}

	
	
	
	
	
	void setStyle(JFXButton btn) {
	    btn.setStyle(""
	        + "-fx-text-fill : white;"
	        + "-fx-border-color:white;"
	        + "-fx-border-width:0px 0px 3px 0px;"
	        + "-fx-background-color:#fc5e03;");
	    
	    
	  }
	  void setFontBlack() {
	    btnDashboard.setStyle("-fx-text-fill : white;");
	    btnLeave.setStyle("-fx-text-fill : white;");
	    
	  }
	
	
}
