package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import application.Main;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SignInController implements Initializable{

    @FXML
    private TextField txt_email;

    @FXML
    private PasswordField txt_password;

    @FXML
    private ComboBox<String> signInComboBox;
    
    ObservableList<String> loginAs= FXCollections.observableArrayList("ADMIN","EMPLOYEE");
    
    String email,password;
    static String  user_fname,user_lname,user_email;
    void getText() {
    	email=txt_email.getText();
    	password=txt_password.getText();
    }
    @FXML
    void CloseAction(ActionEvent event) {
    	Platform.exit();
        System.exit(0);
    }

    @FXML
    void requestAccessAction(ActionEvent event) {

   	 try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Signup.fxml"));
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

    @FXML
    void signInAction(ActionEvent event) {
    	if(signInComboBox.getValue().equals("EMPLOYEE")) {
    	getText();
    	boolean x=getEmployeeData();
    	if(x==true) {
		    	 try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EmployeeMainPage.fxml"));
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
    	}else {
    		AlertClass.showAlert("Incorrect Email/Password OR Access Denied");
    	}
    	}else {
    		if(txt_email.getText().equals("admin@gmail.com")||txt_password.getText().equals("admin")) {
    			 try {
 					FXMLLoader loader = new FXMLLoader(getClass().getResource("/adminView/AdminMainPage.fxml"));
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
    		}else {
    			AlertClass.showAlert("Incorrect Email/Password");
    		}
    	}
    }

    boolean getEmployeeData() {
    	try {
    		Connection conn=Main.conn;
    		String sql="SELECT * FROM EMPLOYEE WHERE EMAIL='"+email+"' AND PASSWORD='"+password+"' AND STATUS='Abrbadas' ";
    		PreparedStatement pst=conn.prepareStatement(sql);
    		ResultSet rs=pst.executeQuery();
    		while(rs.next()) {
    			user_fname=rs.getString(1);
    			user_lname=rs.getString(2);
    			user_email=rs.getString(3);
    			return true;
    		}
    		rs.close();
    		pst.close();
    	}catch(Exception e) {
    		AlertClass.showAlert(e.toString());
    	}
    	return false;
    }
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		signInComboBox.setItems(loginAs);
		signInComboBox.setValue(loginAs.get(1));
		
	}
}
