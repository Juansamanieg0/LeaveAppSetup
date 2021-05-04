package db;

import java.sql.Connection;
import java.sql.PreparedStatement;

import application.Main;
import controller.AlertClass;

public class Quires {

	public static boolean InsertQuery(String query) {
		try {
		
			Connection conn=Main.conn;
			PreparedStatement pst=conn.prepareStatement(query);
			pst.execute();
			return true;
		}catch(Exception e) {
			if(e.toString().contains("UNIQUE")) {
				AlertClass.showAlert("Already Added");
				
			}else {
			AlertClass.showAlert(e.toString());
			}
		}
		return false;
	}

}
