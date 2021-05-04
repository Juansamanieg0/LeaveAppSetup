package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class dashboardPageController implements Initializable{

    @FXML
    private Label lbl_total;

    @FXML
    private Label lbl_available;

    @FXML
    private Label lbl_used;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		getLeaves(SignInController.user_email);
		setLeaves();
		
	}

	int totalLeaves,PreviousUsed,Available;
	
	void getLeaves(String email) {
		try {
			Connection conn=Main.conn;
			PreparedStatement pst=conn.prepareStatement("SELECT * FROM LEAVE_RECORD WHERE EMAIL='"+email+"'");
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				totalLeaves=rs.getInt(2);
				Available=rs.getInt(3);
				PreviousUsed=rs.getInt(4);
				
			}
		}catch(Exception e) {
			AlertClass.showAlert(e.toString());
		}
	}
	
	void setLeaves() {
		lbl_total.setText(String.valueOf(totalLeaves));
		lbl_available.setText(String.valueOf(Available));
		lbl_used.setText(String.valueOf(PreviousUsed));
	}
}