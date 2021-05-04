package db;

import java.sql.Connection;
import java.sql.DriverManager;

import javafx.scene.control.Alert;

public class DbConnection {
	Connection conn=null;
	public static Connection connector()  {
		try {
			
			Class.forName("org.sqlite.JDBC");
			Connection conn= DriverManager.getConnection("jdbc:sqlite:database.db");
//			   String dbUrl = "jdbc:sqlserver://17cp09server.database.windows.net;databaseName=AbdullahButtDb";
//	            String user = "Abdullahbutt";		
//	            String pass = "17-Cp-09";
//	            Connection conn = DriverManager.getConnection(dbUrl, user, pass);
			return conn;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Alert a=new Alert(Alert.AlertType.INFORMATION);
			a.setContentText(""+e);
			a.showAndWait();
			a.setHeaderText(null);
			return null;
		}
		
	}

}
