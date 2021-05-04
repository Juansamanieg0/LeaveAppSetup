package controller;

import java.sql.Connection;

import com.jfoenix.controls.JFXTextField;

import application.Main;
import db.Quires;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SignUpController {

    @FXML
    private JFXTextField txt_fname;

    @FXML
    private JFXTextField txt_lname;

    @FXML
    private JFXTextField txt_email;

    @FXML
    private JFXTextField txt_password;

    @FXML
    void CloseAction(ActionEvent event) {
    	Platform.exit();
        System.exit(0);
    }

    @FXML
    void requestBtnAction(ActionEvent event) {
    	getValues();
    	if(IsEmpty()==true) {
    		AlertClass.showAlert("Por favor complete todos los detalles");
    	}else {
    		Connection connection=Main.conn;
    		String sql="INSERT INTO EMPLOYEE VALUES('"+fname+"','"+lname+"','"+email+"','"+password+"','"+status+"')";
    		boolean x=Quires.InsertQuery(sql);
    		if(x==true) {
    		AlertClass.showAlert("Solicitud exitosa");
    		 sql="INSERT INTO LEAVE_RECORD VALUES('"+email+"','50','50','0')";
    		Quires.InsertQuery(sql);
    		}
    	}
    }

    String fname,lname,email,password,status;

    @FXML
    void signInBtnAction(ActionEvent event) {

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
   		} catch (Exception e) {
   			AlertClass.showAlert(e.toString());
   		}
    }
    
    
    
    void getValues() {
    	fname=txt_fname.getText();
    	lname=txt_lname.getText();
    	email=txt_email.getText();
    	password=txt_password.getText();
    	status="Pendiente";
    }
    boolean IsEmpty() {
    	if(fname.isEmpty()||lname.isEmpty()||email.isEmpty()||password.isEmpty()) {
    		AlertClass.showAlert("Please Fill All Details");
    		return true;
    	}else {
    		return false;
    	}
    }
    
    
   

}
