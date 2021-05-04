package controller;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import application.Main;
import db.Quires;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import model.LeaveTable;

public class leavePageController implements Initializable{

    @FXML
    private DatePicker txt_fromdate;

    @FXML
    private DatePicker txt_todate;

    @FXML
    private TableView<LeaveTable> leaveTable;

    @FXML
    private TableColumn<LeaveTable,String> fromColumn;

    @FXML
    private TableColumn<LeaveTable,String> toColumn;

    @FXML
    private TableColumn<LeaveTable,String> statusColumn;

    @FXML
    void applyAction(ActionEvent event) {
    	getValues();
    	if(IsEmpty()==true) {
    		AlertClass.showAlert("Por favor complete todos los detalles");
    	}else {
    		Connection connection=Main.conn;
    		String sql="INSERT INTO LEAVE VALUES('"+email+"','"+fromdate+"','"+todate+"','"+status+"')";
    		boolean x=Quires.InsertQuery(sql);
    		PopulateTable("SELECT * FROM LEAVE WHERE EMAIL='"+SignInController.user_email+"'");
    	
    	}
    }
String email,fromdate,todate,status;

    void getValues() {
    email=SignInController.user_email;
    fromdate=txt_fromdate.getValue().toString();
    todate=txt_todate.getValue().toString();
    status="Pendiente";
    }
    
    boolean IsEmpty() {
    	if(email.isEmpty()||fromdate.isEmpty()||todate.isEmpty()) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    
    
    ObservableList<LeaveTable> oblist= FXCollections.observableArrayList();
    
    public  void  PopulateTable( String sql){
        oblist.clear();
        leaveTable.getItems().clear();
          try {
        	  Connection connection=Main.conn;
              ResultSet rs = connection.createStatement().executeQuery(sql);
              while(rs.next()){
                
                  oblist.add( new LeaveTable(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
            
              }
          
          fromColumn.setCellValueFactory(new PropertyValueFactory<LeaveTable,String>("fromdate"));
          toColumn.setCellValueFactory(new PropertyValueFactory<LeaveTable,String>("todate"));
          statusColumn.setCellValueFactory(new PropertyValueFactory<LeaveTable,String>("status"));
          
         
          leaveTable.setItems(null);
          leaveTable.setItems(oblist);
        
          
          }
          catch (Exception e) {
                AlertClass.showAlert(e.toString());
              }

      }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		PopulateTable("SELECT * FROM LEAVE WHERE EMAIL='"+SignInController.user_email+"'");
		
	}
}
